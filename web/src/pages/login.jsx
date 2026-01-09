import React from 'react';

import {LoginPage} from "../framework/pages/LoginPage";
import {Button} from "antd";


export default class  extends React.Component {




    render() {
        return <LoginPage />
        // 支持自定义
      // return <LoginPage form={<Button type={"primary"} size='large'>xx登录</Button>} />
    }




}
