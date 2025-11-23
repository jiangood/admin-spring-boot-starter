import React from 'react';

export interface FieldDateProps  {

 type: 'YYYY' |
     'YYYY-MM' |
     'YYYY-QQ'|
     'YYYY-MM-DD' |
     'YYYY-MM-DD HH:mm' |
     'YYYY-MM-DD HH:mm:ss'|
     'HH:mm' |
     'HH:mm:ss';


}

export class FieldDate extends React.Component<FieldDateProps, any> {}

