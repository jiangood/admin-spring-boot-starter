import {
    FieldDateTimePickerString,
    FieldDictRadio,
    FieldImageBase64,
    FieldInput,
    FieldPassword,
    FieldRadioBoolean,
    FieldSelect,
    FieldUploadImage
} from "../field";
import {ViewBoolean, ViewImage,  ViewText} from "../view";
import {VPassword} from "../../value-view-components";


export const fieldRegistry = {
    'text':FieldInput,

    'dict':FieldDictRadio,
    'dictRadio':FieldDictRadio,
    'password':FieldPassword,
    'datetime':FieldDateTimePickerString,
    'boolean':FieldRadioBoolean,
    imageBase64:FieldImageBase64,
    image:FieldUploadImage,

    select: FieldSelect
}

export const viewRegistry = {
    text: ViewText,
    password: VPassword,
    boolean: ViewBoolean,
    imageBase64: ViewImage,
    image:ViewImage
}




