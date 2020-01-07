package cn.leafw.admin.service;

import cn.leafw.admin.mapper.OrganizationMapper;
import cn.leafw.admin.model.entity.OrganizationDO;
import cn.leafw.framework.base.BaseServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 部门 服务类
 * </p>
 *
 * @author CareyWYR
 * @since 2019-05-30
 */
@Service
public class OrganizationService extends BaseServiceImpl<OrganizationDO> {

    @Resource
    private OrganizationMapper organizationMapper;

    /**
     * 查询组织列表
     * @param pageNum pageNum
     * @param pageSize pageSize
     * @param name name
     * @return Page<OrganizationDO>
     */
    public PageInfo<OrganizationDO> selectOrgList(Integer pageNum, Integer pageSize, String name){
        return PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() ->{organizationMapper.selectByName(name);});
    }


}
