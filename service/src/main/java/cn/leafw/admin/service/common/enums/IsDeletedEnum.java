package cn.leafw.admin.service.common.enums;

/**
 * @author carey
 * @date 2019/3/26
 */
public enum IsDeletedEnum {

    /**
     *
     */
    UNDELETE("0", "正常"),
    DELETE("1", "删除");

    private String id;
    private String name;

    private IsDeletedEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
