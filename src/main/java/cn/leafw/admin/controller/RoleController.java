package cn.leafw.admin.controller;


import cn.leafw.admin.model.entity.RoleDO;
import cn.leafw.admin.model.vo.RoleQueryVO;
import cn.leafw.admin.model.vo.RoleVO;
import cn.leafw.admin.service.RoleService;
import cn.leafw.framework.dto.ResultDTO;
import cn.leafw.framework.utils.ResultHelper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
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
     * @param roleQueryVO roleQueryVO
     * @param pageNum pageNum
     * @param pageSize pageSize
     * @return 角色列表
     */
    @PostMapping("/role/list/{pageSize}/{pageNum}")
    public ResultDTO<PageInfo<RoleVO>> queryRoleList(@RequestBody RoleQueryVO roleQueryVO, @PathVariable("pageNum") Integer pageNum,
                                                     @PathVariable("pageSize") Integer pageSize){
        PageInfo<RoleVO> roles = roleService.selectRoleList(pageNum, pageSize, roleQueryVO);
        return ResultHelper.returnOk(roles);
    }

    @GetMapping("/role/list/all")
    public ResultDTO queryAllRole(){
        return ResultHelper.returnOk(roleService.selectAll());
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

