package cn.leafw.admin.model.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import javax.persistence.Table;

/**
 * 权限
 *
 * @author <a href="mailto:wyr95626@95626.cn">CareyWYR</a>
 * @date 2020/1/5
 */
@Table(name="leafw_permission")
@Alias("permission")
@Data
public class PermissionDO {

    private Long permissionId;

    private String permissionName;

    private Integer permissionType;

    private String permissionKey;

    private Long tenantId;

    private Long parentId;

    private Integer isdeleted;
    private Long created;
    private String createby;
    private Long updated;
    private String updateby;
}

