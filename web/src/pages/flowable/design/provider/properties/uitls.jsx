import {useService} from "bpmn-js-properties-panel";
import {UuidUtils} from "../../../../../framework";
import {createRoot} from "react-dom/client";
import {h} from "preact";
import React from "react";
import {useEffect} from "@bpmn-io/properties-panel/preact/hooks";

export function renderReactComponent(props, ReactComponent){
    const {element, id} = props;
    const modeling = useService('modeling');

    let elementId = UuidUtils.uuidV4();
    useEffect(() => {
        const dom = document.getElementById(elementId)
        const root = createRoot(dom);
        root.render(<ReactComponent element={element} modeling={modeling} />);

    }, []);

    return h('div', {id: elementId})
}
