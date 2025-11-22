import React from "react";
import {dictOptions} from "../system";
import {Select} from "antd";

export function FieldDictSelect(props) {
    const {value, onChange, typeCode, ...rest} = props
    const options = dictOptions(typeCode)

    return <Select value={value}
                   onChange={onChange}
                   style={{width: '100%', minWidth: 200}}
                   options={options}
                   {...rest}>

    </Select>
}