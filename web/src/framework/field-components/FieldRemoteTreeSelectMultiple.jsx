import {Spin, TreeSelect} from 'antd';

import React from 'react';
import {StrUtil} from "../utils";
import {HttpUtil} from "../system";
import {FieldRemoteSelectMultiple} from "./FieldRemoteSelectMultiple";

export class FieldRemoteTreeSelectMultiple extends React.Component {

    static defaultProps = {
        treeDefaultExpandAll: true,
    }

    state = {
        data: [],
        value: [],
        loading: false,
        key: this.props.id,
    };

    componentDidMount() {
        this.loadData();
    }


    loadData = async () => {
        const {url} = this.props;
        this.setState({loading: true});
        try {
            const rs = await HttpUtil.get(url)
            this.setState({data: rs});
        } finally {
            this.setState({loading: false});
        }
    };



    render() {
        let {value,onChange} = this.props;
        let {data} = this.state;

        if (this.state.loading) {
            return <Spin/>;
        }




        return (
            <TreeSelect
                style={{width: '100%', minWidth: 200}}
                allowClear={true}
                dropdownStyle={{maxHeight: 400, overflow: 'auto'}}
                treeData={data}
                showCheckedStrategy={TreeSelect.SHOW_ALL}
                value={value || undefined}
                onChange={onChange}
                multiple={true}

                filterTreeNode={(inputValue, treeNode) => {
                    const {title} = treeNode
                    return StrUtil.contains(title, inputValue)
                }}
                treeLine={{showLeafIcon: true}}
                treeDefaultExpandAll={this.props.treeDefaultExpandAll}
            />
        );
    }
}
