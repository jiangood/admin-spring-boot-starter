import {Form, Input} from "antd";

export default function (){

    return <div>
        demo-司机表单
        <Form>
            <Form.Item label="车牌号">
                <Input/>
            </Form.Item>
            <Form.Item label="姓名">
                <Input/>
            </Form.Item>
        </Form>
    </div>
}