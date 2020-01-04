package cn.leafw.admin.model.dto;

import cn.leafw.framework.dto.BaseQueryDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author CareyWYR
 * @since 2019-05-30
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserQueryDto extends BaseQueryDTO {

    private Long id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 所属租户
     */
    private Long tenantId;
    /**
     * 所属部门
     */
    private Long orgId;
    /**
     * 所属角色列表
     */
    private String roleIds;
    private Integer isdeleted;
    private Long created;
    private String createby;
    private Long updated;
    private String updateby;

}
