# 文档

## 介绍

本文档旨在介绍 `springboot-admin-starter` 项目的结构、依赖以及如何使用。

### 开发环境

**Maven 版本:** 0.0.2
**NPM 版本:** 0.0.1-beta.30

### 后端依赖

| GroupId | ArtifactId | Version |
| :------ | :--------- | :------ |
| org.springframework.boot | spring-boot-starter-web | |
| org.springframework.boot | spring-boot-starter-quartz | |
| org.springframework.boot | spring-boot-starter-validation | |
| org.springframework.boot | spring-boot-starter-aop | |
| org.springframework.boot | spring-boot-starter-data-jpa | |
| org.springframework.boot | spring-boot-starter-cache | |
| org.springframework.boot | spring-boot-starter-security | |
| org.springframework.boot | spring-boot-configuration-processor | |
| org.mapstruct | mapstruct | ${mapstruct.version} (1.6.3) |
| org.mapstruct | mapstruct-processor | ${mapstruct.version} (1.6.3) |
| com.jhlabs | filters | 2.0.235-1 |
| io.minio | minio | 8.6.0 |
| com.squareup.okhttp3 | okhttp-jvm | 5.1.0 |
| javax.mail | mail | 1.4.7 |
| org.apache.poi | poi-ooxml | ${poi.version} (5.5.0) |
| org.apache.poi | poi-scratchpad | ${poi.version} (5.5.0) |
| com.itextpdf | itextpdf | 5.5.13.4 |
| com.github.f4b6a3 | uuid-creator | 6.1.1 |
| commons-dbutils | commons-dbutils | 1.8.1 |
| cn.hutool | hutool-all | ${hutool.version} (5.8.41) |
| org.apache.commons | commons-lang3 | |
| com.google.guava | guava | ${guava.version} (33.5.0-jre) |
| commons-io | commons-io | ${commons-io.version} (2.21.0) |
| com.fasterxml.jackson.dataformat | jackson-dataformat-yaml | |
| commons-beanutils | commons-beanutils | 1.11.0 |
| com.belerweb | pinyin4j | 2.5.1 |
| org.jsoup | jsoup | 1.21.2 |
| org.projectlombok | lombok | |
| com.mysql | mysql-connector-j | |
| io.github.jiangood | ureport-console | 1.0.4 |
| org.flowable | flowable-spring-boot-starter-process | 7.2.0 |
| org.springframework.boot | spring-boot-starter-test | |
| org.springframework.security | spring-security-test | |
| com.h2database | h2 | |

### 前端依赖 (peerDependencies)

| Package | Version |
| :------ | :------ |
| @ant-design/icons | ^5.4.0 |
| @bpmn-io/properties-panel | ^3.34.0 |
| @tinymce/tinymce-react | ^6.0.0 |
| @umijs/types | ^3.5.43 |
| antd | ^6.0.0 |
| antd-img-crop | ^4.23.0 |
| axios | ^1.13.2 |
| bpmn-js | ^18.7.0 |
| bpmn-js-properties-panel | ^5.43.0 |
| preact | ^10.27.2 |
| dayjs | ^1.11.13 |
| jsencrypt | ^3.5.4 |
| lodash | ^4.17.21 |
| qs | ^6.14.0 |
| react | ^19.0.0 |
| react-dom | ^19.0.0 |
| umi | ^4.0.0 |

### 菜单配置及业务项目配置

`springboot-admin-starter` 框架的菜单和配置项在 `application-data-framework.yml` 中定义。业务项目可以通过创建 `application-data-biz.yml` 文件来覆盖或扩展这些配置。

#### 框架配置项示例

- **邮件配置**: `email.from`, `email.pass`
- **网站配置**: `sys.copyright`, `sys.loginBackground`, `sys.loginBoxBottomTip`, `sys.title`, `sys.waterMark` (布尔类型，用于控制水印显示)
- **系统配置**: `sys.baseUrl`, `sys.jwtSecret`

#### 菜单列表示例

以下是 `application-data-framework.yml` 中定义的菜单结构示例：

-   **我的任务** (`myFlowableTask`)
    -   路径: `/flowable/task`
    -   权限: `myFlowableTask:manage`
-   **系统管理** (`sys`)
    -   **机构管理** (`sysOrg`)
        -   路径: `/system/org`
        -   图标: `ApartmentOutlined`
        -   权限: `sysOrg:save`, `sysOrg:view`, `sysOrg:delete`
    -   **用户管理** (`sysUser`)
        -   路径: `/system/user`
        -   图标: `UserOutlined`
        -   权限: `sysUser:view`, `sysUser:save`, `sysUser:delete`, `sysUser:resetPwd`, `sysUser:grantPerm`
    -   **角色管理** (`sysRole`)
        -   路径: `/system/role`
        -   图标: `IdcardOutlined`
        -   权限: `sysRole:save`
    -   **操作手册** (`sysManual`)
        -   路径: `/system/sysManual`
        -   图标: `CopyOutlined`
        -   权限: `sysManual:view`, `sysManual:delete`, `sysManual:save`
    -   **系统配置** (`sysConfig`)
        -   路径: `/system/config`
        -   图标: `SettingOutlined`
        -   权限: `sysConfig:view`, `sysConfig:save`
    -   **数据字典** (`sysDict`)
        -   路径: `/system/dict`
        -   图标: `FileSearchOutlined`
        -   权限: `sysDict:view`, `sysDict:save`, `sysDict:delete`
    -   **存储文件** (`sysFile`)
        -   路径: `/system/file`
        -   图标: `FolderOpenOutlined`
        -   权限: `sysFile:view`, `sysFile:delete`
    -   **作业调度** (`job`)
        -   路径: `/system/job`
        -   图标: `OrderedListOutlined`
        -   权限: `job:view`, `job:save`, `job:triggerJob`, `job:jobLogClean`
    -   **操作日志** (`sysLog`)
        -   路径: `/system/log`
        -   图标: `FileSearchOutlined`
        -   权限: `sysLog:view`
    -   **接口管理** (`api`)
        -   路径: `/system/api`
        -   图标: `ApiOutlined`
        -   权限: `api`
    -   **流程引擎** (`flowableModel`)
        -   路径: `/flowable`
        -   权限: `flowableModel:design`, `flowableModel:deploy`, `flowableTask:setAssignee`, `flowableInstance:close`
    -   **报表管理** (`ureport`)
        -   路径: `/ureport`
        -   图标: `TableOutlined`
        -   权限: `ureport:view`, `ureport:design`

### 其他文档

*   [前端文档](#前端)
*   [后端文档](#后端)
*   [业务代码模板](#模板)

## 前端

## 后端

## 模板