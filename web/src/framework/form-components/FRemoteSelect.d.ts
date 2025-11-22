import React from 'react';
import { SelectProps } from 'antd/es/select';

export interface RemoteSelectProps extends Omit<SelectProps, 'options' | 'children'> {

    /**
     * 请求地址
     */
    url: string ;
}

declare class RemoteSelect extends React.Component<RemoteSelectProps, any> {}

export default RemoteSelect;