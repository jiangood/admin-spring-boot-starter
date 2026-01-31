# open-admin

## 项目介绍

open-admin 是一个小型管理系统框架，提供了一整套前后端开箱即用的解决方案，包括用户管理、角色权限、数据字典、作业调度、流程引擎等功能。

本框架旨在简化企业级管理系统开发，提供了完整的权限体系、数据管理、作业调度和工作流引擎，支持快速构建各类管理后台系统。

## 核心功能

1. **用户权限管理**：完整的用户、角色、权限体系
2. **数据字典管理**：统一的数据字典维护和使用
3. **作业调度**：基于Quartz的定时任务管理
4. **文件管理**：统一的文件上传、存储和管理
5. **系统配置**：灵活的系统参数配置
6. **操作日志**：完整的操作日志记录
7. **报表管理**：集成UReport报表引擎
8. **流程引擎**：内置工作流引擎，支持自定义流程

## 技术栈

- **前端**：React、Ant Design、UmiJS
- **后端**：Java、Spring Boot、JPA、Quartz
- **数据库**：MySQL
- **流程引擎**：内置工作流引擎
- **报表引擎**：UReport

## 文档导航

- **[快速开始](./site/quick-start.md)**：项目环境搭建和基础使用
- **[架构设计](./site/architecture.md)**：系统架构和设计理念
- **[前端文档](./site/frontend/)**：前端组件和工具类使用指南
  - [组件库](./site/frontend/components.md)
  - [工具类](./site/frontend/utils.md)
  - [字段组件](./site/frontend/field-components.md)
- **[后端文档](./site/backend/)**：后端模块和工具使用指南
  - [数据规范](./site/backend/data-spec.md)
  - [工具类](./site/backend/utils.md)
  - [注解](./site/backend/annotations.md)
  - [验证器](./site/backend/validators.md)
- **[开发指南](./site/guide/)**：开发规范和最佳实践
  - [编码规范](./site/guide/coding-standard.md)
  - [智能体使用](./site/guide/agent.md)
- **[配置说明](./site/config/)**：系统配置和环境变量
- **[功能模块](./site/modules/)**：核心功能模块详细说明

## 系统要求

- JDK 17+
- MySQL 8.0+
- Node.js 16+
- npm 7+

## 快速开始

请参考 [快速开始](./site/quick-start.md) 文档，了解如何搭建项目环境和开始使用。

## 许可证

MIT License

## 贡献

欢迎提交 Issue 和 Pull Request 来帮助改进这个项目！