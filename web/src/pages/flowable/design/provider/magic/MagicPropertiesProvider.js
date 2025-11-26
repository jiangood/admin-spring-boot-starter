
import {is} from 'bpmn-js/lib/util/ModelUtil';
import {ServiceTaskProps} from "./parts/ServiceTaskProps";

const LOW_PRIORITY = 10001;


export default function MagicPropertiesProvider(propertiesPanel) {

    this.getGroups = function (element) {
        return function (groups) {
            if(is(element, 'bpmn:ServiceTask')){
                groups.push({
                    id: 'ServiceTaskLike',
                    label: "服务任务",
                    entries: ServiceTaskProps(element),
                })
            }
            return groups;
        }
    }

    propertiesPanel.registerProvider(LOW_PRIORITY, this);
}

MagicPropertiesProvider.$inject = ['propertiesPanel'];


