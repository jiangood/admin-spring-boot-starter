import React from 'react';
import {Space} from 'antd';
import {PermUtils} from "../../utils";


/**
 * 带权限的按钮列表
 * @param maxNum: 显示子节点的个数， 超过的为收缩起来
 *
 */
export class ButtonList extends React.Component {


    render() {
        let {children} = this.props;
        const menus = [];

        // 判断是否数组
        if (Array.isArray(children)) {
            for (let child of children) {
                if (child === null || child === undefined) {
                    continue;
                }
                if (child.props == null || child.props.perm == null || PermUtils.hasPermission(child.props.perm)) {
                    menus.push(child);
                }
            }
        } else {
            menus.push(children)
        }


        return <Space>{menus}</Space>;
    }
}
