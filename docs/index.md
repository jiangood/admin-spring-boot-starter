# 介绍

- [编码规范](编码规范.md)
- [使用说明书](index.md)
- [模板代码](template.md)
- [ai提示词](ai.md)


## 1.1 项目信息

**后端**：
- 项目名称：`springboot-admin-starter`
- 版本：`0.0.2`
- 描述：小型管理系统框架
- Java 版本要求：`17`
- 作者：jiangtao

**前端**：
- 模块名：`@jiangood/springboot-admin-starter`
- 版本：`0.0.1-beta.30`
- 描述：springboot-admin-starter

## 1.2 开发环境

- Java 17
- Maven
- Node.js
- React 19
- Ant Design 6.x
- MySQL（或其他JPA支持的数据库）

## 1.3 后端依赖

| 依赖名称 | 版本号 | 描述 |
|---------|--------|------|
| spring-boot-starter-web | 3.5.8 | Web 应用启动器 |
| spring-boot-starter-quartz | 3.5.8 | 任务调度支持 |
| spring-boot-starter-validation | 3.5.8 | 参数验证支持 |
| spring-boot-starter-aop | 3.5.8 | 面向切面编程支持 |
| spring-boot-starter-data-jpa | 3.5.8 | JPA 数据访问支持 |
| spring-boot-starter-cache | 3.5.8 | 缓存支持 |
| spring-boot-starter-security | 3.5.8 | 安全支持 |
| mapstruct | 1.6.3 | 对象映射工具 |
| minio | 8.6.0 | 对象存储客户端 |
| poi-ooxml | 5.5.0 | Excel 操作支持 |
| poi-scratchpad | 5.5.0 | Word 操作支持 |
| itextpdf | 5.5.13.4 | PDF 操作支持 |
| uuid-creator | 6.1.1 | UUID 生成工具 |
| hutool-all | 5.8.41 | Java 工具库 |
| commons-lang3 | (Spring Boot内置) | Apache Commons Lang |
| guava | 33.5.0-jre | Google 工具库 |
| flowable-spring-boot-starter-process | 7.2.0 | 工作流程引擎 |
| mysql-connector-j | (Spring Boot内置) | MySQL 连接器 |

## 1.4 前端依赖

| 依赖名称 | 版本号 | 描述 |
|---------|--------|------|
| antd | ^6.0.0 | Ant Design 组件库 |
| react | ^19.0.0 | React 框架 |
| react-dom | ^19.0.0 | React DOM 渲染 |
| axios | ^1.13.2 | HTTP 客户端 |
| umi | ^4.0.0 | 前端框架 |
| @ant-design/icons | ^5.4.0 | Ant Design 图标库 |
| @tinymce/tinymce-react | ^6.0.0 | 富文本编辑器 |
| dayjs | ^1.11.13 | 日期处理库 |
| lodash | ^4.17.21 | JavaScript 工具库 |

# 2 前端

## 2.1 组件

### 2.1.1 ProModal
**参数说明**: 
- `title`: 弹窗标题
- `ref`: 引用对象
- `onShow`: 显示时回调函数
- `footer`: 底部内容
- `width`: 弹窗宽度
- `children`: 弹窗内容

**说明**: 专业弹窗组件，支持显示/隐藏控制

### 2.1.2 Ellipsis
**参数说明**:
- `length`: 省略前的字符长度，默认15
- `children`: 需要省略的文本内容
- `pre`: 是否使用pre标签显示

**说明**: 文本省略组件，点击可查看完整内容

### 2.1.3 PageLoading
**参数说明**:
- `message`: 加载提示信息

**说明**: 页面加载动画组件

### 2.1.4 DownloadFileButton
**参数说明**:
- `url`: 下载文件的URL
- `params`: 请求参数
- `children`: 按钮内容
- 其他Button的参数

**说明**: 提供带加载提示的文件下载按钮

### 2.1.5 NamedIcon
**参数说明**:
- `name`: 图标名称

**说明**: 根据名称动态渲染Ant Design图标

### 2.1.6 LinkButton
**参数说明**:
- `path`: 要打开的页面路径
- `label`: 页面标签
- `children`: 按钮内容
- `size`: 按钮大小，默认'small'

**说明**: 链接类型的按钮组件

## 2.2 字段组件

### 2.2.1 FieldRemoteSelect
**参数说明**:
- `url`: 远程搜索的URL
- `value`: 当前值
- `onChange`: 值改变回调函数
- 其他Select的参数

**说明**: 远程搜索选择框，支持模糊搜索

### 2.2.2 FieldDate
**参数说明**:
- `type`: 日期类型，可选值：'YYYY', 'YYYY-MM', 'YYYY-QQ', 'YYYY-MM-DD', 'YYYY-MM-DD HH:mm', 'YYYY-MM-DD HH:mm:ss', 'HH:mm', 'HH:mm:ss'
- `value`: 当前值
- `onChange`: 值改变回调函数

**说明**: 支持多种日期时间格式的选择组件

### 2.2.3 FieldDateRange
**参数说明**: 
- `value`: 当前值
- `onChange`: 值改变回调函数
- 其他DatePicker.RangePicker参数

**说明**: 日期范围选择组件

### 2.2.4 FieldDictSelect
**参数说明**:
- `dictType`: 字典类型
- `value`: 当前值
- `onChange`: 值改变回调函数

**说明**: 数据字典选择组件

### 2.2.5 FieldEditor
**参数说明**:
- `value`: 编辑器内容
- `onChange`: 内容改变回调函数

**说明**: 富文本编辑器组件

### 2.2.6 FieldBoolean
**参数说明**:
- `value`: 当前值
- `onChange`: 值改变回调函数

**说明**: 布尔值选择组件

### 2.2.7 FieldPercent
**参数说明**:
- `value`: 当前值
- `onChange`: 值改变回调函数

**说明**: 百分比输入组件

### 2.2.8 FieldUploadFile
**参数说明**:
- `value`: 当前值
- `onChange`: 值改变回调函数
- `accept`: 允许的文件类型
- `maxCount`: 最大上传数量

**说明**: 文件上传组件

### 2.2.9 FieldRemoteTree
**参数说明**:
- `url`: 获取树数据的URL
- `value`: 当前值
- `onChange`: 值改变回调函数

**说明**: 远程树形数据选择组件

### 2.2.10 FieldTable
**参数说明**:
- `columns`: 表格列定义
- `value`: 当前值
- `onChange`: 值改变回调函数

**说明**: 表格字段组件

## 2.3 查看组件

### 2.3.1 ViewBoolean
**参数说明**:
- `value`: 布尔值

**说明**: 将布尔值显示为'是'或'否'

### 2.3.2 ViewPassword
**参数说明**:
- `value`: 密码值

**说明**: 密码查看组件，通常显示为星号

## 2.4 工具

### 2.4.1 DateUtils（日期工具）
**说明**: 日期处理工具类
**举例**:
```javascript
import { DateUtils } from "@jiangood/springboot-admin-starter";

// 格式化当前时间
const now = DateUtils.now(); // "2022-01-23 11:59:59"
// 友好时间显示
const friendly = DateUtils.friendlyTime(new Date("2022-01-22")); // "1天前"
```

### 2.4.2 StringUtils（字符串工具）
**说明**: 字符串处理工具类
**举例**:
```javascript
import { StringUtils } from "@jiangood/springboot-admin-starter";

// 字符串截取
const result = StringUtils.ellipsis("这是一个很长的字符串", 10); // "这是一个很长的..."
// 驼峰转换
const camel = StringUtils.toCamelCase("snake_case_name"); // "snakeCaseName"
```

### 2.4.3 TreeUtils（树工具）
**说明**: 树结构处理工具类
**举例**:
```javascript
import { TreeUtils } from "@jiangood/springboot-admin-starter";

// 扁平数组转树结构
const tree = TreeUtils.buildTree([{id: 1, pid: null}, {id: 2, pid: 1}]);
// 树结构转扁平数组 
const list = TreeUtils.treeToList(tree);
```

### 2.4.4 ArrUtils（数组工具）
**说明**: 数组处理工具类

### 2.4.5 UrlUtils（URL工具）
**说明**: URL处理工具类

### 2.4.6 EventBusUtils（事件总线工具）
**说明**: 事件总线工具类

### 2.4.7 ColorsUtils（颜色工具）
**说明**: 颜色处理工具类

### 2.4.8 DomUtils（DOM工具）
**说明**: DOM操作工具类

### 2.4.9 MessageUtils（消息工具）
**说明**: 消息提示工具类

### 2.4.10 ObjectUtils（对象工具）
**说明**: 对象处理工具类

### 2.4.11 StorageUtils（存储工具）
**说明**: 本地存储工具类

### 2.4.12 DeviceUtils（设备工具）
**说明**: 设备检测工具类

### 2.4.13 ValidateUtils（验证工具）
**说明**: 数据验证工具类

### 2.4.14 UuidUtils（UUID工具）
**说明**: UUID生成工具类

# 3 后端

## 3.1 菜单列表

系统包含以下菜单配置：

- 我的任务 (myFlowableTask): `/flowable/task`，权限 `myFlowableTask:manage`
- 系统管理 (sys): 包含子菜单
  - 机构管理 (sysOrg): `/system/org`，权限 `sysOrg:save`, `sysOrg:view`, `sysOrg:delete`
  - 用户管理 (sysUser): `/system/user`，权限 `sysUser:view`, `sysUser:save`, `sysUser:delete`, `sysUser:resetPwd`, `sysUser:grantPerm`
  - 角色管理 (sysRole): `/system/role`，权限 `sysRole:save`
  - 操作手册 (sysManual): `/system/sysManual`，权限 `sysManual:view`, `sysManual:delete`, `sysManual:save`
  - 系统设置 (sysConfig): `/system/config`，权限 `sysConfig:view`, `sysConfig:save`
  - 数据字典 (sysDict): `/system/dict`，权限 `sysDict:view`, `sysDict:save`, `sysDict:delete`
  - 存储文件 (sysFile): `/system/file`，权限 `sysFile:view`, `sysFile:delete`
  - 作业调度 (job): `/system/job`，权限 `job:view`, `job:save`, `job:triggerJob`, `job:jobLogClean`
  - 操作日志 (sysLog): `/system/log`，权限 `sysLog:view`
  - 接口管理 (api): `/system/api`，权限 `api`
  - 流程引擎 (flowableModel): `/flowable`，权限 `flowableModel:design`, `flowableModel:deploy`, `flowableTask:setAssignee`, `flowableInstance:close`
  - 报表管理 (ureport): `/ureport`，权限 `ureport:view`, `ureport:design`

## 3.2 业务项目配置

业务项目需要在 `application-data.yml` 文件中配置菜单和系统设置。配置格式与 `application-data-framework.yml` 相同，系统会自动合并框架和业务项目的菜单配置。

## 3.3 数据框架

### 3.3.1 Spec（JPA Specification 构建器）
**说明**: 简洁、动态、支持关联字段查询的 JPA Specification 构建器
**参数方法**:
- `eq(field, value)`: 等于查询
- `ne(field, value)`: 不等于查询
- `gt(field, value)`: 大于查询
- `lt(field, value)`: 小于查询
- `ge(field, value)`: 大于等于查询
- `le(field, value)`: 小于等于查询
- `like(field, value)`: 模糊查询
- `in(field, values)`: IN 查询
- `between(field, value1, value2)`: 范围查询
- `isNotNull(field)`: 非空查询
- `isNull(field)`: 空值查询
- `or(specifications)`: OR 条件查询

**举例**:
```java
Spec<User> spec = Spec.of()
    .eq("deptId", deptId)
    .like("name", "张")
    .ge("age", 18);
Page<User> userPage = userDao.findAll(spec, pageable);
```

### 3.3.2 TreeUtils（树工具类）
**说明**: 将列表转换为树结构的工具类
**方法**:
- `buildTree(list)`: 构建树结构
- `treeToMap(tree)`: 树结构转Map
- `walk(list, consumer)`: 遍历树节点
- `getLeafs(list)`: 获取叶子节点

### 3.3.3 RemarkUtils（注解工具）
**说明**: 处理 `@Remark` 注解的工具类
**方法**:
- `getRemark(field)`: 获取字段注解值
- `getRemark(method)`: 获取方法注解值
- `getRemark(clazz)`: 获取类注解值

### 3.3.4 ExcelUtils（Excel工具）
**说明**: Excel导入导出工具
**方法**:
- `importExcel(cls, inputStream)`: 导入Excel
- `exportExcel(cls, list, outputStream)`: 导出Excel

**举例**:
```java
// 导出Excel
List<User> users = userService.findAll();
ExcelUtils.exportExcel(User.class, users, outputStream);
```

### 3.3.5 JdbcUtils（原生SQL工具）
**说明**: 原生SQL执行工具类
**方法**:
- `findOne(cls, sql, params)`: 查询单个对象
- `findAll(cls, sql, params)`: 查询对象列表
- `update(sql, params)`: 执行更新语句
- `exists(sql, params)`: 检查记录是否存在
- `insert(tableName, bean)`: 动态插入记录
- `updateById(tableName, bean)`: 根据ID更新记录

**举例**:
```java
// 查询单个对象
User user = jdbcUtils.findOne(User.class, "SELECT * FROM user WHERE id = ?", id);
// 动态插入
int rows = jdbcUtils.insert("sys_user", user);
```

### 3.3.6 ID生成注解

#### 3.3.6.1 GeneratePrefixedSequence
**说明**: 带前缀的序列ID生成注解
**参数**:
- `prefix`: 前缀字符串

**举例**:
```java
@GeneratePrefixedSequence(prefix = "USER")
private String id;
```

#### 3.3.6.2 GenerateUuidV7
**说明**: UUID v7 ID生成注解
**举例**:
```java
@GenerateUuidV7
private String id;
```

### 3.3.7 数据类型转换器
**说明**: 数据库字段与Java对象之间的类型转换器

- `BaseConverter<T>`: 基础JSON转换器
- `ToListConverter`: 列表类型转换器
- `ToMapConverter`: Map类型转换器
- `ToBigDecimalListConverter`: BigDecimal列表转换器
- `ToIntListConverter`: Integer列表转换器
- `ToLongListConverter`: Long列表转换器
- 等等

### 3.3.8 验证器
**说明**: 自定义验证注解

- `@ValidateMobile`: 验证手机号
- `@ValidateChineseName`: 验证中文姓名
- `@ValidateIdNum`: 验证身份证号码
- `@ValidateCreditCode`: 验证统一社会信用代码
- `@ValidatePassword`: 验证密码强度
- `@ValidatePlateNumber`: 验证车牌号
- `@ValidateZipCode`: 验证邮政编码
- 等等

## 3.4 系统功能

### 3.4.1 LoginUtils（登录工具）
**说明**: 获取当前登录用户信息的工具类
**方法**:
- `getUserId()`: 获取当前用户ID
- `getUser()`: 获取当前登录用户对象
- `getPermissions()`: 获取当前用户权限
- `getRoles()`: 获取当前用户角色
- `isAdmin()`: 判断是否为管理员

### 3.4.2 任务调度

#### 3.4.2.1 BaseJob（基础任务类）
**说明**: 任务调度的基础类，提供日志记录和异常处理

#### 3.4.2.2 JobDescription（任务描述注解）
**说明**: 任务描述注解
**参数**:
- `label`: 任务标签
- `params`: 任务参数描述

#### 3.4.2.3 HelloWorldJob（任务示例）
**说明**: 任务调度示例类，演示如何创建自定义任务

## 3.5 流程引擎

### 3.5.1 流程配置
在 `application-process.yml` 中配置流程定义，包括流程键值、名称、监听器和变量定义。

### 3.5.2 LeaveProcessListener（请假流程监听器）
**说明**: 请假流程的事件监听器，用于处理流程事件。

