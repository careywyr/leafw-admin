package cn.leafw.admin.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 用户权限
 *
 * @author <a href="mailto:wyr95626@95626.cn">CareyWYR</a>
 * @date 2020/1/5
 */
@Data
public class UserPermissionVO implements Serializable {

    private Long userId;

    private String userName;

    private List<String> roleIds;

    private List<PermissionTreeVO> permissions;

}

