package cn.leafw.admin.service;

import cn.hutool.crypto.SecureUtil;
import cn.leafw.admin.mapper.UserMapper;
import cn.leafw.admin.model.dto.LoginDTO;
import cn.leafw.admin.model.entity.PermissionDO;
import cn.leafw.admin.model.entity.RoleDO;
import cn.leafw.admin.model.entity.UserDO;
import cn.leafw.admin.model.entity.UserRoleDO;
import cn.leafw.admin.model.vo.PermissionTreeVO;
import cn.leafw.admin.model.vo.UserPermissionVO;
import cn.leafw.admin.model.vo.UserQueryVO;
import cn.leafw.admin.model.vo.UserVO;
import cn.leafw.admin.utils.TreeUtil;
import cn.leafw.framework.base.BaseServiceImpl;
import cn.leafw.framework.exception.BusinessException;
import cn.leafw.framework.security.JwtUtil;
import cn.leafw.framework.utils.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

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
    @Autowired
    private UserRoleService userRoleService;

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

        UserRoleDO userRoleDO = new UserRoleDO();
        userRoleDO.setUserId(userDO.getUserId());
        List<UserRoleDO> userRoleDOS = userRoleService.select(userRoleDO);
        List<String> roleIds = userRoleDOS.stream().map(e -> String.valueOf(e.getRoleId())).collect(Collectors.toList());
        List<RoleDO> roleDOS = roleService.selectByIdIn(roleIds);
        Set<String> permissionIds = new HashSet<>();
        roleDOS.forEach(roleDO -> {
            Set<String> oneRolePermissionIds = StringUtils.commaDelimitedListToSet(roleDO.getPermissionIds());
            permissionIds.addAll(oneRolePermissionIds);
        });
        //todo 改成从user_role表查询
        userPermissionVO.setRoleIds(roleIds);
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

    public PageInfo<UserVO> selectUserList(UserQueryVO userQueryVO){
        return PageHelper.startPage(userQueryVO.getCurrentPage(), userQueryVO.getPageSize()).doSelectPageInfo(() -> userMapper.selectUserList(userQueryVO));
    }

    /**
     * 创建用户
     * @param userVO
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void createUser(UserVO userVO){
        if(StringUtils.isEmpty(userVO.getRoleIds())){
            throw BusinessException.of("角色不能为空!");
        }
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userVO, userDO);
        userDO.setCreated(System.currentTimeMillis()/1000L);
        userDO.setUpdated(System.currentTimeMillis()/1000L);
        this.insert(userDO);
        // 保存user_role表
        String[] roleIds = StringUtils.commaDelimitedListToStringArray(userVO.getRoleIds());
        userRoleService.saveUserRole(roleIds, userDO.getUserId(), userVO.getCreateby());
    }

    /**
     * 更新用户
     * @param userVO
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateUser(UserVO userVO){
        if(null == userVO.getUserId()){
            throw BusinessException.of("用户id为空!");
        }
        if(StringUtils.isEmpty(userVO.getRoleIds())){
            throw BusinessException.of("角色不能为空!");
        }
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userVO, userDO);
        userDO.setUpdated(System.currentTimeMillis()/1000L);
        this.updateByPrimaryKey(userDO);
        // 保存user_role表
        String[] roleIds = StringUtils.commaDelimitedListToStringArray(userVO.getRoleIds());
        UserRoleDO userRoleDO = new UserRoleDO();
        userRoleDO.setUserId(userVO.getUserId());
        userRoleService.delete(userRoleDO);
        userRoleService.saveUserRole(roleIds, userDO.getUserId(), userVO.getUpdateby());
    }

}
