# 用户权限管理

本文档介绍了 open-admin 系统中的用户权限管理功能。

## 功能概述

用户权限管理是 open-admin 系统的核心功能之一，提供了完整的用户、角色、权限体系，确保系统的安全性和可管理性。

## 核心概念

### 1. 用户

用户是系统的基本访问单位，每个用户都有唯一的账号和密码，以及对应的角色。

### 2. 角色

角色是权限的集合，通过为用户分配角色，可以快速为用户授予一组权限。

### 3. 权限

权限是对系统资源的访问控制，包括菜单权限、按钮权限、API权限等。

## 功能详解

### 1. 用户管理

#### 1.1 用户列表

- **功能**：展示系统中的所有用户，支持分页、筛选、搜索
- **操作**：查看、编辑、删除、重置密码、授权数据

#### 1.2 用户创建

- **功能**：创建新用户
- **字段**：用户名、密码、姓名、邮箱、电话、状态、角色

#### 1.3 用户编辑

- **功能**：修改用户信息
- **字段**：姓名、邮箱、电话、状态、角色

#### 1.4 重置密码

- **功能**：重置用户密码为默认值
- **默认密码**：123456

### 2. 角色管理

#### 2.1 角色列表

- **功能**：展示系统中的所有角色，支持分页、筛选、搜索
- **操作**：查看、编辑、删除、分配权限

#### 2.2 角色创建

- **功能**：创建新角色
- **字段**：角色名称、角色编码、描述

#### 2.3 角色编辑

- **功能**：修改角色信息
- **字段**：角色名称、描述

#### 2.4 分配权限

- **功能**：为角色分配菜单和按钮权限
- **权限类型**：菜单权限、按钮权限

### 3. 权限管理

#### 3.1 权限列表

- **功能**：展示系统中的所有权限，支持分页、筛选、搜索
- **权限来源**：系统自动从代码中扫描生成

## 技术实现

### 后端实现

1. **实体类**：`User.java`、`Role.java`、`Permission.java`
2. **服务类**：`UserService.java`、`RoleService.java`、`PermissionService.java`
3. **权限控制**：基于注解的权限控制，如 `@RequiresPermissions`

### 前端实现

1. **页面组件**：`user/index.jsx`、`role/index.jsx`、`permission/index.jsx`
2. **权限判断**：使用 `perm` 属性判断按钮权限
3. **菜单权限**：基于用户角色的菜单过滤

## 使用示例

### 1. 后端权限控制

```java
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/admin/user")
public class UserController {

    @RequiresPermissions("user:view")
    @GetMapping("/list")
    public Result list() {
        // 实现逻辑
    }

    @RequiresPermissions("user:save")
    @PostMapping("/save")
    public Result save(@RequestBody User user) {
        // 实现逻辑
    }

    @RequiresPermissions("user:delete")
    @GetMapping("/delete")
    public Result delete(@RequestParam Long id) {
        // 实现逻辑
    }
}
```

### 2. 前端权限判断

```jsx
import {Button, Popconfirm} from 'antd';
import {ButtonList} from "@jiangood/open-admin";

// 按钮权限判断
<ButtonList>
    <Button size='small' perm='user:save' onClick={() => this.handleEdit(record)}>编辑</Button>
    <Popconfirm perm='user:delete' title='是否确定删除用户信息' onConfirm={() => this.handleDelete(record)}>
        <Button size='small'>删除</Button>
    </Popconfirm>
</ButtonList>
```

## 最佳实践

1. **权限设计**：根据最小权限原则，为用户分配必要的权限
2. **角色管理**：按照功能模块创建角色，如管理员、运营人员、普通用户
3. **权限同步**：当系统功能变更时，及时更新权限配置
4. **定期检查**：定期检查用户权限，确保权限分配合理

## 常见问题

### 1. 用户登录失败

- **原因**：用户名或密码错误，用户状态为禁用
- **解决方法**：检查用户名密码，联系管理员启用用户

### 2. 权限不足

- **原因**：用户或角色没有对应的权限
- **解决方法**：联系管理员为用户分配相应的权限

### 3. 菜单不显示

- **原因**：用户没有菜单对应的权限
- **解决方法**：联系管理员为用户分配菜单权限