package cn.leafw.admin.model.vo;

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
public class UserQueryVO extends BaseQueryDTO {

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
     * 所属部门
     */
    private Long orgId;
    /**
     * 所属角色
     */
    private Long roleId;

}
