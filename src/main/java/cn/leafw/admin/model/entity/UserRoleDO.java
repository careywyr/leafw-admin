package cn.leafw.admin.model.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import javax.persistence.Table;

/**
 * UserRoleDO
 *
 * @author <a href="mailto:wyr95626@95626.cn">CareyWYR</a>
 * @date 2020/1/8
 */
@Table(name = "leafw_user_role")
@Alias("userRole")
@Data
public class UserRoleDO {

    private Long userRoleId;
    private Long userId;
    private Long roleId;
    private Long created;
    private String createby;
    private Long updated;
    private String updateby;
}

