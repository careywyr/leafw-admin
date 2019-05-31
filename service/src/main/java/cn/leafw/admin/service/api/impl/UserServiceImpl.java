package cn.leafw.admin.service.api.impl;

import cn.leafw.admin.dao.entity.User;
import cn.leafw.admin.dao.mapper.UserMapper;
import cn.leafw.admin.service.api.UserService;
import cn.leafw.admin.service.common.utils.BeanConverter;
import cn.leafw.admin.service.dto.UserDto;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author CareyWYR
 * @since 2019-05-30
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public UserDto loginByUserNameAndPwd(UserDto userLoginDto){
        log.info("用户登陆, userDto = {}", JSONObject.toJSONString(userLoginDto));
        EntityWrapper<User> entityWrapper = new EntityWrapper<>();
        entityWrapper.setEntity(BeanConverter.convertOne(userLoginDto, User.class));
        entityWrapper.
                eq("user_name", userLoginDto.getUserName()).
                eq("password", userLoginDto.getPassword()).
                eq("isdeleted", 0);
        User user = selectOne(entityWrapper);
        return null == user?null:BeanConverter.convertOne(user, UserDto.class);
    }


}
