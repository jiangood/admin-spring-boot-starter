import React from "react";
import {Button, Card, Empty, Modal, Skeleton, Table, Tabs, Typography} from "antd";

import {FormOutlined, ShareAltOutlined} from "@ant-design/icons";
import {
    FormRegistryUtils,
    Gap,
    HttpUtils,
    MessageUtils,
    ViewFlowableInstanceProgress,
    ViewInstanceProgress
} from "../../framework";



export class ViewFlowableInstanceProgressButton extends React.Component{
    state = {
        open:false,
    }

    onClick = () => {
        console.log('点击追踪流程')
        MessageUtils.alert(<ViewInstanceProgress value={this.props.value} />,{
            title:'流程审批信息',
            width:800
        })
    };
    render() {
       return <Button onClick={this.onClick} size='small'>追踪流程</Button>
    }


}