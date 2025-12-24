import InstanceInfo from "../InstanceInfo";
import {PageUtils} from "../../../framework";
import React from "react";

export default class extends React.Component{

    render() {
        let params = PageUtils.currentParams();
        const businessKey = params.businessKey
        return <InstanceInfo businessKey={businessKey}/>
    }

}