# 首页

## 开发环境

- **Maven 版本**: 0.0.2
  ![Maven Version](https://img.shields.io/maven-central/v/io.github.jiangood/springboot-admin-starter)
- **NPM 版本**: 0.0.1-beta.30
  ![NPM Version](https://img.shields.io/npm/v/springboot-admin-starter)

## 后端依赖

以下是项目的主要后端依赖：

- `org.springframework.boot:spring-boot-starter-web`
- `org.springframework.boot:spring-boot-starter-quartz`
- `org.springframework.boot:spring-boot-starter-validation`
- `org.springframework.boot:spring-boot-starter-aop`
- `org.springframework.boot:spring-boot-starter-data-jpa`
- `org.springframework.boot:spring-boot-starter-cache`
- `org.springframework.boot:spring-boot-starter-security`
- `org.springframework.boot:spring-boot-configuration-processor`
- `org.mapstruct:mapstruct`
- `com.jhlabs:filters`
- `io.minio:minio`
- `com.squareup.okhttp3:okhttp-jvm`
- `javax.mail:mail`
- `org.apache.poi:poi-ooxml`
- `org.apache.poi:poi-scratchpad`
- `com.itextpdf:itextpdf`
- `com.github.f4b6a3:uuid-creator`
- `commons-dbutils:commons-dbutils`
- `cn.hutool:hutool-all`
- `org.apache.commons:commons-lang3`
- `com.google.guava:guava`
- `commons-io:commons-io`
- `com.fasterxml.jackson.dataformat:jackson-dataformat-yaml`
- `commons-beanutils:commons-beanutils`
- `com.belerweb:pinyin4j`
- `org.jsoup:jsoup`
- `org.projectlombok:lombok`
- `com.mysql:mysql-connector-j`
- `io.github.jiangood:ureport-console`
- `org.flowable:flowable-spring-boot-starter-process`

## 前端依赖

以下是项目的主要前端依赖 (peerDependencies 部分):

- `@ant-design/icons`
- `@bpmn-io/properties-panel`
- `@tinymce/tinymce-react`
- `@umijs/types`
- `antd`
- `antd-img-crop`
- `axios`
- `bpmn-js`
- `bpmn-js-properties-panel`
- `preact`
- `dayjs`
- `jsencrypt`
- `lodash`
- `qs`
- `react`
- `react-dom`
- `umi`

## 菜单列表

系统预设的菜单结构如下：

- 我的任务 (`myFlowableTask`)
- 系统管理 (`sys`)
  - 机构管理 (`sysOrg`)
  - 用户管理 (`sysUser`)
  - 角色管理 (`sysRole`)
  - 操作手册 (`sysManual`)
  - 系统配置 (`sysConfig`)
  - 数据字典 (`sysDict`)
  - 存储文件 (`sysFile`)
  - 作业调度 (`job`)
  - 操作日志 (`sysLog`)
  - 接口管理 (`api`)
  - 流程引擎 (`flowableModel`)
  - 报表管理 (`ureport`)

## 业务项目如何配置

业务项目需要在 `application-data-biz.yml` 中进行相应的配置。

## 其他文档

- [前端模块](front.md)
- [后端模块](back.md)
- [功能模块](template.md)
