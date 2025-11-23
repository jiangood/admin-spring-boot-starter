import React from 'react';
import { SelectProps } from 'antd/es/select';
import {TreeProps} from "antd";

export interface FieldRemoteTreeCascaderProps  {

    /**
     * 请求地址
     * 要求返回数据格式为：tree
     */
    url: string ;

    value?: string;
    onChange?: (value: string) => void;
}

/**
 * 远程树级联选择器, 类似select，但是树级联
 *
 * 注意，value为非数组形式，区别于cascader组件
 */
export class FieldRemoteTreeCascader extends React.Component<FieldRemoteTreeCascaderProps, any> {}

