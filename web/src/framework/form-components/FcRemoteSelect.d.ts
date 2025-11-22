import React from 'react';
import { SelectProps } from 'antd/es/select';

export interface FcRemoteSelectProps extends Omit<SelectProps, 'options' | 'children'> {

    /**
     * 请求地址
     */
    url: string ;
}

export class FcRemoteSelect extends React.Component<FcRemoteSelectProps, any> {}

