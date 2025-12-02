package io.admin.common.utils;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.springframework.core.io.Resource;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class YmlUtils {
    public static <T> T parseYml(Resource resource, Class<T> beanClass) throws IOException {
        File file = resource.getFile();
        return parseYml(file,beanClass);
    }


    public static <T> T parseYml(String resourcePath, Class<T> beanClass) throws IOException {
        File file = ResourceUtils.getFile(resourcePath);
        return parseYml(file,beanClass);
    }

    public static <T> T parseYml(File file, Class<T> beanClass) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.KEBAB_CASE);
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS, true);
        return mapper.readValue(file, beanClass);
    }
}
