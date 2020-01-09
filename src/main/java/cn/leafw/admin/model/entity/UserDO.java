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
 *
 * </p>
 *
 * @author CareyWYR
 * @since 2019-05-30
 */
@Table(name = "leafw_user")
@Alias("user")
@Data
public class UserDO {

    @Id
    @OrderBy("DESC")
    @GeneratedValue(generator = "JDBC")
    private Long userId;
    /**
     * 用户名
     */
    private String userName;

    private String email;

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
     * 所属角色列表
     */
    private Integer userStatus;
    private Long created;
    private String createby;
    private Long updated;
    private String updateby;
}
