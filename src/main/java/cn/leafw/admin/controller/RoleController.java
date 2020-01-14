package cn.leafw.admin.controller;


import cn.leafw.admin.model.entity.RoleDO;
import cn.leafw.admin.model.vo.PermissionTreeVO;
import cn.leafw.admin.model.vo.RoleQueryVO;
import cn.leafw.admin.model.vo.RoleVO;
import cn.leafw.admin.service.PermissionService;
import cn.leafw.admin.service.RoleService;
import cn.leafw.framework.dto.ResultDTO;
import cn.leafw.framework.security.UserContext;
import cn.leafw.framework.utils.ResultHelper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @Autowired
    private PermissionService permissionService;

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

    /**
     * 所有角色
     * @return List<RoleDO>
     */
    @GetMapping("/role/list/all")
    public ResultDTO<List<RoleDO>> queryAllRole(){
        return ResultHelper.returnOk(roleService.selectAll());
    }

    /**
     * 删除角色
     * @param roleId roleId
     * @return Integer
     */
    @DeleteMapping("/role/{roleId}")
    public ResultDTO<Integer> deleteRole(@PathVariable("roleId") Long roleId){
        return ResultHelper.returnOk(roleService.deleteByPrimaryKey(roleId));
    }

    /**
     * 查询角色详情
     * @param roleId roleId
     * @return RoleVO
     */
    @GetMapping("/role/{roleId}")
    public ResultDTO<RoleVO> queryRoleDetail(@PathVariable("roleId") Long roleId){
        return ResultHelper.returnOk(roleService.queryRoleDetail(roleId));
    }

    @GetMapping("/permission/all")
    public ResultDTO<List<PermissionTreeVO>> queryPermissionTree(){
        return ResultHelper.returnOk(permissionService.queryPermissionTree());
    }


    @PostMapping("/role")
    public ResultDTO saveRole(@RequestBody RoleVO roleVO){
        RoleDO roleDO = new RoleDO();
        BeanUtils.copyProperties(roleVO, roleDO);
        roleDO.setCreated(System.currentTimeMillis()/1000L);
        roleDO.setCreateby(String.valueOf(UserContext.getUser().getUserId()));
        roleDO.setUpdated(System.currentTimeMillis()/1000L);
        roleDO.setUpdateby(String.valueOf(UserContext.getUser().getUserId()));
        roleService.insert(roleDO);
        return ResultHelper.returnOk(null);
    }
}

