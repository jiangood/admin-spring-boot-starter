import {Form} from "antd";
import {FieldRemoteSelect, FieldRemoteSelectMultiple, StringUtils} from "../../../../../framework";
import React from "react";

export function UserTaskForm(props) {
    const {element, modeling} = props
    let initialValues = {
        assignee: element.businessObject.assignee,
        candidateGroups: element.businessObject.candidateGroups,
        candidateUsers: StringUtils.split(element.businessObject.candidateUsers, ',')
    };
    return (<div style={{padding:16}}>
        <Form layout='vertical'
              initialValues={initialValues}
              onValuesChange={(changedValues) => {
                  modeling.updateProperties(element, changedValues);
              }}>
            <Form.Item label="办理人" name='assignee'>
                <FieldRemoteSelect url='admin/flowable/model/assigneeOptions'/>
            </Form.Item>
            <Form.Item label="候选组" name='candidateGroups'>
                <FieldRemoteSelect url='admin/flowable/model/candidateGroupsOptions'/>
            </Form.Item>
            <Form.Item label="候选人" name='candidateUsers'>
                <FieldRemoteSelectMultiple url='admin/flowable/model/candidateUsersOptions'/>
            </Form.Item>
        </Form></div>
    )
}