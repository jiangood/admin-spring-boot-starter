# 数据字典管理

本文档介绍了 open-admin 系统中的数据字典管理功能。

## 功能概述

数据字典管理是 open-admin 系统的核心功能之一，提供了统一的数据字典维护和使用机制，确保系统中数据的一致性和规范性。

## 核心概念

### 1. 字典类型

字典类型是数据字典的分类，如性别、状态、行业等，每个字典类型包含多个字典项。

### 2. 字典项

字典项是具体的字典值，如性别中的男、女，状态中的启用、禁用等。

## 功能详解

### 1. 字典类型管理

#### 1.1 字典类型列表

- **功能**：展示系统中的所有字典类型，支持分页、筛选、搜索
- **操作**：查看、编辑、删除、查看字典项

#### 1.2 字典类型创建

- **功能**：创建新字典类型
- **字段**：字典名称、字典编码、描述、状态

#### 1.3 字典类型编辑

- **功能**：修改字典类型信息
- **字段**：字典名称、描述、状态

### 2. 字典项管理

#### 2.1 字典项列表

- **功能**：展示指定字典类型的所有字典项，支持分页、筛选、搜索
- **操作**：查看、编辑、删除、排序

#### 2.2 字典项创建

- **功能**：创建新字典项
- **字段**：字典标签、字典值、排序、状态、描述

#### 2.3 字典项编辑

- **功能**：修改字典项信息
- **字段**：字典标签、字典值、排序、状态、描述

#### 2.4 字典项排序

- **功能**：调整字典项的显示顺序
- **操作**：通过拖拽调整顺序

## 技术实现

### 后端实现

1. **实体类**：`DictType.java`、`Dict.java`
2. **服务类**：`DictTypeService.java`、`DictService.java`
3. **缓存机制**：字典数据缓存，提高查询性能

### 前端实现

1. **页面组件**：`dict-type/index.jsx`、`dict/index.jsx`
2. **字典选择器**：`FieldDictSelect` 组件，用于表单中选择字典值
3. **字典工具**：`DictUtils`，用于前端获取和使用字典数据

## 使用示例

### 1. 后端使用

#### 1.1 获取字典列表

```java
import io.github.jiangood.openadmin.modules.system.service.DictService;

@Autowired
private DictService dictService;

// 根据字典类型获取字典列表
List<Dict> genderList = dictService.getDictListByType("gender");

// 获取字典Map
Map<String, String> genderMap = dictService.getDictMapByType("gender");
```

#### 1.2 字典缓存

系统会自动缓存字典数据，当字典发生变化时，缓存会自动更新。

### 2. 前端使用

#### 2.1 字典选择器

```jsx
import {FieldDictSelect} from "@jiangood/open-admin";

<Form.Item label="性别" name="gender" rules={[{required: true}]}>
    <FieldDictSelect dictType="gender" placeholder="请选择性别"/>
</Form.Item>
```

#### 2.2 字典工具

```jsx
import {DictUtils} from "@jiangood/open-admin";

// 获取字典列表
const genderList = DictUtils.getDictList("gender");

// 获取字典标签
const genderLabel = DictUtils.getDictLabel("gender", "1");

// 获取字典值
const genderValue = DictUtils.getDictValue("gender", "男");
```

## 最佳实践

1. **字典设计**：合理设计字典类型，避免过多或过少的字典类型
2. **字典编码**：使用简洁明了的字典编码，如 `gender`、`status`
3. **字典值**：使用有意义的字典值，如 0 表示禁用，1 表示启用
4. **排序管理**：根据业务需求合理设置字典项的排序
5. **缓存使用**：优先使用前端字典工具获取字典数据，减少后端请求

## 常见问题

### 1. 字典数据不更新

- **原因**：前端缓存未刷新，后端缓存未更新
- **解决方法**：刷新页面，或等待缓存自动过期

### 2. 字典选择器无数据

- **原因**：字典类型不存在，或字典类型下无字典项
- **解决方法**：检查字典类型是否正确，确保字典类型下有字典项

### 3. 字典值重复

- **原因**：同一字典类型下存在相同的字典值
- **解决方法**：修改字典项，确保同一字典类型下字典值唯一