
import {is} from 'bpmn-js/lib/util/ModelUtil';
import {DelegateExpressionProps} from "./properties/DelegateExpressionProps";
import {FormProps} from "./properties/FormProps";
import {UserProps} from "./properties/UserProps";

const LOW_PRIORITY = 10001;


export default function MagicPropertiesProvider(propertiesPanel) {

    this.getGroups = function (element) {
        return function (groups) {
            if(is(element, 'bpmn:ServiceTask')){
                groups.push({
                    id: 'processBean',
                    label: "处理器",
                    entries: DelegateExpressionProps(element),
                })
            }

            if(is(element,'bpmn:UserTask')){
                groups.push({
                    id: 'user',
                    label: "用户",
                    entries: UserProps(element),
                })
                groups.push({
                    id: 'form',
                    label: "表单",
                    entries: FormProps(element),
                })
            }

            return groups;
        }
    }

    propertiesPanel.registerProvider(LOW_PRIORITY, this);
}

MagicPropertiesProvider.$inject = ['propertiesPanel'];


