package cn.leafw.admin.mapper;

import cn.leafw.admin.model.entity.OrganizationDO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

/**
 * <p>
 * 部门 Mapper 接口
 * </p>
 *
 * @author CareyWYR
 * @since 2019-05-30
 */
public interface OrganizationMapper extends BaseMapper<OrganizationDO> {

    List<OrganizationDO> selectByName(@Param("orgName") String orgName);

}
