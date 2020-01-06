package cn.leafw.admin.controller;


import cn.leafw.admin.service.UserService;
import cn.leafw.framework.dto.ResultDTO;
import cn.leafw.framework.security.UserContext;
import cn.leafw.framework.utils.ResultHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 *  User前端控制器
 * </p>
 *
 * @author CareyWYR
 * @since 2019-05-30
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/permission")
    public ResultDTO userPermission(){
        return ResultHelper.returnOk(userService.queryUserPermission(UserContext.getUser().getUserId()));
    }

}

