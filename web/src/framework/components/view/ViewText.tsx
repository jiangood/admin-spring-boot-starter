import React from "react";
import {Typography} from "antd";

interface ViewTextProps {
    value: string | null | undefined;
}

export class ViewText extends React.Component<ViewTextProps, any>{

    render() {
        if(this.props.value == null){
            return null;
        }
        return <Typography.Text> {this.props.value}</Typography.Text>;
    }
}
