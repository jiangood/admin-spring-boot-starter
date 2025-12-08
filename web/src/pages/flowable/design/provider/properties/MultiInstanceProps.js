import {
    getBusinessObject,
    is
} from 'bpmn-js/lib/util/ModelUtil';

import { TextFieldEntry, isTextFieldEntryEdited } from '@bpmn-io/properties-panel';
import {useService} from "bpmn-js-properties-panel";


/**
 * @returns {Array<Entry>} entries
 */
export function MultiInstanceProps(props) {
    const {
        element
    } = props;

    if (!isMultiInstanceSupported(element)) {
        return [];
    }
    const entries = [
        {
            id: 'collection',
            component: Collection,
            isEdited: isTextFieldEntryEdited
        },
        {
            id:'elementVariable',
            component: ElementVariable,
            isEdited: isTextFieldEntryEdited
        }
    ];

    return entries;
}

function Collection(props) {
    const { element } = props;
    const debounce = useService('debounceInput');

    const getValue = () => {
        return getLoopCharacteristics(element).get('collection')
    };

    const setValue = (value) => {
       getLoopCharacteristics( element).set('collection', value);
    };

    return TextFieldEntry({
        element,
        id: 'collection',
        label: '集合',
        getValue,
        setValue,
        debounce,
        placeholder:'如 userList'
    });
}

function ElementVariable(props) {
    const { element } = props;

    const debounce = useService('debounceInput');
    const getValue = () => {
        return getLoopCharacteristics(element).get('elementVariable')
    };

    const setValue = (value) => {
        getLoopCharacteristics( element).set('elementVariable', value);
    };

    return TextFieldEntry({
        element,
        id: 'elementVariable',
        label: '元素变量',
        getValue,
        setValue,
        debounce,
        placeholder:'如 user'
    });
}




/**
 * isMultiInstanceSupported - check whether given element supports MultiInstanceLoopCharacteristics.
 *
 * @param {djs.model.Base} element
 * @return {boolean}
 */
function isMultiInstanceSupported(element) {
    const loopCharacteristics = getLoopCharacteristics(element);
    return !!loopCharacteristics && is(loopCharacteristics, 'bpmn:MultiInstanceLoopCharacteristics');
}
function getLoopCharacteristics(element) {
    const bo = getBusinessObject(element);
    return bo.loopCharacteristics;
}

