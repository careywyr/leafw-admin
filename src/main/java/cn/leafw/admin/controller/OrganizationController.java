package cn.leafw.admin.controller;


import cn.leafw.admin.model.entity.OrganizationDO;
import cn.leafw.admin.service.OrganizationService;
import cn.leafw.framework.dto.ResultDTO;
import cn.leafw.framework.security.UserContext;
import cn.leafw.framework.utils.ResultHelper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 部门 前端控制器
 * </p>
 *
 * @author CareyWYR
 * @since 2019-05-30
 */
@RestController
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    /**
     * 组织列表
     * @param orgName orgName
     * @param pageNum pageNum
     * @param pageSize pageSize
     * @return 组织列表
     */
    @GetMapping("/org/list/{pageSize}/{pageNum}")
    public ResultDTO<PageInfo<OrganizationDO>> queryOrgList(@RequestParam(name="orgName", required = false) String orgName, @PathVariable("pageNum") Integer pageNum,
                                                        @PathVariable("pageSize") Integer pageSize){
        PageInfo<OrganizationDO> organizationDOS = organizationService.selectOrgList(pageNum, pageSize, orgName);
        return ResultHelper.returnOk(organizationDOS);
    }

    @GetMapping("/org/list/all")
    public ResultDTO queryOrgAll(){
        return ResultHelper.returnOk(organizationService.selectOrgList());
    }

    /**
     * 删除组织
     * @param orgId orgId
     */
    @DeleteMapping("/org/{orgId}")
    public ResultDTO<Integer> deleteOrg(@PathVariable("orgId") Long orgId){
        return ResultHelper.returnOk(organizationService.deleteByPrimaryKey(orgId));
    }

    /**
     * 新建组织
     * @param organizationDO organizationDO
     */
    @PostMapping("/org")
    public ResultDTO<Integer> saveOrg(@RequestBody OrganizationDO organizationDO){
        organizationDO.setCreated(System.currentTimeMillis()/1000L);
        organizationDO.setCreateby(String.valueOf(UserContext.getUser().getUserId()));
        organizationDO.setUpdated(System.currentTimeMillis()/1000L);
        organizationDO.setUpdateby(String.valueOf(UserContext.getUser().getUserId()));
        return ResultHelper.returnOk(organizationService.insert(organizationDO));
    }


    /**
     * 修改组织
     * @param organizationDO organizationDO
     */
    @PutMapping("/org")
    public ResultDTO<Integer> updateOrg(@RequestBody OrganizationDO organizationDO){
        organizationDO.setUpdated(System.currentTimeMillis()/1000L);
        organizationDO.setUpdateby(String.valueOf(UserContext.getUser().getUserId()));
        return ResultHelper.returnOk(organizationService.updateByPrimaryKey(organizationDO));
    }

}

