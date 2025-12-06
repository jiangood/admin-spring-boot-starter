# 后端模块

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

## 树结构工具类 (`io.admin.common.utils.tree`)

- **TreeManager.java**: 树结构管理类，用于构建和操作树形数据。
- **TreeNode.java**: 树节点定义类，描述了树中每个节点的基本属性。
- **TreeUtils.java**: 树结构操作工具类，提供了一系列处理树形结构的实用方法。

## JPA Specification 构建器 (`io.admin.framework.data.specification.Spec.java`)

`Spec.java` 是一个强大的 JPA `Specification` 构建器。它提供了简洁、动态的查询构建能力，并支持关联字段（例如 "dept.name"）的查询。通过链式调用，可以方便地收集各种 `Specification` 条件，并最终通过 `AND` 逻辑连接所有条件。

主要特性包括：

- **丰富的操作符支持**: 支持 `EQUAL`, `LIKE`, `IN`, `BETWEEN`, `IS_NULL` 等多种查询操作符。
- **动态查询**: 允许根据业务逻辑动态添加和组合查询条件。
- **关联字段查询**: 能够通过点操作符查询关联实体中的字段。
- **逻辑组合**: 支持 `OR` 条件、`NOT` 操作，以及针对模糊查询的 `orLike` 方法。
- **聚合函数与分组**: 提供了 `groupBy` 和 `having` 方法，支持在查询中使用聚合函数和分组条件。
- **Example查询**: 支持通过 `Example` 对象进行查询。
- **集合成员查询**: `isMember` 和 `isNotMember` 方法用于检查元素是否属于实体集合字段的成员。