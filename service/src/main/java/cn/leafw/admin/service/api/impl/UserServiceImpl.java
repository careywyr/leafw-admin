package cn.leafw.admin.service.api.impl;

import cn.leafw.admin.dao.entity.User;
import cn.leafw.admin.dao.mapper.UserMapper;
import cn.leafw.admin.service.api.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
