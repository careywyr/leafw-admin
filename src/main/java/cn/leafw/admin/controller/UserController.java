package cn.leafw.admin.controller;


import cn.leafw.admin.constant.UserStatusEnum;
import cn.leafw.admin.model.entity.UserDO;
import cn.leafw.admin.model.vo.UserPermissionVO;
import cn.leafw.admin.model.vo.UserQueryVO;
import cn.leafw.admin.model.vo.UserVO;
import cn.leafw.admin.service.UserService;
import cn.leafw.framework.dto.ResultDTO;
import cn.leafw.framework.security.UserContext;
import cn.leafw.framework.utils.ResultHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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

    /**
     * 获取用户权限
     * @return UserPermissionVO
     */
    @GetMapping("/user/permission")
    public ResultDTO<UserPermissionVO> userPermission(){
        return ResultHelper.returnOk(userService.queryUserPermission(UserContext.getUser().getUserId()));
    }

    /**
     * 查询用户列表
     * @param userQueryVO  userQueryVO
     * @param pageNum pageNum
     * @param pageSize pageSize
     * @return PageInfo<UserVO>
     */
    @PostMapping("/user/list/{pageSize}/{pageNum}")
    public ResultDTO<PageInfo<UserVO>> queryUserList(@RequestBody UserQueryVO userQueryVO, @PathVariable("pageNum") Integer pageNum,
                                                     @PathVariable("pageSize") Integer pageSize){
        userQueryVO.setCurrentPage(pageNum);
        userQueryVO.setPageSize(pageSize);
        return ResultHelper.returnOk(userService.selectUserList(userQueryVO));
    }

    /**
     * 修改用户状态
     * @param userId userId
     * @return void
     */
    @GetMapping("/user/valid")
    public ResultDTO changeUserStatus(@RequestParam("userId") Long userId){
        UserDO userDO = userService.selectByPrimaryKey(userId);
        userDO.setUserStatus(UserStatusEnum.VALID.getId().equals(userDO.getUserStatus())?UserStatusEnum.INVALID.getId():UserStatusEnum.VALID.getId());
        userDO.setUpdated(System.currentTimeMillis()/1000L);
        userDO.setUpdateby(String.valueOf(UserContext.getUser().getUserId()));
        return ResultHelper.returnOk(userService.updateByPrimaryKey(userDO));
    }

    /**
     * 创建账号
     * @param userVO userVO
     * @return void
     */
    @PostMapping("/user")
    public ResultDTO createUser(@RequestBody UserVO userVO){
        userVO.setCreateby(String.valueOf(UserContext.getUser().getUserId()));
        userVO.setUpdateby(String.valueOf(UserContext.getUser().getUserId()));
        userService.createUser(userVO);
        return ResultHelper.returnOk(null);
    }

    /**
     * 更新用户
     * @param userVO userVO
     * @return void
     */
    @PutMapping("/user")
    public ResultDTO updateUser(@RequestBody UserVO userVO){
        userVO.setUpdateby(String.valueOf(UserContext.getUser().getUserId()));
        userService.updateUser(userVO);
        return ResultHelper.returnOk(null);
    }

    @DeleteMapping("/user")
    public ResultDTO deleteUser(@RequestParam("userId") Long userId){
        return ResultHelper.returnOk(userService.deleteByPrimaryKey(userId));
    }

}

