import { html } from 'htm/preact';

import { TextFieldEntry, isTextFieldEntryEdited } from '@bpmn-io/properties-panel';
import { useService } from 'bpmn-js-properties-panel';

export  function ServiceTaskProps () {

  return [
    {
      id: 'delegateExpression',
      component: ServiceTaskComponent,
      isEdited: isTextFieldEntryEdited,
    }

  ];
}

function ServiceTaskComponent(props) {
  const { element, id } = props;

  const modeling = useService('modeling');
  const debounce = useService('debounceInput');

  const getValue = (element) => {
    return element.businessObject.delegateExpression || '';
  };

  const setValue = value => {
    return modeling.updateProperties(element, {
      delegateExpression: value
    });
  };



  return html`<${TextFieldEntry}
    id=${ id }
    element=${ element }
    label='Java服务类表达式'
    description='实现了JavaDelegate的Bean名称表达式， 如 \${demoDelegate}'
    getValue=${ getValue }
    setValue=${ setValue }
    debounce=${ debounce }
  />`;
}
