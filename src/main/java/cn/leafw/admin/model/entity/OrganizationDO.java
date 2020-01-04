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
 * 部门
 * </p>
 *
 * @author CareyWYR
 * @since 2019-05-30
 */
@Table(name="leafw_organization")
@Alias("organization")
@Data
public class OrganizationDO {

    @Id
    @OrderBy("DESC")
    @GeneratedValue(generator = "JDBC")
    private Long orgId;
    private String orgName;
    private Long tenantId;
    private Integer isdeleted;
    private Long created;
    private String createby;
    private Long updated;
    private String updateby;
}
