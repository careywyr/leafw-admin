package cn.leafw.admin.controller;

import cn.leafw.admin.model.dto.LoginDTO;
import cn.leafw.admin.service.UserService;
import cn.leafw.framework.dto.ResultDTO;
import cn.leafw.framework.security.UserContext;
import cn.leafw.framework.utils.ResultHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/**
 * 登陆controller
 *
 * @author <a href="mailto:wyr95626@95626.cn">CareyWYR</a>
 * @date 2019/12/17
 */
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * pc邮箱登陆
     * @param loginDTO
     * @return
     */
    @PostMapping("/login/email")
    public ResultDTO emailLogin(@RequestBody @Valid LoginDTO loginDTO){
        Map<String, String> userMap = userService.login(loginDTO);
        return ResultHelper.returnOk(userMap);
    }


    @GetMapping("/test")
    public ResultDTO test(){
        return ResultHelper.returnOk(UserContext.getUser().getData());
    }
}

