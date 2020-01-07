package cn.leafw.admin.mapper;

import cn.leafw.admin.model.entity.RoleDO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author CareyWYR
 * @since 2019-05-30
 */
public interface RoleMapper extends BaseMapper<RoleDO> {

    List<RoleDO> selectByIdIn(@Param("roleIds") List<Long> roleIds);

    List<RoleDO> selectByName(@Param("roleName") String roleName);

}
