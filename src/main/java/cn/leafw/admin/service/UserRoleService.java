package cn.leafw.admin.service;

import cn.leafw.admin.mapper.UserRoleMapper;
import cn.leafw.admin.model.entity.UserRoleDO;
import cn.leafw.framework.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * TODO
 *
 * @author <a href="mailto:wyr95626@95626.cn">CareyWYR</a>
 * @date 2020/1/8
 */
@Service
public class UserRoleService extends BaseServiceImpl<UserRoleDO> {

    @Resource
    private UserRoleMapper userRoleMapper;

    public void saveUserRole(String[] roleIds, Long userId, String createby){
        for (String roleId : roleIds) {
            UserRoleDO userRoleDO = new UserRoleDO();
            userRoleDO.setUserId(userId);
            userRoleDO.setRoleId(Long.valueOf(roleId));
            userRoleDO.setCreated(System.currentTimeMillis()/1000L);
            userRoleDO.setCreateby(createby);
            userRoleDO.setUpdated(System.currentTimeMillis()/1000L);
            userRoleDO.setUpdateby(createby);
            userRoleMapper.insert(userRoleDO);
        }
    }
}

