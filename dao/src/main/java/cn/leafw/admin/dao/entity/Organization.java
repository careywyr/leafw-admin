package cn.leafw.admin.dao.entity;

import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * <p>
 * 部门
 * </p>
 *
 * @author CareyWYR
 * @since 2019-05-30
 */
@TableName("leafw_organization")
public class Organization implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String orgName;
    private Long tenantId;
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

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
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
        return "Organization{" +
        ", id=" + id +
        ", orgName=" + orgName +
        ", tenantId=" + tenantId +
        ", isdeleted=" + isdeleted +
        ", created=" + created +
        ", createby=" + createby +
        ", updated=" + updated +
        ", updateby=" + updateby +
        "}";
    }
}
