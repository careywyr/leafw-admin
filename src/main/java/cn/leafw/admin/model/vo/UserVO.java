package cn.leafw.admin.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author CareyWYR
 * @since 2019-05-30
 */
@Data
public class UserVO {

    private Long userId;
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
    private Integer userStatus;
    /**
     * 所属部门
     */
    private Long orgId;
    private String orgName;
    /**
     * 所属角色列表
     */
    private List<String> roleIds;
    private String roleNames;
    private Long created;
    private String createby;
    private Long updated;
    private String updateby;

}
