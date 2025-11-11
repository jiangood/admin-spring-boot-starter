import React from "react";
import {HttpUtil, ProTable} from "@jian41/admin-framework";

export default class extends React.Component {


    render() {
        return <ProTable
            columns={[
                {dataIndex: 'name', title: '名称'},
                {dataIndex: 'action', title: '动作'},
                {dataIndex: 'desc', title: '描述'},

            ]}
            request={(params,) => HttpUtil.pageData('apiResource/page', params)}
        />
    }
}
