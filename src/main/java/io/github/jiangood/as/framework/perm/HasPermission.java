package io.github.jiangood.as.framework.perm;

import java.lang.annotation.*;

/***
 *  简化 security 的权限注解
 *  @HasPermission("job:read" )  相当于  @PreAuthorize("hasAuthority('job:read')")
  */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface HasPermission {

    String value();
}
