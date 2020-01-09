package cn.leafw.admin.service;

import cn.leafw.admin.mapper.PermissionMapper;
import cn.leafw.admin.model.entity.PermissionDO;
import cn.leafw.admin.model.vo.PermissionTreeVO;
import cn.leafw.admin.utils.TreeUtil;
import cn.leafw.framework.base.BaseServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 权限
 *
 * @author <a href="mailto:wyr95626@95626.cn">CareyWYR</a>
 * @date 2020/1/5
 */
@Service
public class PermissionService extends BaseServiceImpl<PermissionDO> {

    @Resource
    private PermissionMapper permissionMapper;

    public List<PermissionDO> selectByIdIn(Set<String> permissionIds){
        List<Long> permissionsIdList = new ArrayList<>();
        permissionIds.forEach(id -> permissionsIdList.add(Long.valueOf(id)));
        return permissionMapper.selectByIdIn(permissionsIdList);
    }

    public List<PermissionTreeVO> queryPermissionTree(){
        //todo 这里应该查询当前账号所拥有的权限树
        List<PermissionDO> allPermissions = this.selectAll();
        List<PermissionTreeVO> tempTree = new ArrayList<>();
        for (PermissionDO permission : allPermissions) {
            PermissionTreeVO permissionTreeVO = new PermissionTreeVO();
            BeanUtils.copyProperties(permission, permissionTreeVO);
            tempTree.add(permissionTreeVO);
        }
        return TreeUtil.buildByRecursive(tempTree);
    }

}

