import React from 'react';
import { SelectProps } from 'antd/es/select';
import {TreeProps} from "antd";

export interface FieldRemoteTreeProps  {

    /**
     * 请求地址
     */
    url: string ;
}

/**
 * 多选树
 *
 * 区别于下拉框，是扁平展示的树
 * 这种需要扁平展示的树，通常都是多选。
 *
 */
export class FieldRemoteTree extends React.Component<FieldRemoteTreeProps, any> {}

