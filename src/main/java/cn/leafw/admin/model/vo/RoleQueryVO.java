package cn.leafw.admin.model.vo;

import cn.leafw.framework.dto.BaseQueryDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * RoleQueryVO
 *
 * @author <a href="mailto:wyr95626@95626.cn">CareyWYR</a>
 * @date 2020/1/8
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RoleQueryVO extends BaseQueryDTO {

    private String roleName;

    private Long orgId;
}

