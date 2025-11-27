import React from 'react';

/**
 *  多选，但是值是字符串，逗号破解的
 */
export interface FieldRemoteSelectMultipleInlineProps  {

    /**
     * 请求地址
     */
    url: string ;

    /**
     * 默认展开所有
     */
    treeDefaultExpandAll?: boolean ;
}

export class FieldRemoteSelectMultipleInline extends React.Component<FieldRemoteSelectMultipleInlineProps, any> {}

