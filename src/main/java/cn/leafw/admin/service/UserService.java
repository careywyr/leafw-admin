package cn.leafw.admin.service;

import cn.hutool.core.convert.impl.BeanConverter;
import cn.hutool.crypto.SecureUtil;
import cn.leafw.admin.mapper.UserMapper;
import cn.leafw.admin.model.dto.LoginDTO;
import cn.leafw.admin.model.entity.PermissionDO;
import cn.leafw.admin.model.entity.RoleDO;
import cn.leafw.admin.model.entity.UserDO;
import cn.leafw.admin.model.vo.PermissionTreeVO;
import cn.leafw.admin.model.vo.UserPermissionVO;
import cn.leafw.admin.utils.TreeUtil;
import cn.leafw.framework.base.BaseServiceImpl;
import cn.leafw.framework.exception.BusinessException;
import cn.leafw.framework.security.JwtUtil;
import cn.leafw.framework.utils.StringUtils;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CareyWYR
 * @since 2019-05-30
 */
@Service
public class UserService extends BaseServiceImpl<UserDO> {

    @Resource
    private UserMapper userMapper;
    @Value("${security.jwt.ttl}")
    private Long ttl;
    @Value("${security.jwt.secret}")
    private String secret;
    @Value("${wx.appId}")
    private String appId;
    @Value("${wx.appSecret}")
    private String appSecret;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 登陆
     * @param loginDTO  loginDTO
     */
    public Map<String, String> login(LoginDTO loginDTO){
        Map<String, String> userMap = new HashMap<>();
        UserDO queryUserDO = new UserDO();
        queryUserDO.setUserName(loginDTO.getUserName());
        String encodePassword = SecureUtil.md5(loginDTO.getPassword());
        queryUserDO.setPassword(encodePassword);
        UserDO userDO = Optional.ofNullable(userMapper.selectOne(queryUserDO)).orElseThrow(() -> BusinessException.of("账号或密码不正确"));
        userMap.put("userId", String.valueOf(userDO.getUserId()));
        userMap.put("userName",userDO.getUserName());
        userMap.put("email",userDO.getEmail());
        String jwt = JwtUtil.createJwt(String.valueOf(userDO.getUserId()), ttl, secret, userMap);
        userMap.put("token", jwt);
        return userMap;
    }

    /**
     * 查询用户权限
     * @param userId
     * @return
     */
    public UserPermissionVO queryUserPermission(Long userId){
        UserPermissionVO cachePermission = (UserPermissionVO) redisTemplate.opsForValue().get("PERMISSION_" + userId);
        if(null != cachePermission){
            return cachePermission;
        }
        UserPermissionVO  userPermissionVO = new UserPermissionVO();
        UserDO userDO = this.selectByPrimaryKey(userId);
        if(StringUtils.isEmpty(userDO.getRoleIds())){
            throw BusinessException.of("用户角色不存在!");
        }
        userPermissionVO.setUserId(userDO.getUserId());
        userPermissionVO.setUserName(userDO.getUserName());
        String[] roleIds = StringUtils.commaDelimitedListToStringArray(userDO.getRoleIds());
        List<RoleDO> roleDOS = roleService.selectByIdIn(roleIds);
        Set<String> permissionIds = new HashSet<>();
        roleDOS.forEach(roleDO -> {
            Set<String> oneRolePermissionIds = StringUtils.commaDelimitedListToSet(roleDO.getPermissionIds());
            permissionIds.addAll(oneRolePermissionIds);
        });
        userPermissionVO.setRoleIds(Arrays.asList(roleIds));
        List<PermissionDO> permissions = permissionService.selectByIdIn(permissionIds);
        List<PermissionTreeVO> tempTree = new ArrayList<>();
        for (PermissionDO permission : permissions) {
            PermissionTreeVO permissionTreeVO = new PermissionTreeVO();
            BeanUtils.copyProperties(permission, permissionTreeVO);
            tempTree.add(permissionTreeVO);
        }
        List<PermissionTreeVO> permissionTree = TreeUtil.buildByRecursive(tempTree);
        userPermissionVO.setPermissions(permissionTree);
        redisTemplate.opsForValue().set("PERMISSION_"+userId, userPermissionVO);
        return userPermissionVO;
    }

}
