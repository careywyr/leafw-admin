package cn.leafw.admin.service;

import cn.leafw.admin.mapper.RoleMapper;
import cn.leafw.admin.model.entity.RoleDO;
import cn.leafw.framework.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CareyWYR
 * @since 2019-05-30
 */
@Service
public class RoleService extends BaseServiceImpl<RoleDO> {

    @Resource
    private RoleMapper roleMapper;

    public List<RoleDO> selectByIdIn(String[] roleIds){
        List<Long> roleIdList = new ArrayList<>();
        for (String roleId : roleIds) {
            roleIdList.add(Long.valueOf(roleId));
        }
        List<RoleDO> roleDOS = roleMapper.selectByIdIn(roleIdList);
        return roleDOS;
    }
}
