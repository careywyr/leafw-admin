package cn.leafw.admin.dao.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 租户
 * </p>
 *
 * @author CareyWYR
 * @since 2019-05-30
 */
@TableName("leafw_tenant")
public class Tenant implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public Long getParentTenant() {
        return parentTenant;
    }

    public void setParentTenant(Long parentTenant) {
        this.parentTenant = parentTenant;
    }

    public Integer getIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(Integer isdeleted) {
        this.isdeleted = isdeleted;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby;
    }

    public Long getUpdated() {
        return updated;
    }

    public void setUpdated(Long updated) {
        this.updated = updated;
    }

    public String getUpdateby() {
        return updateby;
    }

    public void setUpdateby(String updateby) {
        this.updateby = updateby;
    }

    @Override
    public String toString() {
        return "Tenant{" +
        ", id=" + id +
        ", tenantName=" + tenantName +
        ", parentTenant=" + parentTenant +
        ", isdeleted=" + isdeleted +
        ", created=" + created +
        ", createby=" + createby +
        ", updated=" + updated +
        ", updateby=" + updateby +
        "}";
    }
}
