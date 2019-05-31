package cn.leafw.admin.dao.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

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
@Data
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
}
