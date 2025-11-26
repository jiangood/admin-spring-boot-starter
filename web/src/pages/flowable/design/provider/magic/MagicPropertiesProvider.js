import spellProps from './parts/SpellProps';

import {is} from 'bpmn-js/lib/util/ModelUtil';

const LOW_PRIORITY = 500;


export default function MagicPropertiesProvider(propertiesPanel, translate) {

    this.getGroups = function (element) {
        /**
         * We return a middleware that modifies
         * the existing groups.
         */
        return function (groups) {
            if (is(element, 'bpmn:StartEvent')) {
                groups.push({
                    id: 'magic',
                    label: "时间设置",
                    entries: spellProps(element),
                });
            }
            return groups;
        }
    }

    propertiesPanel.registerProvider(LOW_PRIORITY, this);
}

MagicPropertiesProvider.$inject = ['propertiesPanel', 'translate'];

