import {Checkbox, Radio, Select} from 'antd';
import React from 'react';
import {dictList, dictValue, dictValueTag} from "../../../system";

export interface DictProps {
  typeCode: string;
  params?: string; // 同typeCode, pro-table用
  mode?: string | 'read';
  value?: any;
  onChange?: (value: any) => void;
  placeholder?: string;
}


export function DictRadio(props: DictProps) {
  let { typeCode, mode, value, params } = props;

  if(typeCode == null){
    typeCode = params;
  }

  if (mode === 'read') {
    return dictValueTag(typeCode, value);
  }

  let list = dictList(typeCode);
  return (
    <Radio.Group value={String(value)} onChange={props.onChange}>
      {list.map((o: any) => (
        <Radio value={o.code} key={o.code}>
          {o.name}
        </Radio>
      ))}
    </Radio.Group>
  );
}

// 多选框
export function DictCheckbox(props: DictProps) {
  const { typeCode, mode, value } = props;
  if (mode === 'read') {
    if (value && value.length) {
      return value.map((v: any) => dictValueTag(typeCode, v));
    }
    return '-';
  }

  let list = dictList(typeCode);
  return (
    <Checkbox.Group value={value} onChange={props.onChange}>
      {list.map((o: any) => (
        <Checkbox value={o.code} key={o.code}>
          {o.name}
        </Checkbox>
      ))}
    </Checkbox.Group>
  );
}

export function FieldDictValueTag(props: DictProps) {
  const { typeCode, value } = props;
  return dictValueTag(typeCode, value);
}
export function FieldDictValue(props: DictProps) {
  const { typeCode, value } = props;
  return dictValue(typeCode, value);
}

export {
  DictRadio as FieldDictRadio,
  DictCheckbox as FieldDictCheckbox,
};
