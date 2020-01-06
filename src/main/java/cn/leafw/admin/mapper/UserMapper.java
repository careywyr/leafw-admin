package cn.leafw.admin.mapper;

import cn.leafw.admin.model.entity.UserDO;
import cn.leafw.admin.model.vo.UserPermissionVO;
import tk.mybatis.mapper.common.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author CareyWYR
 * @since 2019-05-30
 */
public interface UserMapper extends BaseMapper<UserDO> {

    UserPermissionVO selectPermission(Long userId);
}
