import {isTextFieldEntryEdited, SelectEntry, TextFieldEntry} from '@bpmn-io/properties-panel';
import {useService} from 'bpmn-js-properties-panel';
import {useEffect, useState} from '@bpmn-io/properties-panel/preact/hooks';
import {HttpUtils} from "../../../../../framework";

export function ConditionProps() {

    return [
        {
            id: 'expression',
            component: Component,
            isEdited: isTextFieldEntryEdited,
        }
    ]
}

function Component(props) {
    const {element, id} = props;

    const modeling = useService('modeling');
    const debounce = useService('debounceInput');
    const getValue = (element) => {
        return element.businessObject.formKey || '';
    };

    const setValue = value => {
        return modeling.updateProperties(element, {
            formKey: value
        });
    };


    return TextFieldEntry({
        element,
        id: id,
        label: '条件表达式',
        getValue,
        setValue,
        debounce,


    })

}
