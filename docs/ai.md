# 预备知识
- 文档地址 https://jiangood.github.io/open-admin
- 分析框架已有功能，已有功能请不要重复开发
- 分析需求，分析出需要的实体
- 前端页面符合umijs，antd的编码规则，页面文件使用小写开头，并且确定路径和 application-data.yml 文件中的菜单定义的路径保持一致
- 前端尽量使用react的类模式，参考模板中的数据全球，表格显示等，代码尽量放到一个jsx中
- 后端实体需要必要的注释
- 代码实现：优先使用用户指定的语言/框架
- 代码应包含必要的注释、异常处理和日志记录。
- 最后重新检查代码，确保导入的公共类与模板相同



#  代码模板
## 实体代码模板
文件路径：`src/main/java/io/admin/modules/system/entity/User.java`
```java
package io.admin.modules.system.entity;

import io.github.jiangood.openadmin.Remark;
import io.github.jiangood.openadmin.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

@Remark("用户信息")
@Entity
@Getter
@Setter
@FieldNameConstants
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Remark("名称")
    @Column(length = 100)
    @Size(max = 100, message = "名称长度不能超过100个字符")
    private String name;

    @Remark("爱好")
    private String fav;
}
```


## DAO类模板 (UserDao.java)
文件路径：`src/main/java/io/admin/modules/system/dao/UserDao.java`

```java
package io.admin.modules.system.dao;

import io.github.jiangood.openadmin.BaseDao;
import io.admin.modules.system.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends BaseDao<User> {

}
```

## Service类模板 (UserService.java)
文件路径：`src/main/java/io/admin/modules/system/service/UserService.java`

```java
package io.admin.modules.system.service;

import io.github.jiangood.openadmin.BaseService;
import io.admin.modules.system.dao.UserDao;
import io.admin.modules.system.entity.User;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User> {

    @Resource
    private UserDao userDao;

}
```

## Controller类模板 (UserController.java)
文件路径：`src/main/java/io/admin/modules/system/controller/UserController.java`

```java
package io.admin.modules.system.controller;

import io.github.jiangood.openadmin.AjaxResult;
import io.github.jiangood.openadmin.RequestBodyKeys;
import io.github.jiangood.openadmin.framework.perm.HasPermission;
import io.github.jiangood.openadmin.Spec;
import io.admin.modules.system.entity.User;
import io.admin.modules.system.service.UserService;
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
@RequestMapping("admin/user")
public class UserController {

    @Resource
    private UserService service;

    @HasPermission("user:view")
    @RequestMapping("page")
    public AjaxResult page(String searchText, @PageableDefault(direction = Sort.Direction.DESC, sort = "updateTime") Pageable pageable) throws Exception {
        Spec<User> q = Spec.<User>of().orLike(searchText, "name");

        Page<User> page = service.findPageByRequest(q, pageable);

        return AjaxResult.ok().data(page);
    }

    @HasPermission("user:save")
    @PostMapping("save")
    public AjaxResult save(@RequestBody User input, RequestBodyKeys updateFields) throws Exception {
        service.saveOrUpdateByRequest(input, updateFields);
        return AjaxResult.ok().msg("保存成功");
    }

    @HasPermission("user:delete")
    @RequestMapping("delete")
    public AjaxResult delete(String id) {
        service.deleteByRequest(id);
        return AjaxResult.ok().msg("删除成功");
    }

    /**
     * 下拉选择框的数据源，主要供前端组件 FieldRemoteSelect等使用
     * @param searchText
     * @return data格式为：[{value:1,label:'xxx'}]
     */
    @HasPermission("user:view")
    @GetMapping("options")
    public AjaxResult options(String searchText) {
        Spec<User> q = Spec.<User>of().orLike(searchText, "name");
        List<User> list = service.findAll(q, Sort.by("name"));
        List<Option> options = list.stream().map(a -> Option.of(a.getId(), a.getName())).toList();
        return AjaxResult.ok().data(options);
    }
}
```

## 前端页面模板 (index.jsx)
文件路径：`web/src/pages/system/user/index.jsx`

```jsx
import {PlusOutlined} from '@ant-design/icons'
import {Button, Form, Input, Modal, Popconfirm} from 'antd'
import React from 'react'
import {ButtonList, FieldUploadFile, HttpUtils, Page, ProTable} from "@jiangood/open-admin";

export default class extends React.Component {

    state = {
        formValues: {},
        formOpen: false
    }

    formRef = React.createRef()
    tableRef = React.createRef()

    columns = [
        {
            title: '名称',
            dataIndex: 'name',
        },
       
       
        {
            title: '操作',
            dataIndex: 'option',
            render: (_, record) => (
                <ButtonList>
                    <Button size='small' perm='user:save' onClick={() => this.handleEdit(record)}>编辑</Button>
                    <Popconfirm perm='user:delete' title='是否确定删除用户信息'  onConfirm={() => this.handleDelete(record)}>
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
        HttpUtils.post('admin/user/save', values).then(rs => {
            this.setState({formOpen: false})
            this.tableRef.current.reload()
        })
    }

    handleDelete = record => {
        HttpUtils.get('admin/user/delete', {id: record.id}).then(rs => {
            this.tableRef.current.reload()
        })
    }

    render() {
        return <Page padding={true}>
            <ProTable
                actionRef={this.tableRef}
                toolBarRender={(params, {selectedRows, selectedRowKeys}) => {
                    return <ButtonList>
                        <Button perm='user:save' type='primary' onClick={this.handleAdd}>
                            <PlusOutlined/> 新增
                        </Button>
                    </ButtonList>
                }}
                request={(params) => HttpUtils.get('admin/user/page', params)}
                columns={this.columns}
            />

            <Modal title='用户信息'
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

                    <Form.Item label='名称' name='name' rules={[{required: true}]}>
                        <Input/>
                    </Form.Item>

                    <Form.Item label='爱好' name='fav' >
                        <Input/>
                    </Form.Item>


                </Form>
            </Modal>
        </Page>
    }
}
```

## 菜单配置模板 (application-data.yml)
文件路径：`src/main/resources/application-data.yml`

```yaml
data:
  menus:
    - id: user
      name: 用户信息
      path: /system/user
      icon: CopyOutlined
      perms:
        - perm: user:view
          name: 查看
        - perm: user:delete
          name: 删除
        - perm: user:save
          name: 保存
```
