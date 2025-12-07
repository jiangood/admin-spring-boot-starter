# SpringBoot Admin Starter

## 1 介绍

本项目是一个小型管理系统框架，为业务项目提供快速开发能力。它集成了常用的系统管理功能，如用户管理、角色管理、权限管理、作业调度、流程引擎等功能，并提供了完整的前后端解决方案。

### 开发环境

- **Java**: 17+
- **Node.js**: 19+
- **数据库**: MySQL 8.0+

### 版本信息

后端最新版本为： ![Maven Version](https://img.shields.io/maven-central/v/io.github.jiangood/springboot-admin-starter)

前端最新版本为： ![NPM Version](https://img.shields.io/npm/v/@jiangood/springboot-admin-starter)

### 后端依赖

- **Spring Boot**: 3.5.8 - 核心框架
- **Spring Web**: 3.5.8 - Web开发支持
- **Spring Data JPA**: 3.5.8 - 数据持久化
- **Spring Security**: 3.5.8 - 安全框架
- **Spring Quartz**: 3.5.8 - 作业调度
- **MapStruct**: 1.6.3 - 对象映射
- **Hutool**: 5.8.41 - 工具类库
- **POI**: 5.5.0 - Excel处理
- **Guava**: 33.5.0-jre - Google工具库
- **Commons IO**: 2.21.0 - IO工具
- **Lombok**: 1.18.30 - 代码简化
- **Flowable**: 7.2.0 - 工作流引擎
- **MinIO**: 8.6.0 - 对象存储
- **iTextPDF**: 5.5.13.4 - PDF处理
- **UUID Creator**: 6.1.1 - UUID生成器
- **Hibernate**: 内置 - JPA实现

### 前端依赖

- **React**: 19.0.0 - 前端框架
- **Ant Design**: 6.0.0 - UI组件库
- **Umi**: 4.0.0 - 前端构建工具
- **Axios**: 1.13.2 - HTTP客户端
- **Lodash**: 4.17.21 - 实用工具库
- **Day.js**: 1.11.13 - 时间处理库
- **BPMN-js**: 18.7.0 - 流程图编辑器
- **TinyMCE**: 6.0.0 - 富文本编辑器
- **Typescript**: 5.0.0 - 类型检查

## 2 前端

前端框架提供了一套完整的组件库和工具类，帮助业务项目快速搭建用户界面。

### 组件库

#### 2.1 公共组件

框架提供了丰富的公共组件，包括：

- **Page**: 页面容器组件，用于设置页面样式和背景色
- **ProTable**: 高级表格组件，支持分页、搜索、排序等功能
- **ProModal**: 高级模态框组件，支持显示和隐藏控制
- **Ellipsis**: 文本省略组件
- **LinkButton**: 链接按钮组件
- **DownloadFileButton**: 文件下载按钮组件
- **NamedIcon**: 图标组件
- **PageLoading**: 页面加载组件
- **ButtonList**: 按钮列表组件，用于管理按钮间距
- **Gap**: 间距组件

#### 2.2 字段组件

框架提供了多种字段组件，用于表单输入：

- **FieldBoolean**: 布尔值字段
- **FieldDate**: 日期字段
- **FieldDateRange**: 日期范围字段
- **FieldDictSelect**: 数据字典选择器
- **FieldEditor**: 富文本编辑器
- **FieldPercent**: 百分比字段
- **FieldRemoteSelect**: 远程选择器
- **FieldRemoteSelectMultiple**: 远程多选器
- **FieldRemoteSelectMultipleInline**: 远程内联多选器
- **FieldRemoteTree**: 远程树选择器
- **FieldRemoteTreeCascader**: 远程树级联选择器
- **FieldRemoteTreeSelect**: 远程树选择器
- **FieldRemoteTreeSelectMultiple**: 远程树多选器
- **FieldSysOrgTree**: 系统组织树选择器
- **FieldSysOrgTreeSelect**: 系统组织树选择器
- **FieldTable**: 表格字段
- **FieldTableSelect**: 表格选择器
- **FieldUploadFile**: 文件上传组件，支持多种文件类型和裁剪功能

#### 2.3 视图组件

框架提供了视图组件，用于数据显示：

- **ViewBoolean**: 布尔值视图
- **ViewPassword**: 密码视图

#### 2.4 工具函数

框架提供了丰富的工具函数：

- **ArrUtils**: 数组工具类
- **ColorsUtils**: 颜色工具类
- **DateUtils**: 日期工具类
- **DeviceUtils**: 设备工具类
- **DomUtils**: DOM工具类
- **EventBusUtils**: 事件总线工具类
- **MessageUtils**: 消息工具类
- **ObjectUtils**: 对象工具类
- **StorageUtils**: 存储工具类
- **StringUtils**: 字符串工具类
- **TreeUtils**: 树结构工具类
- **UrlUtils**: URL工具类
- **UuidUtils**: UUID工具类
- **ValidateUtils**: 验证工具类
- **system**: 系统相关工具

#### 2.5 系统组件

框架还提供了一些系统专用组件，用于处理系统管理相关的功能。

### 2.6 页面组件

框架提供了预设的页面组件：

- **LoginPage**: 登录页面

### 2.7 ProTable 参数说明

`ProTable` 组件提供了以下参数：

- **request**: 数据请求函数
- **columns**: 表格列定义
- **actionRef**: 操作引用，用于调用组件内部方法
- **toolBarRender**: 工具栏渲染函数
- **rowKey**: 行键，用于标识每一行，默认为 "id"
- **defaultPageSize**: 默认每页显示数量
- **scrollY**: 表格垂直滚动高度
- **bordered**: 是否显示边框
- **toolbarOptions**: 工具栏选项，设置为 false 可隐藏工具栏
- **searchFormItemsRender**: 搜索表单项渲染（已废弃，请直接放置到 ProTable 的子节点）

### 2.8 FieldUploadFile 参数说明

`FieldUploadFile` 组件提供了以下参数：

- **maxCount**: 最大上传文件数量，默认为 1
- **accept**: 允许上传的文件类型
- **listType**: 文件列表样式，默认为 'picture-card'
- **cropImage**: 是否开启图片裁剪
- **onFileChange**: 文件变化时的回调函数
- **cropperProps**: 裁剪组件属性（在开启图片裁剪时使用）

## 3 后端

后端框架提供了丰富的功能组件和工具类，帮助业务项目快速实现数据管理、权限控制等功能。

### 3.1 规范化查询

框架提供了 `Spec` 类，用于构建动态查询条件：

```java
public class Spec<T> implements Specification<T> {
    // 构建查询条件的链式API
    public Spec<T> eq(String field, Object value) // 等于
    public Spec<T> ne(String field, Object value) // 不等于
    public Spec<T> like(String field, String value) // 模糊匹配
    public Spec<T> in(String field, Collection<?> values) // 包含
    public Spec<T> between(String field, C value1, C value2) // 范围查询
    public Spec<T> isNotNull(String field) // 非空
    public Spec<T> isNull(String field) // 为空
    public Spec<T> distinct(boolean distinct) // 去重
    public Spec<T> or(Specification<T>... orSpecifications) // 或条件
    public Spec<T> not(Specification<T> spec) // 非条件
}
```

### 3.2 树形结构处理

框架提供了 `TreeUtils` 工具类，用于处理树形结构数据：

```java
public class TreeUtils {
    // 构建树结构
    public static List<TreeOption> buildTree(List<TreeOption> list)
    // 将树转换为Map
    public static Map<String, TreeOption> treeToMap(List<TreeOption> tree)
    // 遍历树节点
    public static <E> void walk(List<E> list, Function<E, List<E>> getChildren, Consumer<E> consumer)
    // 获取树的叶子节点
    public static <E> List<E> getLeafs(List<E> list, Function<E, List<E>> getChildren)
    // 将树转换为列表
    public static <E> List<E> treeToList(List<E> tree, Function<E, List<E>> getChildren)
}
```

### 3.3 注解处理

框架提供了 `RemarkUtils` 工具类，用于获取注解信息：

```java
public class RemarkUtils {
    // 获取字段注解信息
    public static String getRemark(Field field)
    // 获取类注解信息
    public static String getRemark(Class<?> t)
    // 获取枚举注解信息
    public static String getRemark(Enum<?> t)
    // 获取方法注解信息
    public static String getRemark(Method method)
}
```

### 3.4 Excel导入导出

框架提供了 `ExcelUtils` 工具类，支持Excel的导入导出功能：

```java
public class ExcelUtils {
    // 导入Excel
    public static <T> List<T> importExcel(Class<T> cls, InputStream is) throws Exception
    // 导出Excel
    public static <T> void exportExcel(Class<T> cls, List<T> list, OutputStream os) throws Exception
}
```

### 3.5 JDBC工具

框架提供了 `JdbcUtils` 工具类，用于执行原生SQL查询：

```java
@Component
public class JdbcUtils {
    // 执行更新、插入或删除操作
    public int update(String sql, Object... params)
    // 查询单条记录
    public <T> T findOne(Class<T> cls, String sql, Object... params)
    // 查询多条记录
    public <T> List<T> findAll(Class<T> cls, String sql, Object... params)
    // 分页查询
    public <T> Page<T> findAll(Class<T> cls, Pageable pageable, String sql, Object... params)
    // 动态插入
    public int insert(String tableName, Object bean)
    // 动态更新
    public int updateById(String table, Object bean)
}
```

### 3.6 ID生成策略

框架提供了多种ID生成策略：

- **GenerateUuidV7**: 基于UUID Version 7的ID生成策略
- **GeneratePrefixedSequence**: 带前缀的序列ID生成策略
- **UuidV7IdGenerator**: UUID V7 ID生成器实现
- **PrefixedSequenceGenerator**: 前缀序列生成器实现
- **DailyTableGenerator**: 每日表生成器

### 3.7 数据转换器

框架提供了多种数据转换器：

- **BaseConverter**: 基础转换器
- **BaseToListConverter**: 转换为列表
- **ToIntListConverter**: 转换为整数列表
- **ToLongListConverter**: 转换为长整数列表
- **ToBigDecimalListConverter**: 转换为大整数列表
- **ToListConverter**: 列表转换器
- **ToMapConverter**: 转换为Map
- **ToPositionConverter**: 位置转换器
- **ToQueryStringMapConverter**: 查询字符串Map转换器

### 3.8 验证器

框架提供了丰富的验证器：

- **ValidateCarDrivingLicence**: 验证驾驶证
- **ValidateChineseName**: 验证中文姓名
- **ValidateContainsChinese**: 验证包含中文
- **ValidateCreditCode**: 验证统一社会信用代码
- **ValidateDate**: 验证日期
- **ValidateGeneral**: 通用验证
- **ValidateHex**: 验证十六进制
- **ValidateIdNum**: 验证身份证号
- **ValidateIp**: 验证IP地址
- **ValidateIpv4**: 验证IPv4地址
- **ValidateMobile**: 验证手机号
- **ValidateNotContainsChinese**: 验证不包含中文
- **ValidatePassword**: 验证密码
- **ValidatePlateNumber**: 验证车牌号
- **ValidateStartWithLetter**: 验证以字母开头
- **ValidateYearMonth**: 验证年月
- **ValidateYearQuarter**: 验证年季度
- **ValidateZipCode**: 验证邮编

### 3.9 登录工具

框架提供了 `LoginUtils` 工具类，用于获取当前登录用户信息：

```java
public class LoginUtils {
    // 获取当前登录用户ID
    public static String getUserId()
    // 获取当前登录用户
    public static LoginUser getUser()
    // 获取组织权限
    public static List<String> getOrgPermissions()
    // 获取权限
    public static List<String> getPermissions()
    // 获取角色
    public static List<String> getRoles()
    // 是否为管理员
    public static boolean isAdmin()
}
```

### 3.10 作业调度

框架提供了作业调度功能：

- **BaseJob**: 作业基类，所有作业需要继承此类
- **JobDescription**: 作业描述注解，用于定义作业参数
- **HelloWorldJob**: 示例作业实现

```java
@JobDescription(label = "你好世界",  params = {@FieldDescription(name = "name", label = "姓名")})
public class HelloWorldJob extends BaseJob {
    @Override
    public String execute(JobDataMap data, Logger logger) throws Exception {
        System.out.printf("你好世界, 欢迎您%s!%n", data.get("name"));
        logger.info("运行你好世界");
        return "OK";
    }
}
```

### 3.11 流程引擎

框架集成了Flowable工作流引擎，并提供了示例流程监听器：

```java
@Component
public class LeaveProcessListener implements ProcessListener {
    @Override
    public void onProcessEvent(FlowableEventType type, String initiator, String businessKey, Map<String, Object> variables) {
        // 处理流程事件
    }
}
```

### 3.12 菜单列表

系统预设了以下菜单项：

- 我的任务
  - 路径: /flowable/task
  - 权限: myFlowableTask:manage
- 系统管理
  - 机构管理: /system/org
  - 用户管理: /system/user  
  - 角色管理: /system/role
  - 操作手册: /system/sysManual
  - 系统设置: /system/config
  - 数据字典: /system/dict
  - 存储文件: /system/file
  - 作业调度: /system/job
  - 操作日志: /system/log
  - 接口管理: /system/api
  - 流程引擎: /flowable
  - 报表管理: /ureport

业务项目可以在 `application-data-biz.yml` 文件中进行自定义配置。

## 4 系统设置参考

系统配置项定义在 `application-data-framework.yml` 文件中，包括：

### 4.1 邮件配置

- **email.from**: 邮件发送账号
- **email.pass**: 邮件发送密码

### 4.2 网站配置

- **sys.copyright**: 版权信息
- **sys.loginBackground**: 登录背景图
- **sys.loginBoxBottomTip**: 登录框下面的提示
- **sys.title**: 站点标题
- **sys.waterMark**: 开启水印（布尔类型），在所有页面增加水印

### 4.3 系统配置

- **sys.baseUrl**: 请求基础地址，用于拼接完整请求地址
- **sys.jwtSecret**: JWT密码

## 5 流程配置

流程定义配置在 `application-process.yml` 文件中：

```yaml
# 流程定义列表
process:
  list:
    - key: "leave_request"
      name: "请假流程"
      listener: io.admin.modules.flowable.example.LeaveProcessListener
      variables:
        - name: "days"
          label: "请假天数"
          value-type: number
          required: true
        - name: "reason"
          label: "请假原因"
          required: true
      # 流程中各个节点对应的表单定义
      forms:
        - key: "start_form"
          label: "请假申请表"
        - key: "manager_approve_form"
          label: "经理审批表"
        - key: "finish_view"
          label: "流程结果查看"
```

## 6 模板代码

下面以学生管理为例，展示通用代码模板：

### 6.1 学生实体类 (Student.java)

```java
package io.admin.modules.system.entity;

import io.admin.common.utils.annotation.Remark;
import io.admin.framework.data.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

@Remark("学生")
@Entity
@Getter
@Setter
@Table(uniqueConstraints = {@UniqueConstraint(name = "uk_student", columnNames = {"studentNo", "version"})})
@FieldNameConstants
public class Student extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Remark("学号")
    @Column(length = 100)
    @Size(max = 100, message = "学号长度不能超过100个字符")
    private String studentNo;

    @NotNull
    @Remark("姓名")
    @Column(length = 100)
    @Size(max = 100, message = "姓名长度不能超过100个字符")
    private String name;

    @Remark("年龄")
    @Positive(message = "年龄必须为正整数")
    private Integer age;

    @Remark("班级")
    @Column(length = 100)
    @Size(max = 100, message = "班级长度不能超过100个字符")
    private String className;

}
```

### 6.2 学生DAO类 (StudentDao.java)

```java
package io.admin.modules.system.dao;

import io.admin.framework.data.repository.BaseDao;
import io.admin.framework.data.specification.Spec;
import io.admin.modules.system.entity.Student;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDao extends BaseDao<Student> {

    public int findMaxVersion(String studentNo){
        Spec<Student> q = Spec.<Student>of().eq(Student.Fields.studentNo, studentNo);

        Student e = this.findTop1(q, Sort.by(Sort.Direction.DESC, Student.Fields.version));

        return e == null ? 0 : e.getVersion();
    }

}
```

### 6.3 学生Service类 (StudentService.java)

```java
package io.admin.modules.system.service;

import io.admin.framework.data.service.BaseService;
import io.admin.modules.system.dao.StudentDao;
import io.admin.modules.system.entity.Student;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService extends BaseService<Student> {

    @Resource
    StudentDao dao;

    @Override
    public Student saveOrUpdateByRequest(Student input, List<String> updateKeys) throws Exception {
        if(input.isNew()){
            int maxVersion = dao.findMaxVersion(input.getStudentNo());
            input.setVersion(maxVersion+1);
        }

        return super.saveOrUpdateByRequest(input, updateKeys);
    }
}
```

### 6.4 学生Controller类 (StudentController.java)

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
        q.orLike(searchText, Student.Fields.name, Student.Fields.studentNo);

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

### 6.5 前端页面 (index.jsx)

```javascript
import {PlusOutlined} from '@ant-design/icons'
import {Button, Form, Input, Modal, Popconfirm} from 'antd'
import React from 'react'
import {ButtonList, HttpUtils, Page, ProTable} from "../../framework";

export default class extends React.Component {

    state = {
        formValues: {},
        formOpen: false
    }

    formRef = React.createRef()
    tableRef = React.createRef()

    columns = [
        {
            title: '学号',
            dataIndex: 'studentNo',
        },
        {
            title: '姓名',
            dataIndex: 'name',
        },
        {
            title: '年龄',
            dataIndex: 'age',
        },
        {
            title: '班级',
            dataIndex: 'className',
        },
        {
            title: '操作',
            dataIndex: 'option',
            render: (_, record) => (
                <ButtonList>
                    <Button size='small' perm='student:save' onClick={() => this.handleEdit(record)}>编辑</Button>
                    <Popconfirm perm='student:delete' title='是否确定删除学生'  onConfirm={() => this.handleDelete(record)}>
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

            <Modal title='学生'
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

                    <Form.Item label='学号' name='studentNo' rules={[{required: true}]}>
                        <Input/>
                    </Form.Item>

                    <Form.Item label='姓名' name='name' rules={[{required: true}]}>
                        <Input/>
                    </Form.Item>

                    <Form.Item label='年龄' name='age'>
                        <Input type='number'/>
                    </Form.Item>

                    <Form.Item label='班级' name='className'>
                        <Input/>
                    </Form.Item>

                </Form>
            </Modal>
        </Page>
    }
}
```

### 6.6 配置文件示例

在 `application-data-framework.yml` 中添加学生管理的菜单配置：

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