/***
 *
 * @param code
 */
import {Tag} from 'antd';
import React from 'react';
import {SysUtil} from "./sys";
import {StrUtil} from "../utils";

// 根据字典类型code返回字典数据列表， code 支持 驼峰或下划线（都转为下划线比较）
export function dictList(code) {
    const map = SysUtil.getDictInfo()
    if (map === undefined) {
        return [];
    }
    const code1 = code.toUpperCase()
    const code2 = StrUtil.toUnderlineCase( code).toUpperCase()

    return map[code1] || map[code2] || [];
}

export function dictMap(typeCode) {
    const list = dictList(typeCode);

    if (list == null) {
        console.log('未找到数据字典, code=' + typeCode);
        return {};
    }
    const map = {};
    list.forEach((i) => {
        const code = i.code;

        // @ts-ignore
        map[code] = i;
    });

    return map;
}

export function dictValue(typeCode, code) {
    const item = dictData(typeCode, code);
    if (item) {
        return item.name;
    }
}

export function dictValueTag(typeCode, dataCode) {
    if (dataCode == null) {
        return '';
    }
    const data = dictData(typeCode, dataCode);
    if (data != null) {
        const {name, color} = data;

        if (color == null) {
            return name;
        }

        // js 方式创建 ，等同于 <Tag color={color}>{name}</Tag>
        return React.createElement(Tag, {color: color}, name);
    }
    return '';
}

export function dictData(typeCode, code) {
    if (code == null) {
        return null;
    }
    const map = dictMap(typeCode);
    // @ts-ignore
    let item = map[code];
    if (item == null) {
        // @ts-ignore
        item = map[code + ''];
    }
    return item;
}
