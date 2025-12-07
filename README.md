# 1 介绍

本项目是一个后端管理系统的框架，供业务项目使用。它提供了一套完整的后端管理解决方案，包括用户管理、权限控制、数据字典、文件上传、作业调度、工作流等功能模块，旨在帮助开发者快速搭建企业级后台管理系统。

## 开发环境
- Java 17 及以上版本
- Maven 3.5+ 
- Node.js (用于前端开发)
- MySQL 8.0+ (或兼容数据库)

本项目基于Spring Boot 3.5.8构建，集成了多种主流技术栈，包括Spring Security、Spring Data JPA、Quartz、Flowable等。

说明后端最新版本为： ![Maven Version](https://img.shields.io/maven-central/v/io.github.jiangood/springboot-admin-starter)

说明前端最新版本为： ![NPM Version](https://img.shields.io/npm/v/@jiangood/springboot-admin-starter)

## 后端依赖

- `spring-boot-starter-web`: 核心Web功能支持
- `spring-boot-starter-quartz`: 作业调度功能
- `spring-boot-starter-validation`: 数据验证功能
- `spring-boot-starter-aop`: 面向切面编程支持
- `spring-boot-starter-data-jpa`: 数据持久化支持
- `spring-boot-starter-cache`: 缓存功能支持
- `spring-boot-starter-security`: 安全认证授权
- `mapstruct`: 对象映射工具
- `com.jhlabs:filters`: 图像处理滤镜
- `io.minio:minio`: 对象存储服务客户端
- `javax.mail:mail`: 邮件发送功能
- `org.apache.poi:poi-ooxml`: Excel文件处理
- `org.apache.poi:poi-scratchpad`: 文档处理
- `com.itextpdf:itextpdf`: PDF文件处理
- `com.github.f4b6a3:uuid-creator`: UUID生成器
- `commons-dbutils:commons-dbutils`: 数据库操作工具
- `cn.hutool:hutool-all`: Java工具集
- `org.apache.commons:commons-lang3`: Apache工具集
- `com.google.guava:guava`: Google工具集
- `commons-io:commons-io`: IO操作工具
- `org.jsoup:jsoup`: HTML解析工具
- `org.flowable:flowable-spring-boot-starter-process`: 工作流引擎支持
- `lombok`: 代码简化工具
- `mysql:mysql-connector-j`: MySQL数据库驱动

## 前端依赖（不含开发依赖）

- `@ant-design/icons`: Ant Design图标库，版本 5.4.0
- `antd`: Ant Design UI组件库，版本 6.0.0
- `antd-img-crop`: 图片裁剪组件，版本 4.23.0
- `axios`: HTTP客户端，版本 1.13.2
- `bpmn-js`: 工作流图编辑器，版本 18.7.0
- `dayjs`: 日期处理库，版本 1.11.13
- `jsencrypt`: RSA加密库，版本 3.5.4
- `lodash`: JavaScript工具库，版本 4.17.21
- `qs`: 查询字符串解析库，版本 6.14.0
- `react`: React框架，版本 19.0.0
- `react-dom`: React DOM渲染器，版本 19.0.0
- `umi`: React应用框架，版本 4.0.0

# 2 前端

框架提供了一套完整的前端组件库，用于快速构建管理界面。导入相关文件时，使用模块名 `@jiangood/springboot-admin-starter` 即可。

## 2.1 组件库文档

| 组件名称 | 参数 | 说明 |
|---------|------|------|
| `ProTable` | `request`: 数据请求函数<br>`columns`: 列定义<br>`actionRef`: 表格操作引用<br>`toolBarRender`: 工具栏渲染函数<br>`rowKey`: 行唯一标识字段名<br>`defaultPageSize`: 默认每页大小<br>`showToolbarSearch`: 是否显示工具栏搜索<br>`scrollY`: 表格垂直滚动高度 | 高级表格组件，支持分页、排序、搜索功能 |
| `ProModal` | `title`: 模态框标题<br>`actionRef`: 模态框操作引用<br>`onShow`: 显示时回调<br>`width`: 模态框宽度<br>`children`: 子节点 | 高级模态框组件，提供显示/隐藏方法 |
| `FieldDictSelect` | `value`: 当前值<br>`onChange`: 值改变回调<br>`typeCode`: 字典类型代码 | 字典选择组件，根据字典类型显示选项 |
| `FieldRemoteSelect` | `value`: 当前值<br>`onChange`: 值改变回调<br>`url`: 远程搜索接口地址<br>`placeholder`: 占位符 | 远程搜索选择组件，支持模糊搜索 |
| `FieldRemoteSelectMultiple` | `value`: 当前值<br>`onChange`: 值改变回调<br>`url`: 远程搜索接口地址<br>`placeholder`: 占位符 | 远程多选搜索组件 |
| `FieldRemoteTree` | `value`: 当前值<br>`onChange`: 值改变回调<br>`url`: 远程树数据接口地址 | 远程树形选择组件 |
| `FieldRemoteTreeSelect` | `value`: 当前值<br>`onChange`: 值改变回调<br>`url`: 远程树数据接口地址 | 远程树选择组件 |
| `FieldUploadFile` | `value`: 当前值<br>`onChange`: 值改变回调<br>`accept`: 允许上传的文件类型<br>`maxCount`: 最大文件数量 | 文件上传组件，支持单/多文件上传 |
| `LoginPage` | 无 | 登录页面组件 |
| `Ellipsis` | `text`: 显示文本<br>`length`: 最大长度<br>`tooltip`: 是否显示提示框 | 省略文本组件，超长文本截取并显示省略号 |
| `LinkButton` | `to`: 跳转地址<br>`children`: 子节点 | 链接按钮组件，用于页面间跳转 |

### 2.2 字段组件

| 组件名称 | 参数 | 说明 |
|---------|------|------|
| `FieldBoolean` | `value`: 当前值<br>`onChange`: 值改变回调 | 布尔值选择组件，显示为开关 |
| `FieldDate` | `value`: 当前值<br>`onChange`: 值改变回调 | 日期选择组件 |
| `FieldDateRange` | `value`: 当前值<br>`onChange`: 值改变回调 | 日期范围选择组件 |
| `FieldEditor` | `value`: 当前值<br>`onChange`: 值改变回调 | 富文本编辑器组件 |
| `FieldPercent` | `value`: 当前值<br>`onChange`: 值改变回调 | 百分比输入组件 |
| `FieldTable` | `value`: 当前值<br>`onChange`: 值改变回调<br>`columns`: 表格列定义 | 表格输入组件，用于嵌套对象数组编辑 |
| `FieldTableSelect` | `value`: 当前值<br>`onChange`: 值改变回调<br>`url`: 数据接口地址<br>`columns`: 表格列定义 | 表格选择组件，通过表格选择数据 |

### 2.3 工具函数

| 函数名称 | 参数 | 说明 |
|---------|------|------|
| `ArrUtils` | | 数组操作工具 |
| `ColorsUtils` | | 颜色处理工具 |
| `DateUtils` | | 日期时间处理工具 |
| `DeviceUtils` | | 设备检测工具 |
| `DomUtils` | | DOM操作工具 |
| `EventBusUtils` | | 事件总线工具 |
| `MessageUtils` | | 消息提示工具 |
| `ObjectUtils` | | 对象操作工具 |
| `StorageUtils` | | 本地存储工具 |
| `StringUtils` | | 字符串操作工具 |
| `TreeUtils` | | 树形结构处理工具 |
| `UrlUtils` | | URL处理工具 |
| `UuidUtils` | | UUID生成工具 |
| `ValidateUtils` | | 数据验证工具 |

## 2.4 后端

### 2.4.1 菜单列表

框架提供了预定义的菜单结构，业务项目可以在 `application-data-biz.yml` 中进行自定义配置。默认菜单包括：

- **我的任务** (`myFlowableTask`)
  - 路径: `/flowable/task`
  - 权限: `myFlowableTask:manage`

- **系统管理** (`sys`)
  - **机构管理** (`sysOrg`)
    - 路径: `/system/org`
    - 权限: `sysOrg:save`, `sysOrg:view`, `sysOrg:delete`
  - **用户管理** (`sysUser`)
    - 路径: `/system/user`
    - 权限: `sysUser:view`, `sysUser:save`, `sysUser:delete`, `sysUser:resetPwd`, `sysUser:grantPerm`
  - **角色管理** (`sysRole`)
    - 路径: `/system/role`
    - 权限: `sysRole:save`
  - **操作手册** (`sysManual`)
    - 路径: `/system/sysManual`
    - 权限: `sysManual:view`, `sysManual:delete`, `sysManual:save`
  - **系统设置** (`sysConfig`)
    - 路径: `/system/config`
    - 权限: `sysConfig:view`, `sysConfig:save`
  - **数据字典** (`sysDict`)
    - 路径: `/system/dict`
    - 权限: `sysDict:view`, `sysDict:save`, `sysDict:delete`
  - **存储文件** (`sysFile`)
    - 路径: `/system/file`
    - 权限: `sysFile:view`, `sysFile:delete`
  - **作业调度** (`job`)
    - 路径: `/system/job`
    - 权限: `job:view`, `job:save`, `job:triggerJob`, `job:jobLogClean`
  - **操作日志** (`sysLog`)
    - 路径: `/system/log`
    - 权限: `sysLog:view`
  - **接口管理** (`api`)
    - 路径: `/system/api`
    - 权限: `api`
  - **流程引擎** (`flowableModel`)
    - 路径: `/flowable`
    - 权限: `flowableModel:design`, `flowableModel:deploy`, `flowableTask:setAssignee`, `flowableInstance:close`
  - **报表管理** (`ureport`)
    - 路径: `/ureport`
    - 权限: `ureport:view`, `ureport:design`

### 2.4.2 业务项目配置

业务项目可以通过创建 `application-data-biz.yml` 文件来定义自己的菜单、配置和流程。配置主要包括：

1. **菜单配置**：定义业务项目的菜单结构
2. **系统配置**：定义业务项目的系统设置项
3. **流程配置**：定义业务项目的流程定义

业务项目需要在 `application-data-biz.yml` 中配置菜单结构，格式与框架默认菜单相同。

## 2.5 核心工具类

### 2.5.1 JPA Specification 构建器 (Spec.java)

`Spec.java` 是一个简洁、动态、支持关联字段查询的 JPA Specification 构建器。

```java
// 核心功能：通过链式调用收集 Specification，最终使用 AND 逻辑连接所有条件
public class Spec<T> implements Specification<T> {
    // 等于条件
    public Spec<T> eq(String field, Object value)
    
    // 不等于条件
    public Spec<T> ne(String field, Object value)
    
    // 大于条件
    public <C extends Comparable<C>> Spec<T> gt(String field, C value)
    
    // 小于条件
    public <C extends Comparable<C>> Spec<T> lt(String field, C value)
    
    // 大于等于条件
    public <C extends Comparable<C>> Spec<T> ge(String field, C value)
    
    // 小于等于条件
    public <C extends Comparable<C>> Spec<T> le(String field, C value)
    
    // 模糊匹配
    public Spec<T> like(String field, String value)
    
    // 左模糊匹配
    public Spec<T> leftLike(String field, String value)
    
    // 右模糊匹配
    public Spec<T> rightLike(String field, String value)
    
    // 不匹配
    public Spec<T> notLike(String field, String value)
    
    // 包含条件
    public Spec<T> in(String field, Collection<?> values)
    
    // 范围条件
    public <C extends Comparable<C>> Spec<T> between(String field, C value1, C value2)
    
    // 非空条件
    public Spec<T> isNotNull(String field)
    
    // 为空条件
    public Spec<T> isNull(String field)
    
    // 逻辑或条件
    public final Spec<T> or(Specification<T>... orSpecifications)
    
    // 逻辑非条件
    public Spec<T> not(Specification<T> spec)
    
    // 多字段或模糊查询
    public Spec<T> orLike(String value, String... fields)
    
    // 分组条件
    public Spec<T> groupBy(String... fields)
    
    // 聚合查询
    public Spec<T> selectFnc(AggregateFunction fn, String field)
    
    // 字段选择
    public Spec<T> select(String... fields)
}
```

使用示例：
```java
// 构建查询条件
Spec<User> spec = Spec.of()
    .like("username", "admin")
    .eq("status", 1)
    .ge("age", 18)
    .between("createTime", startDate, endDate);

List<User> users = userService.findAll(spec);
```

### 2.5.2 树结构处理工具 (TreeUtils.java)

`TreeUtils.java` 提供了树结构数据的构建、遍历、转换等功能。

```java
public class TreeUtils {
    // 构建树结构
    public static <E> List<E> buildTree(List<E> list, 
                                        Function<E, String> keyFn, 
                                        Function<E, String> pkeyFn, 
                                        Function<E, List<E>> getChildren, 
                                        BiConsumer<E, List<E>> setChildren)
    
    // 将树结构转换为Map
    public static <E> Map<String, E> treeToMap(List<E> tree, 
                                              Function<E, String> keyFn, 
                                              Function<E, List<E>> getChildren)
    
    // 递归遍历树
    public static <E> void walk(List<E> list, 
                               Function<E, List<E>> getChildren, 
                               Consumer<E> consumer)
    
    // 获取树的叶子节点
    public static <E> List<E> getLeafs(List<E> list, 
                                      Function<E, List<E>> getChildren)
    
    // 将树结构转换为列表
    public static <E> List<E> treeToList(List<E> tree, 
                                        Function<E, List<E>> getChildren)
    
    // 获取节点的父节点路径
    public static <E> List<String> getPids(String nodeId, List<E> list, 
                                           Function<E, String> keyFn, 
                                           Function<E, String> pkeyFn)
}
```

### 2.5.3 注解描述工具 (RemarkUtils.java)

`RemarkUtils.java` 提供了从注解获取描述信息的功能。

```java
public class RemarkUtils {
    // 获取字段的Remark注解值
    public static String getRemark(Field field)
    
    // 获取类的Remark注解值
    public static String getRemark(Class<?> t)
    
    // 获取枚举的Remark注解值
    public static String getRemark(Enum<?> t)
    
    // 获取方法的Remark注解值
    public static String getRemark(Method method)
}
```

### 2.5.4 Excel导入导出工具 (ExcelUtils.java)

`ExcelUtils.java` 提供了Excel文件的导入导出功能。

```java
public class ExcelUtils {
    // 导入Excel文件
    public static <T> List<T> importExcel(Class<T> cls, InputStream is) throws Exception
    
    // 导出Excel文件
    public static <T> void exportExcel(Class<T> cls, List<T> list, OutputStream os) throws Exception
}
```

使用示例：
```java
// 导入
List<User> users = ExcelUtils.importExcel(User.class, inputStream);

// 导出
ExcelUtils.exportExcel(User.class, users, outputStream);
```

### 2.5.5 原生SQL工具类 (JdbcUtils.java)

`JdbcUtils.java` 基于Spring的JdbcTemplate封装，提供复杂的原生SQL查询和更新功能。

```java
@Component
public class JdbcUtils {
    // 执行更新操作
    public int update(String sql, Object... params)
    
    // 查询单条记录
    public <T> T findOne(Class<T> cls, String sql, Object... params)
    
    // 查询多条记录
    public <T> List<T> findAll(Class<T> cls, String sql, Object... params)
    
    // 查询单个标量值
    public Object findScalar(String sql, Object... params)
    
    // 分页查询
    public <T> Page<T> findAll(Class<T> cls, Pageable pageable, String sql, Object... params)
    
    // 动态插入
    public int insert(String tableName, Object bean)
    
    // 根据ID更新
    public int updateById(String table, Object bean)
    
    // 检查记录是否存在
    public boolean exists(String sql, Object... params)
    
    // 获取表名
    public Set<String> getTableNames()
    
    // 检查表是否存在
    public boolean tableExists(String tableName)
    
    // 检查列是否存在
    public boolean columnExists(String tableName, String columnName)
}
```

### 2.5.6 ID生成工具

框架提供了多种ID生成策略：

#### 2.5.6.1 UUID V7 生成器
```java
// 使用注解生成按时间排序的UUID
@GenerateUuidV7
private String id;
```

#### 2.5.6.2 带前缀序列生成器
```java
// 使用注解生成带前缀的序列ID
@GeneratePrefixedSequence(prefix = "ORDER_")
private String orderNo;
```

### 2.5.7 类型转换器

框架提供了丰富的类型转换器，用于处理复杂的数据类型转换：

- `BaseCodeEnumConverter`: 枚举代码转换器
- `ToListConverter`: 列表转换器
- `ToMapConverter`: Map转换器
- `ToIntListConverter`: 整数列表转换器
- `ToLongListConverter`: 长整数列表转换器
- `ToBigDecimalListConverter`: 大数列表转换器
- 等等...

### 2.5.8 验证器

框架提供了多种验证器，用于数据验证：

- `@ValidateMobile`: 手机号验证
- `@ValidateIdNum`: 身份证号验证
- `@ValidateCarDrivingLicence`: 驾驶证号验证
- `@ValidateChineseName`: 中文姓名验证
- `@ValidateCreditCode`: 统一社会信用代码验证
- `@ValidatePassword`: 密码验证
- `@ValidateZipCode`: 邮政编码验证
- 等等...

### 2.5.9 登录工具 (LoginUtils.java)

`LoginUtils.java` 提供了获取当前登录用户信息的功能。

```java
public class LoginUtils {
    // 获取当前登录用户ID
    public static String getUserId()
    
    // 获取当前登录用户对象
    public static LoginUser getUser()
    
    // 获取当前登录用户的组织权限
    public static List<String> getOrgPermissions()
    
    // 获取当前登录用户的权限
    public static List<String> getPermissions()
    
    // 获取当前登录用户的角色
    public static List<String> getRoles()
    
    // 判断是否为管理员
    public static boolean isAdmin()
}
```

### 2.5.10 作业调度

框架集成了Quartz作业调度功能。

#### 2.5.10.1 基础作业类
```java
// 继承BaseJob创建自定义作业
public abstract class BaseJob implements Job {
    // 执行作业的核心方法
    public abstract String execute(JobDataMap data, Logger logger) throws Exception;
}
```

#### 2.5.10.2 作业描述注解
```java
// 使用JobDescription注解定义作业信息
@JobDescription(label = "作业名称", 
                params = {@FieldDescription(name = "name", label = "参数名称")})
public class CustomJob extends BaseJob {
    @Override
    public String execute(JobDataMap data, Logger logger) throws Exception {
        // 实现作业逻辑
        return "OK";
    }
}
```

### 2.5.11 工作流

框架集成了Flowable工作流引擎，用于处理复杂的业务流程。

#### 2.5.11.1 流程监听器
```java
// 实现ProcessListener接口处理流程事件
@Component
public class CustomProcessListener implements ProcessListener {
    @Override
    public void onProcessEvent(FlowableEventType type, String initiator, 
                              String businessKey, Map<String, Object> variables) {
        // 处理流程事件
    }
}
```

# 3 模板代码

以下是一个学生管理功能的完整代码模板，包含实体类、数据访问层、业务逻辑层、控制器以及前端页面。

## 3.1 实体类 (Student.java)

```java
package io.admin.modules.system.entity;

import io.admin.common.utils.annotation.Remark;
import io.admin.framework.data.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

@Remark("学生")
@Entity
@Getter
@Setter
@Table
@FieldNameConstants
public class Student extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Remark("姓名")
    @Column(length = 50)
    @Size(max = 50, message = "姓名长度不能超过50个字符")
    private String name;

    @Remark("年龄")
    private Integer age;

    @Remark("性别")
    @Column(length = 10)
    @Size(max = 10, message = "性别长度不能超过10个字符")
    private String gender;

    @Remark("班级")
    @Column(length = 50)
    @Size(max = 50, message = "班级长度不能超过50个字符")
    private String className;

    @Remark("联系电话")
    @Column(length = 20)
    @Size(max = 20, message = "联系电话长度不能超过20个字符")
    private String phone;

    @Remark("邮箱")
    @Column(length = 100)
    @Size(max = 100, message = "邮箱长度不能超过100个字符")
    private String email;
}
```

## 3.2 数据访问层 (StudentDao.java)

```java
package io.admin.modules.system.dao;

import io.admin.framework.data.repository.BaseDao;
import io.admin.framework.data.specification.Spec;
import io.admin.modules.system.entity.Student;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDao extends BaseDao<Student> {
    
    public Student findByName(String name) {
        Spec<Student> spec = Spec.of().eq(Student.Fields.name, name);
        return this.findOne(spec);
    }
}
```

## 3.3 业务逻辑层 (StudentService.java)

```java
package io.admin.modules.system.service;

import io.admin.framework.data.service.BaseService;
import io.admin.modules.system.dao.StudentDao;
import io.admin.modules.system.entity.Student;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class StudentService extends BaseService<Student> {

    @Resource
    StudentDao dao;
}
```

## 3.4 控制器 (StudentController.java)

```java
package io.admin.modules.system.controller;

import io.admin.common.dto.AjaxResult;
import io.admin.framework.config.argument.RequestBodyKeys;
import io.admin.framework.config.security.HasPermission;
import io.admin.framework.data.specification.Spec;
import io.admin.modules.system.entity.Student;
import io.admin.modules.system.service.StudentService;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/student")
public class StudentController {

    @Resource
    StudentService service;

    @HasPermission("student:view")
    @RequestMapping("page")
    public AjaxResult page(String searchText, @PageableDefault(direction = Sort.Direction.DESC, sort = "updateTime") Pageable pageable) throws Exception {
        Spec<Student> q = Spec.of();
        q.orLike(searchText, Student.Fields.name, Student.Fields.className);

        Page<Student> page = service.findPageByRequest(q, pageable);

        return AjaxResult.ok().data(page);
    }

    @HasPermission("student:save")
    @PostMapping("save")
    public AjaxResult save(@RequestBody Student input, RequestBodyKeys updateFields) throws Exception {
        service.saveOrUpdateByRequest(input, updateFields);
        return AjaxResult.ok().msg("保存成功");
    }

    @HasPermission("student:delete")
    @RequestMapping("delete")
    public AjaxResult delete(String id) {
        service.deleteByRequest(id);
        return AjaxResult.ok().msg("删除成功");
    }
}
```

## 3.5 配置文件 (application-data-framework.yml)

```yaml
data:
  menus:
    - id: student
      name: 学生管理
      path: /system/student
      icon: UserOutlined
      perms:
        - name: 查看
          perm: student:view
        - name: 保存
          perm: student:save
        - name: 删除
          perm: student:delete
```

## 3.6 前端页面 (Student/index.jsx)

```javascript
import {PlusOutlined} from '@ant-design/icons'
import {Button, Form, Input, Modal, Popconfirm} from 'antd'
import React from 'react'
import {ButtonList, HttpUtils, Page, ProTable} from "../../../framework";

export default class extends React.Component {

    state = {
        formValues: {},
        formOpen: false
    }

    formRef = React.createRef()
    tableRef = React.createRef()

    columns = [
        {
            title: '姓名',
            dataIndex: 'name',
        },
        {
            title: '年龄',
            dataIndex: 'age',
        },
        {
            title: '性别',
            dataIndex: 'gender',
        },
        {
            title: '班级',
            dataIndex: 'className',
        },
        {
            title: '联系电话',
            dataIndex: 'phone',
        },
        {
            title: '邮箱',
            dataIndex: 'email',
        },
        {
            title: '操作',
            dataIndex: 'option',
            render: (_, record) => (
                <ButtonList>
                    <Button size='small' perm='student:save' onClick={() => this.handleEdit(record)}>编辑</Button>
                    <Popconfirm perm='student:delete' title='是否确定删除学生信息' onConfirm={() => this.handleDelete(record)}>
                        <Button size='small'>删除</Button>
                    </Popconfirm>
                </ButtonList>
            ),
        },
    ]

    handleAdd = () => {
        this.setState({formOpen: true, formValues: {}})
    }

    handleEdit = record => {
        this.setState({formOpen: true, formValues: record})
    }

    onFinish = values => {
        HttpUtils.post('admin/student/save', values).then(rs => {
            this.setState({formOpen: false})
            this.tableRef.current.reload()
        })
    }

    handleDelete = record => {
        HttpUtils.get('admin/student/delete', {id: record.id}).then(rs => {
            this.tableRef.current.reload()
        })
    }

    render() {
        return <Page>
            <ProTable
                actionRef={this.tableRef}
                toolBarRender={(params, {selectedRows, selectedRowKeys}) => {
                    return <ButtonList>
                        <Button perm='student:save' type='primary' onClick={this.handleAdd}>
                            <PlusOutlined/> 新增
                        </Button>
                    </ButtonList>
                }}
                request={(params) => HttpUtils.get('admin/student/page', params)}
                columns={this.columns}
            />

            <Modal title='学生信息'
                   open={this.state.formOpen}
                   onOk={() => this.formRef.current.submit()}
                   onCancel={() => this.setState({formOpen: false})}
                   destroyOnHidden
                   maskClosable={false}
            >
                <Form ref={this.formRef} labelCol={{flex: '100px'}}
                      initialValues={this.state.formValues}
                      onFinish={this.onFinish}
                >
                    <Form.Item name='id' noStyle></Form.Item>

                    <Form.Item label='姓名' name='name' rules={[{required: true}]}>
                        <Input/>
                    </Form.Item>

                    <Form.Item label='年龄' name='age'>
                        <Input type='number'/>
                    </Form.Item>

                    <Form.Item label='性别' name='gender'>
                        <Input/>
                    </Form.Item>

                    <Form.Item label='班级' name='className'>
                        <Input/>
                    </Form.Item>

                    <Form.Item label='联系电话' name='phone'>
                        <Input/>
                    </Form.Item>

                    <Form.Item label='邮箱' name='email'>
                        <Input type='email'/>
                    </Form.Item>
                </Form>
            </Modal>
        </Page>
    }
}
```

以上是学生管理功能的完整代码模板，业务项目可直接使用此模板进行开发，仅需根据实际需求进行适当调整。

---

**注意**: 以上是框架的完整开发文档。业务项目在使用时，只需按照对应部分的说明进行开发即可。如需深入了解具体功能，请参考源码。