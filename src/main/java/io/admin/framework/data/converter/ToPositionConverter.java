package io.admin.framework.data.converter;


import io.admin.common.Position;
import io.admin.common.utils.JsonUtils;

import java.io.IOException;

public class ToPositionConverter extends BaseConverter<Position> {

    @Override
    public Position convertToEntityAttribute(String dbData) {
        if (dbData != null) {
            try {
                return JsonUtils.jsonToBean(dbData, Position.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return null;
    }
}
