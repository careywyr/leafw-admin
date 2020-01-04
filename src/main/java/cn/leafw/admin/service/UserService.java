package cn.leafw.admin.service;

import cn.hutool.crypto.SecureUtil;
import cn.leafw.admin.mapper.UserMapper;
import cn.leafw.admin.model.dto.LoginDTO;
import cn.leafw.admin.model.entity.UserDO;
import cn.leafw.framework.base.BaseServiceImpl;
import cn.leafw.framework.exception.BusinessException;
import cn.leafw.framework.security.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

    /**
     * 登陆
     * @param loginDTO  loginDTO
     */
    public Map<String, String> login(LoginDTO loginDTO){
        Map<String, String> userMap = new HashMap<>();
        UserDO queryUserDO = new UserDO();
        queryUserDO.setEmail(loginDTO.getEmail());
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

}
