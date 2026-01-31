## 1. 项目背景
这是一个使用 open-admin 框架的后端管理系统项目，基于 Spring Boot + React + Ant Design + Umi 技术栈。

# 技术要求
- 使用 open-admin 框架的标准代码模板
- 遵循框架的编码规范
- 后端使用 Java + Spring Boot
- 前端使用 React + Ant Design + Umi
- 集成 Trae AI 能力

# 实现要求
- 后端：创建完整的实体类、DAO、Service、Controller
- 前端：创建对应的页面组件
- 菜单：在 application-data.yml 中添加菜单配置
- 权限：添加对应的权限配置



## 2. 后端代码模板参考

### 2.1 实体类模板
```java
package io.admin.modules.xxx.entity;

import io.github.jiangood.openadmin.Remark;
import io.github.jiangood.openadmin.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

@Remark("实体名称")
@Entity
@Getter
@Setter
@FieldNameConstants
public class EntityName extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Remark("字段名称")
    @Column(length = 100)
    @Size(max = 100, message = "字段长度不能超过100个字符")
    private String fieldName;

    // 其他字段...
}
```

### 2.2 DAO类模板
```java
package io.admin.modules.xxx.dao;

import io.github.jiangood.openadmin.BaseDao;
import io.admin.modules.xxx.entity.EntityName;
import org.springframework.stereotype.Repository;

@Repository
public class EntityNameDao extends BaseDao<EntityName> {

}
```

### 2.3 Service类模板
```java
package io.admin.modules.xxx.service;

import io.github.jiangood.openadmin.BaseService;
import io.admin.modules.xxx.dao.EntityNameDao;
import io.admin.modules.xxx.entity.EntityName;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class EntityNameService extends BaseService<EntityName> {

    @Resource
    private EntityNameDao entityNameDao;

}
```

### 2.4 Controller类模板
```java
package io.admin.modules.xxx.controller;

import io.github.jiangood.openadmin.AjaxResult;
import io.github.jiangood.openadmin.RequestBodyKeys;
import io.github.jiangood.openadmin.framework.perm.HasPermission;
import io.admin.modules.xxx.entity.EntityName;
import io.admin.modules.xxx.service.EntityNameService;
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
@RequestMapping("admin/entityName")
public class EntityNameController {

    @Resource
    private EntityNameService entityNameService;

    @HasPermission("entityName:view")
    @RequestMapping("page")
    public AjaxResult page(String searchText, @PageableDefault(direction = Sort.Direction.DESC, sort = "updateTime") Pageable pageable) throws Exception {
        Spec<EntityName> q = Spec.<EntityName>of().orLike(searchText, "fieldName");
        Page<EntityName> page = entityNameService.findPageByRequest(q, pageable);
        return AjaxResult.ok().data(page);
    }

    @HasPermission("entityName:save")
    @PostMapping("save")
    public AjaxResult save(@RequestBody EntityName input, RequestBodyKeys updateFields) throws Exception {
        entityNameService.saveOrUpdateByRequest(input, updateFields);
        return AjaxResult.ok().msg("保存成功");
    }

    @HasPermission("entityName:delete")
    @RequestMapping("delete")
    public AjaxResult delete(String id) {
        entityNameService.deleteByRequest(id);
        return AjaxResult.ok().msg("删除成功");
    }
}
```

## 3. 前端代码模板参考

### 3.1 前端页面模板
```jsx
import {PlusOutlined} from '@ant-design/icons'
import {Button, Form, Input, Modal, Popconfirm} from 'antd'
import React from 'react'
import {ButtonList, HttpUtils, Page, ProTable} from "@jiangood/open-admin";

export default class extends React.Component {

    state = {
        formValues: {},
        formOpen: false
    }

    formRef = React.createRef()
    tableRef = React.createRef()

    columns = [
        {
            title: '字段名称',
            dataIndex: 'fieldName',
        },
        {
            title: '操作',
            dataIndex: 'option',
            render: (_, record) => (
                <ButtonList>
                    <Button size='small' perm='entityName:save' onClick={() => this.handleEdit(record)}>编辑</Button>
                    <Popconfirm perm='entityName:delete' title='是否确定删除' onConfirm={() => this.handleDelete(record)}>
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

    handleSave = values => {
        HttpUtils.post('admin/entityName/save', values).then(rs => {
            this.setState({formOpen: false})
            this.tableRef.current.reload()
        })
    }

    handleDelete = record => {
        HttpUtils.get('admin/entityName/delete', {id: record.id}).then(rs => {
            this.tableRef.current.reload()
        })
    }

    render() {
        return <Page padding={true}>
            <ProTable
                actionRef={this.tableRef}
                toolBarRender={(params, {selectedRows, selectedRowKeys}) => {
                    return <ButtonList>
                        <Button perm='entityName:save' type='primary' onClick={this.handleAdd}>
                            <PlusOutlined/> 新增
                        </Button>
                    </ButtonList>
                }}
                request={(params) => HttpUtils.get('admin/entityName/page', params)}
                columns={this.columns}
            />

            <Modal title='实体名称'
                   open={this.state.formOpen}
                   onOk={() => this.formRef.current.submit()}
                   onCancel={() => this.setState({formOpen: false})}
                   destroyOnHidden
                   maskClosable={false}
            >
                <Form ref={this.formRef} labelCol={{flex: '100px'}}
                      initialValues={this.state.formValues}
                      onFinish={this.handleSave}
                >
                    <Form.Item name='id' noStyle></Form.Item>

                    <Form.Item label='字段名称' name='fieldName' rules={[{required: true}]}>
                        <Input/>
                    </Form.Item>

                </Form>
            </Modal>
        </Page>
    }
}
```

### 3.2 菜单配置模板
```yaml
data:
  menus:
    - id: entityName
      name: 实体名称
      path: /xxx/entityName
      icon: CopyOutlined
      perms:
        - perm: entityName:view
          name: 查看
        - perm: entityName:delete
          name: 删除
        - perm: entityName:save
          name: 保存
```

