import React from 'react';

export interface FieldRemoteTreeSelectMultipleProps  {

    /**
     * 请求地址
     */
    url: string ;

    /**
     * 默认展开所有
     */
    treeDefaultExpandAll?: boolean ;
}

export class FieldRemoteTreeSelectMultiple extends React.Component<FieldRemoteTreeSelectMultipleProps, any> {}

