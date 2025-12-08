package io.admin.framework.config.security;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.*;

/**
 * 权限检查注解，用于方法或类级别权限控制
 * 主要为了简化使用
 *
 * <p>该注解结合Spring Security使用，通过@PreAuthorize注解调用内置函数进行权限验证。
 * 支持继承性，可应用于方法和类型（类、接口）上。</p>
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@PreAuthorize("hasAuthority(#p0)") // 【核心】直接调用内置函数
public @interface HasPermission {

    /**
     * 需要检查的单个权限字符串，例如 "product:write"
     */
    String value();
}
