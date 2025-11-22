import React from 'react';
import { SelectProps } from 'antd/es/select';

export interface FcRemoteSelectMultipleProps extends Omit<SelectProps, 'options' | 'children'> {

    /**
     * 请求地址
     */
    url: string ;
}

export class FcRemoteSelectMultiple extends React.Component<FcRemoteSelectMultipleProps, any> {}

