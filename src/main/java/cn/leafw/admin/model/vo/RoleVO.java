package cn.leafw.admin.model.vo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 * <p>
 * 
 * </p>
 *
 * @author CareyWYR
 * @since 2019-05-30
 */
@Data
public class RoleVO {

    private Long roleId;
    /**
     * 角色名称
     */
    private String roleName;
    private Long orgId;
    private String orgName;
    private Long tenantId;
    private String permissionIds;
    private Integer isdeleted;
    private Long created;
    private String createby;
    private Long updated;
    private String updateby;

}
