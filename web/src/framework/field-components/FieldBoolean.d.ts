import React from 'react';
import { SelectProps } from 'antd/es/select';

export interface FieldBooleanProps  {

    type?: 'radio' | 'checkbox' | 'select' | 'switch';
    value?: boolean;
    onChange?: (value: boolean) => void;

}

export class FieldBoolean extends React.Component<FieldBooleanProps, any> {}

