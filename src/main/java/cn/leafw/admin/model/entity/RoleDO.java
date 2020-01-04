package cn.leafw.admin.model.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author CareyWYR
 * @since 2019-05-30
 */
@Table(name="leafw_role")
@Alias("role")
@Data
public class RoleDO {

    @Id
    @OrderBy("DESC")
    @GeneratedValue(generator = "JDBC")
    private Long roleId;
    /**
     * 角色名称
     */
    private String roleName;
    private Long orgId;
    private Integer isdeleted;
    private Long created;
    private String createby;
    private Long updated;
    private String updateby;

}
