import React from 'react';

export interface FieldRemoteTreeSelectProps  {

    /**
     * 请求地址
     */
    url: string ;

    /**
     * 默认展开所有
     */
    treeDefaultExpandAll?: boolean ;
}

export class FieldRemoteTreeSelect extends React.Component<FieldRemoteTreeSelectProps, any> {}

