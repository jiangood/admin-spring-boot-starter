package io.admin.framework.enums;

/**
 * 枚举代码接口
 * 所有需要支持通用转换的枚举都应实现此接口
 *
 * 例如
 * @AllArgsConstructor
 * @Getter
 * public enum OrgType implements CodeEnum {
 *     TYPE_UNIT(10, "单位"),
 *     TYPE_DEPT(20, "部门");
 *
 *     private final int code;
 *     private final String msg;
 * }
 *
 * @gendoc
 */
public interface CodeEnum {
    int getCode();
    String getMsg();
}
