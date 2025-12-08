import React from 'react';
import { SelectProps } from 'antd/es/select';

export interface FieldDictSelectProps extends Omit<SelectProps, 'options' | 'children'|'mode'> {

    /**
     * 请求地址
     */
    typeCode: string ;
}

export class FieldDictSelect extends React.Component<FieldDictSelectProps, any> {}

