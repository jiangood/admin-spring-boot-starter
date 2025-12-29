package io.github.jiangood.sa.framework.enums;

import java.lang.annotation.*;

// TODO 枚举类增加这个注解
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface EnumIntValue {

    int value();
}
