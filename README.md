# Open Admin

## 项目简介

Open Admin 是一个功能强大的前后端一体化管理系统框架，基于 Spring Boot + React + Ant Design + Umi 技术栈，为企业级应用提供快速开发解决方案。

## 技术栈

### 后端
- Spring Boot
- Hibernate/JPA
- Maven

### 前端
- React
- Ant Design
- Umi
- TypeScript

## 核心功能

### 后端核心功能
- 基础数据模型和服务
- 权限管理系统
- 日志管理
- 数据验证
- 字典管理
- 定时任务
- 丰富的工具类库

### 前端核心功能
- 响应式布局
- 表单组件
- 表格组件
- 权限控制
- 字典选择器
- 文件上传
- 富文本编辑器
- 系统管理页面

## 快速开始

### 使用示例项目

```bash
git clone https://github.com/jiangood/open-admin-example.git
cd open-admin-example
# 后端启动
mvn spring-boot:run
# 前端启动
cd web
npm install
npm run dev
```

## 安装和使用

### 后端依赖

![Maven Version](https://img.shields.io/maven-central/v/io.github.jiangood/open-admin)

```xml
<dependency>
  <groupId>io.github.jiangood</groupId>
  <artifactId>open-admin</artifactId>
  <version>最新版本</version>
</dependency>
```

### 前端依赖

![NPM Version](https://img.shields.io/npm/v/@jiangood/open-admin)

```bash
npm install @jiangood/open-admin
```

## 目录结构

```
├── src/             # 后端源码
│   ├── main/java/io/github/jiangood/openadmin/
│   │   ├── dto/     # 数据传输对象
│   │   ├── framework/  # 框架核心
│   │   ├── lang/    # 工具类库
│   │   └── modules/  # 功能模块
│   └── main/resources/  # 后端资源
├── web/             # 前端源码
│   ├── src/         # 前端源码
│   │   ├── components/  # 组件
│   │   ├── pages/    # 页面
│   │   └── utils/    # 工具类
│   └── package.json  # 前端依赖
├── docs/            # 文档
└── README.md        # 项目说明
```

## 文档

- [智能体](docs/智能体.md)
- [编码规范](docs/编码规范.md)

## 快照版本

```xml
<repository>
    <name>Central Portal Snapshots</name>
    <id>central-portal-snapshots</id>
    <url>https://central.sonatype.com/repository/maven-snapshots/</url>
    <releases>
        <enabled>false</enabled>
    </releases>
    <snapshots>
        <enabled>true</enabled>
    </snapshots>
</repository>
```

## 贡献指南

欢迎贡献代码和提出问题！请遵循以下步骤：

1. Fork 项目
2. 创建分支
3. 提交更改
4. 发起 Pull Request

## 许可证

本项目采用 MIT 许可证。
