package cn.leafw.admin.service;

import cn.leafw.admin.mapper.RoleMapper;
import cn.leafw.admin.model.entity.PermissionDO;
import cn.leafw.admin.model.entity.RoleDO;
import cn.leafw.admin.model.vo.PermissionTreeVO;
import cn.leafw.admin.model.vo.RoleQueryVO;
import cn.leafw.admin.model.vo.RoleVO;
import cn.leafw.admin.utils.TreeUtil;
import cn.leafw.framework.base.BaseServiceImpl;
import cn.leafw.framework.dto.BaseQueryDTO;
import cn.leafw.framework.exception.BusinessException;
import cn.leafw.framework.utils.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
    @Autowired
    private PermissionService permissionService;

    public List<RoleDO> selectByIdIn(List<String> roleIds){
        List<Long> roleIdList = new ArrayList<>();
        for (String roleId : roleIds) {
            roleIdList.add(Long.valueOf(roleId));
        }
        List<RoleDO> roleDOS = roleMapper.selectByIdIn(roleIdList);
        return roleDOS;
    }

    /**
     * 查询角色列表
     * @param roleQueryVO
     * @return Page<RoleDO>
     */
    public PageInfo<RoleVO> selectRoleList(Integer pageNum, Integer pageSize, RoleQueryVO roleQueryVO){
        return PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> {
            roleMapper.selectByOrgAndName(roleQueryVO);
        });
    }

    public List<RoleDO> selectAllRole(){
        return roleMapper.selectAll();
    }

    /**
     * 查询角色的权限树
     * @param roleId 角色id
     * @return 权限树
     */
    public List<PermissionTreeVO> queryRolePermissionTree(Long roleId){
        RoleDO roleDO = Optional.ofNullable(this.selectByPrimaryKey(roleId)).orElseThrow(() -> BusinessException.of("角色不存在! roleId="+roleId));
        String permissionIds = roleDO.getPermissionIds();
        //todo 这里应该查询当前账号所拥有的权限树
        List<PermissionDO> allPermissions = permissionService.selectAll();
        List<PermissionTreeVO> tempTree = new ArrayList<>();
        for (PermissionDO permission : allPermissions) {
            PermissionTreeVO permissionTreeVO = new PermissionTreeVO();
            BeanUtils.copyProperties(permission, permissionTreeVO);
            tempTree.add(permissionTreeVO);
        }
        if(StringUtils.isNotEmpty(permissionIds)){
            List<String> permissionIdArr = Arrays.asList(StringUtils.commaDelimitedListToStringArray(permissionIds));
            for (PermissionTreeVO permissionTreeVO : tempTree) {
                if(permissionIdArr.contains(String.valueOf(permissionTreeVO.getPermissionId()))){
                    permissionTreeVO.setChecked(true);
                }else{
                    permissionTreeVO.setChecked(false);
                }
            }
        }

        return TreeUtil.buildByRecursive(tempTree);
    }
}
