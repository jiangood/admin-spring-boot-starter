package io.admin.modules.job;


import com.fasterxml.jackson.core.JsonProcessingException;
import io.admin.common.utils.field.Field;

import java.util.List;
import java.util.Map;

public interface JobParamFieldProvider {

    List<Field> getFields(JobDescription jobDesc, Map<String, Object> jobData) throws JsonProcessingException;

}
