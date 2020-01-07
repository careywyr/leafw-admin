package cn.leafw.admin.model.vo;

import lombok.Data;

import java.util.List;

/**
 * TODO
 *
 * @author <a href="mailto:wyr95626@95626.cn">CareyWYR</a>
 * @date 2020/1/5
 */
@Data
public class PermissionTreeVO {

    private Long permissionId;

    private String permissionName;

    private Integer permissionType;

    private String permissionKey;

    private Boolean checked;

    private Long tenantId;

    private Long parentId;

    private List<PermissionTreeVO> childPermission;
}

