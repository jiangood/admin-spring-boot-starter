package io.admin.framework.data.converter;


import io.admin.common.utils.JsonUtils;
import io.admin.common.utils.ReflectUtils;
import jakarta.persistence.AttributeConverter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BaseToListConverter<T> implements AttributeConverter<List<T>, String>, Serializable {


    private static final long serialVersionUID = 1L;


    @Override
    public String convertToDatabaseColumn(List<T> input) {
        return JsonUtils.toJsonQuietly(input);
    }

    @Override
    public List<T> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return new ArrayList<>();
        }

        Class<T> cls = ReflectUtils.getClassGenricType(getClass());

        return JsonUtils.jsonToBeanListQuietly(dbData, cls);
    }


}
