# 前端模块文档

## 前端模块说明

### 组件 (web/src/framework/components)

| 组件名称              | 描述                                     | 主要参数                                                     |
| :-------------------- | :--------------------------------------- | :----------------------------------------------------------- |
| `DownloadFileButton`  | 文件下载按钮，点击后触发文件下载并显示加载提示。 | `url`, `params`, `children`, `...rest` (Ant Design Button Props) |
| `Ellipsis`            | 当文本内容过长时，显示省略号，点击后可查看完整文本。 | `length`, `children`, `pre`                                |
| `LinkButton`          | 链接按钮，点击后通过 `PageUtils.open` 打开指定路径。 | `path`, `label`, `children`, `size`, `...rest` (Ant Design Button Props) |
| `NamedIcon`           | 根据传入的名称动态渲染 Ant Design Icons。 | `name`, `...rest` (Ant Design Icon Props)                    |
| `PageLoading`         | 全屏页面加载提示组件，显示加载动画和提示信息。 | `message`                                                    |

### 表单字段组件 (web/src/framework/field-components)

| 组件名称                        | 描述                                                         | 主要参数                                                     |
| :------------------------------ | :----------------------------------------------------------- | :----------------------------------------------------------- |
| `FieldBoolean`                  | 布尔值输入组件，支持 `select`, `radio`, `checkbox`, `switch` 等多种类型，用于表单中的布尔值字段。 | `value`, `onChange`, `type`                                  |
| `FieldDate`                     | 日期选择组件，根据传入的 `type` 自动渲染不同的日期或时间选择器。 | `type`, `value`, `onChange`, `...rest` (Ant Design DatePicker/TimePicker Props) |
| `FieldDateRange`                | 日期范围选择组件，根据传入的 `type` 自动渲染不同的日期或时间范围选择器。 | `type`, `value`, `onChange`, `...rest` (Ant Design DatePicker.RangePicker/TimePicker.RangePicker Props) |
| `FieldDictSelect`               | 字典选择组件，通过 `typeCode` 从字典工具类中获取选项。       | `value`, `onChange`, `typeCode`, `...rest` (Ant Design Select Props) |
| `FieldEditor`                   | 富文本编辑器，基于 TinyMCE，支持图片上传等功能。             | `value`, `onChange`, `height`, `uploadUrl`, `jsUrl`          |
| `FieldPercent`                  | 百分比输入组件，显示百分比符号，内部将输入值转换为0-1之间的小数。 | `value`, `onChange`, `...rest` (Ant Design InputNumber Props) |
| `FieldRemoteSelect`             | 远程数据选择组件，支持通过搜索文本从远程接口异步加载选项。   | `url`, `value`, `onChange`, `placeholder`, `...rest` (Ant Design Select Props) |
| `FieldRemoteSelectMultiple`     | 远程数据多选组件，支持通过搜索文本从远程接口异步加载选项，并支持多选。 | `url`, `value`, `onChange`, `placeholder`, `...rest` (Ant Design Select Props) |
| `FieldRemoteSelectMultipleInline` | 远程数据多选组件，`value` 是逗号分隔的字符串，内部进行数组和字符串的转换。 | `url`, `value`, `onChange`, `placeholder`, `...rest` (Ant Design Select Props) |
| `FieldRemoteTree`               | 远程树形选择组件，从指定URL加载树形数据，并支持多选。       | `url`, `value`, `onChange`, `...rest` (Ant Design Tree Props) |
| `FieldRemoteTreeCascader`       | 远程树形级联选择组件，从指定URL加载树形数据，并以级联选择器形式展示。 | `url`, `value`, `onChange`, `...rest` (Ant Design Cascader Props) |
| `FieldRemoteTreeSelect`         | 远程树形选择器组件，从指定URL加载树形数据，并以可搜索的下拉树形式展示。 | `url`, `value`, `onChange`, `treeDefaultExpandAll`, `...rest` (Ant Design TreeSelect Props) |
| `FieldRemoteTreeSelectMultiple` | 远程树形多选选择器组件，从指定URL加载树形数据，并以可搜索的下拉树形式展示，支持多选。 | `url`, `value`, `onChange`, `treeDefaultExpandAll`, `...rest` (Ant Design TreeSelect Props) |
| `FieldSysOrgTree`               | 组织机构树选择组件，封装了 `FieldRemoteTree`，用于显示部门树或单位树。 | `type`, `...rest` (FieldRemoteTree Props)                    |
| `FieldSysOrgTreeSelect`         | 组织机构树选择器组件，封装了 `FieldRemoteTreeSelect`，用于显示部门树或单位树的选择器。 | `type`, `...rest` (FieldRemoteTreeSelect Props)              |
| `FieldTable`                    | 可编辑表格组件，支持动态增删行，并对单元格内容进行编辑。     | `columns`, `value`, `onChange`, `style`, `...rest` (Ant Design Table Props) |
| `FieldTableSelect`              | 下拉表格选择组件，在一个 Select 组件中嵌入 ProTable，实现从表格数据中选择值。 | `url`, `columns`, `value`, `onChange`, `placeholder`, `...rest` (Ant Design Select Props) |
| `FieldUploadFile`               | 文件上传组件，支持单文件/多文件上传，图片裁剪，以及文件预览。 | `value`, `onChange`, `onFileChange`, `maxCount`, `cropImage`, `listType`, `accept`, `cropperProps` |

### 工具类 (web/src/framework/utils)

| 工具类名称      | 描述                                                         | 主要方法/参数                                                |
| :-------------- | :----------------------------------------------------------- | :----------------------------------------------------------- |
| `ArrUtils`      | 数组工具类，提供了一系列对数组进行操作的静态方法，包括检查、添加、移除、清空、截取、交换、去重等。 | `contains`, `add`, `remove`, `unique`, `maxBy` 等静态方法    |
| `ColorsUtils`   | 颜色工具类，提供 RGB/HSV/Hex 颜色之间的转换、颜色亮度/感知亮度计算、颜色混合、alpha 透明度调整等功能。 | `rgbToHex`, `hexToRgb`, `lighten`, `brightness`, `blend` 等静态方法 |
| `DateUtils`     | 日期时间工具类，提供日期时间的格式化、获取特定部分、获取当前日期时间、以及友好的时间显示和总耗时计算。 | `formatDate`, `now`, `friendlyTime`, `friendlyTotalTime` 等静态方法 |
| `DeviceUtils`   | 设备和浏览器相关的工具类，提供判断是否为移动设备和获取 WebSocket 基础 URL 的功能。 | `isMobileDevice`, `getWebsocketBaseUrl` 等静态方法           |
| `DomUtils`      | DOM 操作工具类，提供获取元素相对于视口的偏移量、外部高度和外部宽度。 | `offset`, `height`, `width` 等静态方法                       |
| `EventBusUtils` | 静态事件总线工具类，提供事件的注册、触发和移除功能，支持一次性监听和指定上下文。 | `on`, `once`, `emit`, `off` 等静态方法                       |
| `MessageUtils`  | 消息工具类，封装了 Ant Design 的 `Modal`, `message`, 和 `notification` 组件，提供了统一的 Alert、Confirm、Prompt、成功/失败/警告/信息提示、加载提示和通知提醒功能。 | `alert`, `confirm`, `success`, `error`, `notify` 等静态方法  |
| `ObjectUtils`   | 对象工具类，提供安全地获取深度嵌套的属性值、根据目标对象结构复制源对象中存在的属性、以及复制源对象中非 undefined 的属性值到目标对象的功能。 | `get`, `copyPropertyIfPresent`, `copyProperty` 等静态方法    |
| `StorageUtils`  | 存储工具类，使用 `localStorage` 进行数据的存取，并包含时间戳和默认值处理。 | `get`, `set` 等静态方法                                      |
| `StringUtils`   | 字符串工具类，提供字符串的移除前缀/后缀、随机生成、空值处理、包含/计数、大小写转换、反转、截取、混淆、填充、简单加解密、显示宽度计算以及下划线/驼峰命名转换、忽略大小写比较、分割和连接等功能。 | `removePrefix`, `random`, `contains`, `ellipsis`, `toCamelCase`, `split`, `join` 等静态方法 |
| `TreeUtils`     | 树结构操作工具类，提供将数组和树结构互相转换、遍历、查找、构建树、以及获取节点路径等功能。 | `treeToList`, `buildTree`, `walk`, `findByKey`, `getKeyList` 等静态方法 |
| `UrlUtils`      | URL 工具类，提供获取 URL 参数、获取路径名、参数对象转换为查询字符串、设置/替换 URL 参数以及连接路径片段的功能。 | `getParams`, `setParam`, `join` 等静态方法                   |
| `UuidUtils`     | UUID 工具类，提供生成符合 v4 标准的 UUID 字符串的功能。      | `uuidV4` 等静态方法                                          |
| `ValidateUtils` | 验证工具类，提供检查字符串是否为有效电子邮件地址的功能。     | `isEmail` 等静态方法                                         |

### 视图组件 (web/src/framework/view-components)

| 组件名称      | 描述                                     | 主要参数 |
| :------------ | :--------------------------------------- | :------- |
| `ViewBoolean` | 布尔值显示组件，将 `true` 显示为“是”，`false` 显示为“否”，`null` 或 `undefined` 不显示。 | `value`  |
| `ViewPassword` | 密码显示组件，默认隐藏密码为 `******`，提供切换按钮显示/隐藏明文密码。 | `value`  |
