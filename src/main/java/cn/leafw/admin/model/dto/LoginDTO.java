package cn.leafw.admin.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 登陆dto
 *
 * @author <a href="mailto:wyr95626@95626.cn">CareyWYR</a>
 * @date 2019/12/17
 */
@Data
public class LoginDTO {

    @NotNull
    private String email;
    @NotNull
    private String password;

}

