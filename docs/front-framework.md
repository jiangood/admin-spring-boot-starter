## 视图组件 (view-components)

### `ViewBoolean`

*   **组件名称**: `ViewBoolean`
*   **描述**: 这是一个简单的布尔值显示组件，将 `true` 显示为 '是'，将 `false` 显示为 '否'。如果值为 `null` 或 `undefined`，则不渲染任何内容。
*   **参数 (Props)**:
    *   `value`: `boolean | null | undefined`, 要显示的布尔值。
*   **示例**:

    ```jsx
    import React from 'react';
    import { ViewBoolean } from '@/framework/view-components';

    const MyComponent = () => {
      return (
        <div>
          <p>状态为 True: <ViewBoolean value={true} /></p>
          <p>状态为 False: <ViewBoolean value={false} /></p>
          <p>状态为 Null: <ViewBoolean value={null} /></p>
          <p>状态为 Undefined: <ViewBoolean value={undefined} /></p>
        </div>
      );
    };

    export default MyComponent;
    ```

### `ViewPassword`

*   **组件名称**: `ViewPassword`
*   **描述**: 这是一个密码显示组件，默认隐藏密码，点击眼睛图标可以切换密码的可见性。
*   **参数 (Props)**:
    *   `value`: `string | null | undefined`, 要显示的密码值。
*   **示例**:

    ```jsx
    import React from 'react';
    import { ViewPassword } from '@/framework/view-components';

    const MyComponent = () => {
      return (
        <div>
          <p>用户密码: <ViewPassword value="mySecretPassword123" /></p>
          <p>空密码: <ViewPassword value={null} /></p>
        </div>
      );
    };

    export default MyComponent;
    ```