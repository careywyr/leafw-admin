package cn.leafw.admin.service.common.enums;

import java.util.Arrays;
import java.util.Optional;

/**
 * TODO
 *
 * @author <a href="mailto:wyr95626@95626.cn">CareyWYR</a>
 * @date 2019/5/31
 */
public enum LoginTypeEnum {

    /**
     *
     */
    USER_PWD(1, "用户名密码登陆"),
    PHONE(2, "手机验证码登陆"),
    OTHER(3, "其他方式登陆");

    private Integer id;
    private String name;

    LoginTypeEnum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getNameById(String id){
        Optional<LoginTypeEnum> optionalLoginTypeEnum = Arrays.asList(LoginTypeEnum.values()).stream()
                .filter(loginTypeEnum -> loginTypeEnum.getId().equals(id)).findFirst();
        return optionalLoginTypeEnum.isPresent()?optionalLoginTypeEnum.get().getName():null;
    }
}
