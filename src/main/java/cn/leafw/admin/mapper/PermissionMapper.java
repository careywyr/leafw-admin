package cn.leafw.admin.mapper;

import cn.leafw.admin.model.entity.PermissionDO;
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
public interface PermissionMapper extends BaseMapper<PermissionDO> {

    List<PermissionDO> selectByIdIn(@Param("permissionIds") List<Long> permissionIds);
}
