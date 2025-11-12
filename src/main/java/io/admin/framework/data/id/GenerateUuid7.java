package io.admin.framework.data.id;

import io.admin.framework.data.id.impl.Uuid7Generator;
import org.hibernate.annotations.IdGeneratorType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 该注解可以放到类型上， id字段上。
 *
 * 如果实体存在父类， 可放到类型上覆盖
 *
 * TODO 再建一个IdGeneratorDesc注解放到类上， 本注解简化
 *
 */
@IdGeneratorType(Uuid7Generator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface GenerateUuid7 {


}
