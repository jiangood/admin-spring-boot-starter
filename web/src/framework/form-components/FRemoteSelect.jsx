import React from 'react';
import { Select, Spin, message } from 'antd';
import  { debounce } from 'lodash';
import {HttpUtil} from "../system";

export class FRemoteSelect extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            options: [],
            loading: false,
        };

        this.fetchIdRef = 0;
        this.debounceLoadOptions = debounce(this.loadData, 800);
    }


    componentWillUnmount() {
        this.debounceLoadOptions.cancel();
    }

    loadData = async (searchText) => {
        const { url } = this.props;
        const fetchId = ++this.fetchIdRef;

        this.setState({ loading: true });

        try {
            const data = await HttpUtil.get(url, {searchText});

            if (fetchId === this.fetchIdRef) {
                this.setState({ options: data || [] });
            }
        } catch (error) {
            console.error('远程搜索失败:', error);
            message.error('搜索失败，请重试');
            this.setState({ options: [] });
        } finally {
            if (fetchId === this.fetchIdRef) {
                this.setState({ loading: false });
            }
        }
    };

    handleSearch = (value) => {
        this.setState({ searchValue: value });

        if (value.trim() === '') {
            this.setState({ options: [] });
            return;
        }

        this.debounceLoadOptions(value);
    };

    handleChange = (value, option) => {
        const { onChange } = this.props;

        if (onChange) {
            onChange(value, option);
        }
    };

    render() {
        const { options, loading } = this.state;
        const { url, ...selectProps } = this.props;

        return (
            <Select
                showSearch={
                    {
                        filterOption: false,
                        onSearch: this.handleSearch,
                    }
                }
                allowClear
                onChange={this.handleChange}
                notFoundContent={loading ? <Spin size="small" /> : '数据为空'}
                options={options}
                {...selectProps}
            >
            </Select>
        );
    }
}

export default FRemoteSelect;