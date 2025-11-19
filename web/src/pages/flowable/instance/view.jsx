import React from "react";
import InstanceStatusInfo from "../../../components/InstanceStatusInfo";
import {PageUtil} from "../../../framework";

export default class extends React.Component {


    render() {
        const {businessKey, id} = PageUtil.currentParams()
        return <InstanceStatusInfo businessKey={businessKey} id={id}/>
    }

}
