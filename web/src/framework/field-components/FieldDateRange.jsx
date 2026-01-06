/**
 * 根据时间类型自动渲染时间选择组件
 */
import React from "react";
import dayjs from "dayjs";
import {DatePicker, TimePicker} from "antd";
import {DateUtils, StringUtils} from "../utils";

const SP = StringUtils.ISO_SPLITTER;

export class FieldDateRange extends React.Component {
    static defaultProps = {
        type: 'YYYY-MM-DD'
    }

    render() {
        let {type, value, onChange, ...rest} = this.props;
        type = DateUtils.convertTypeToFormat(type)
        switch (type) {
            case 'YYYY':
                return <DatePicker.RangePicker
                    value={this.strToDate(value, 'YYYY')}
                    onChange={v => onChange(this.dateToStr(v,'YYYY'))}
                    picker="year"
                    {...rest}
                />;
            case 'YYYY-MM':
                return <DatePicker.RangePicker
                    value={this.strToDate(value, 'YYYY-MM')}
                    onChange={v => onChange(this.dateToStr(v,'YYYY-MM'))}
                    picker="month"
                    {...rest}
                />;
            case 'YYYY-QQ':
                return <DatePicker.RangePicker
                    value={this.strToDate(value, 'YYYY-QQ')}
                    onChange={v => onChange(this.dateToStr(v,'YYYY-QQ'))}
                    picker="quarter"
                    {...rest}
                />;
            case 'YYYY-MM-DD':
                return   <DatePicker.RangePicker
                    value={this.strToDate(value, 'YYYY-MM-DD')}
                    onChange={v => onChange(this.dateToStr(v,'YYYY-MM-DD'))}
                    {...rest}
                />;
            case 'YYYY-MM-DD HH:mm':
                return <DatePicker.RangePicker
                    value={this.strToDate(value, 'YYYY-MM-DD HH:mm')}
                    onChange={v => onChange(this.dateToStr(v,'YYYY-MM-DD HH:mm'))}
                    format='YYYY-MM-DD HH:mm'
                    showTime
                    {...rest}
                />;
            case 'YYYY-MM-DD HH:mm:ss':
                return <DatePicker.RangePicker
                    value={this.strToDate(value, 'YYYY-MM-DD HH:mm:ss')}
                    onChange={v => onChange(this.dateToStr(v,'YYYY-MM-DD HH:mm:ss'))}
                    showTime
                    {...rest}
                />;
            case 'HH:mm':
                return <TimePicker.RangePicker
                    format='HH:mm'
                    value={this.strToDate(value, 'HH:mm')}
                    onChange={v => onChange(this.dateToStr(v,'HH:mm'))}
                    {...rest}
                />;
            case 'HH:mm:ss':
                return <TimePicker.RangePicker
                    value={this.strToDate(value, 'HH:mm:ss')}
                    onChange={v => onChange(this.dateToStr(v,'HH:mm:ss'))}
                    {...rest}
                />;
        }

        return <div>未知组件 {type}</div>
    }



    strToDate(v, fmt) {
        console.log('调用转换')
        if (!v) {
            return null;
        }

        const arr = v.split(SP)
        let s1 = arr[0];
        let s2 = arr[1];
        return [dayjs(s1), dayjs(s2)]
    }

    dateToStr(dateArr, fmt) {
        const d1 = dateArr[0]
        const d2 = dateArr[1]

        const s1 = d1 ? d1.format(fmt) : ""
        const s2 = d2 ? d2.format(fmt) : "";

        return s1 + SP + s2
    }

}
