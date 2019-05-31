package cn.leafw.admin.dao.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

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
@Data
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

}
