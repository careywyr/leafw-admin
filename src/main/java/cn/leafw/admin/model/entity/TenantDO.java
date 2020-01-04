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
 * 租户
 * </p>
 *
 * @author CareyWYR
 * @since 2019-05-30
 */
@Table(name = "leafw_tenant")
@Alias("tenant")
@Data
public class TenantDO {


    @Id
    @OrderBy("DESC")
    @GeneratedValue(generator = "JDBC")
    private Long tenantId;
    /**
     * 租户名称
     */
    private String tenantName;
    /**
     * 父级公司
     */
    private Long parentTenant;
    private Integer isdeleted;
    private Long created;
    private String createby;
    private Long updated;
    private String updateby;

}
