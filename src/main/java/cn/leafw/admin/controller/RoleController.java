package cn.leafw.admin.controller;


import cn.leafw.admin.model.entity.RoleDO;
import cn.leafw.admin.service.RoleService;
import cn.leafw.framework.dto.ResultDTO;
import cn.leafw.framework.utils.ResultHelper;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author CareyWYR
 * @since 2019-05-30
 */
@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 角色列表
     * @param roleName roleName
     * @param pageNum pageNum
     * @param pageSize pageSize
     * @return 角色列表
     */
    @GetMapping("/role/list/{pageSize}/{pageNum}")
    public ResultDTO<Page<RoleDO>> queryRoleList(@RequestParam(name="roleName", required = false) String roleName, @PathVariable("pageNum") Integer pageNum,
                                                 @PathVariable("pageSize") Integer pageSize){
        Page<RoleDO> roles = roleService.selectRoleList(pageNum, pageSize, roleName);
        return ResultHelper.returnOk(roles);
    }

    /**
     * 删除角色
     * @param roleId roleId
     * @return
     */
    @DeleteMapping("/role/{roleId}")
    public ResultDTO<Integer> deleteRole(@PathVariable("roleId") Long roleId){
        return ResultHelper.returnOk(roleService.deleteByPrimaryKey(roleId));
    }

    @GetMapping("/role/{roleId}")
    public ResultDTO queryRoleDetail(@PathVariable("roleId") Long roleId){

        return ResultHelper.returnOk(null);
    }

}

