package cn.leafw.admin.constant;

/**
 * UserStatusEnum
 *
 * @author <a href="mailto:wyr95626@95626.cn">CareyWYR</a>
 * @date 2020/1/8
 */
public enum  UserStatusEnum {
    /**
     *
     */
    INVALID(0, "正常"),
    VALID(1, "删除");

    private Integer id;
    private String name;

    private UserStatusEnum(Integer id, String name) {
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
}

