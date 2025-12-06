# 项目文档

## 首页

### 项目信息

| 项目名称        | springboot-admin-starter          |
| :-------------- | :-------------------------------- |
| 描述            | 小型管理系统框架                  |

### 引用方式

| 类型 | 说明                                                                                                                                                                                                                                    | 版本                                                                                             |
| :--- | :-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | :----------------------------------------------------------------------------------------------- |
| Maven | ```xml
<dependency>
    <groupId>io.github.jiangood</groupId>
    <artifactId>springboot-admin-starter</artifactId>
    <version>${project.version}</version>
</dependency>
``` | <img src="https://img.shields.io/maven-central/v/io.github.jiangood/springboot-admin-starter.svg?label=Maven%20Central" alt="Maven Central" /> |
| NPM  | ```bash
npm install @jiangood/springboot-admin-starter
```                                                                                                                             | <img src="https://img.shields.io/npm/v/@jiangood/springboot-admin-starter.svg" alt="npm version" /> |

### 开发环境

| 类型 | 环境版本 | 文档链接 |
| :--- | :------- | :------- |
| 后端 | Java 17  |          |
| 前端 | TypeScript 5.x  |          |

### 项目依赖

#### 后端依赖 (Maven)

-   `spring-boot-starter-web`: Web应用开发
-   `spring-boot-starter-data-jpa`: 数据持久化
-   `spring-boot-starter-security`: 安全框架
-   `hutool-all`: Java工具类库
-   `guava`: Google核心库
-   `poi-ooxml`: Office文档处理
-   `minio`: 文件存储

#### 前端依赖 (NPM)

-   `react`: 前端UI库
-   `antd`: Ant Design UI组件库
-   `axios`: HTTP客户端
-   `umi`: 可插拔的企业级前端框架
-   `dayjs`: 轻量级日期处理库

### 核心配置项说明

`src/main/resources/application-data-framework.yml` 文件定义了框架的核心配置项和菜单结构。

#### 配置项 (`data.configs`)

| 配置组名称 | 配置项 Code   | 配置项 Name | 描述                                       |
| :--------- | :------------ | :---------- | :----------------------------------------- |
| 邮件配置   | email.from    | 邮件发送账号 |                                            |
| 邮件配置   | email.pass    | 邮件发送密码 |                                            |
| 网站配置   | sys.copyright | 版权信息    |                                            |
| 网站配置   | sys.loginBackground | 登录背景图  |                                            |
| 网站配置   | sys.loginBoxBottomTip | 登录框下面的提示 |                                            |
| 网站配置   | sys.title     | 站点标题    |                                            |
| 网站配置   | sys.waterMark | 开启水印    | 在所有页面增加水印 (类型: boolean)         |
| 系统配置   | sys.baseUrl   | 请求基础地址 | 非必填，可用于拼接完整请求地址             |
| 系统配置   | sys.jwtSecret | jwt密码     |                                            |

#### 菜单配置 (`data.menus`)

文件中定义了如“我的任务”、“系统管理”、“机构管理”、“用户管理”、“角色管理”、“操作手册”、“系统配置”、“数据字典”、“存储文件”、“作业调度”、“操作日志”、“接口管理”、“流程引擎”、“报表管理”等菜单项及其子菜单、路径、图标和权限。这些菜单构成了系统的主要导航结构。