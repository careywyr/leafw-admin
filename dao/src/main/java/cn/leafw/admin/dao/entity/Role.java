package cn.leafw.admin.dao.entity;

import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author CareyWYR
 * @since 2019-05-30
 */
@TableName("leafw_role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
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
        return "Role{" +
        ", id=" + id +
        ", roleName=" + roleName +
        ", orgId=" + orgId +
        ", isdeleted=" + isdeleted +
        ", created=" + created +
        ", createby=" + createby +
        ", updated=" + updated +
        ", updateby=" + updateby +
        "}";
    }
}
