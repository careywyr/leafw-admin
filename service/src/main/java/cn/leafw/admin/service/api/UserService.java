package cn.leafw.admin.service.api;

import cn.leafw.admin.dao.entity.User;
import cn.leafw.admin.service.dto.UserDto;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CareyWYR
 * @since 2019-05-30
 */
public interface UserService extends IService<User> {

    /**
     * @param userLoginDto
     * @return
     */
    UserDto loginByUserNameAndPwd(UserDto userLoginDto);

}
