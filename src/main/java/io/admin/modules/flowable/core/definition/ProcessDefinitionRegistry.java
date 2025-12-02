package io.admin.modules.flowable.core.definition;


import com.google.common.collect.Lists;
import io.admin.common.utils.SpringUtils;
import io.admin.framework.config.init.SystemHookEventType;
import io.admin.framework.config.init.SystemHookService;
import io.admin.modules.flowable.admin.dao.SysFlowableModelDao;
import io.admin.modules.flowable.admin.entity.SysFlowableModel;
import io.admin.modules.flowable.core.config.ProcessConfiguration;
import io.admin.modules.flowable.core.dto.ProcessDefinitionInfo;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class ProcessDefinitionRegistry {

    private final Map<String, ProcessListener> definitionMap = new HashMap<>();

    private final Map<String, ProcessDefinitionInfo> dtoMap = new HashMap<>();

    @Resource
    private ProcessConfiguration processConfiguration;

    @Resource
    @Lazy
    private ProcessDefinitionRegistry registry;

    @Resource
    @Lazy
    private SysFlowableModelDao sysFlowableModelDao;

    @Resource
    private SystemHookService systemHookService;

    @PostConstruct
    void init(){
        for (ProcessDefinition definition : processConfiguration.getDefinitions()) {
            Class<? extends ProcessListener> listener = definition.getListener();
            ProcessListener bean = SpringUtils.getBean(listener);

            String key = definition.getKey();
            String name = definition.getName();

            SysFlowableModel sysFlowableModel = sysFlowableModelDao.init(key, name);

            ProcessDefinitionInfo info = new ProcessDefinitionInfo();
            info.setId(sysFlowableModel.getId());
            info.setName(name);
            info.setCode(key);
            info.setFormList(definition.getForms());
            info.setConditionVariableList(definition.getVariables());
            registry.register(key, bean, info);

            log.info("注册流程定义类 {} {}", key, definition.getClass().getName());
            systemHookService.trigger(SystemHookEventType.AFTER_FLOWABLE_DEFINITION_INIT);
        }
    }


    public void register(String key, ProcessListener definition, ProcessDefinitionInfo dto) {
        Assert.state(!definitionMap.containsKey(key), "流程监听器只能设置一个");
        definitionMap.put(key, definition);
        dtoMap.put(key, dto);
    }

    public ProcessListener getDefinition(String key) {
        return definitionMap.get(key);
    }

    public ProcessDefinitionInfo getInfo(String key) {
    	return dtoMap.get(key);
    }

    public List<ProcessDefinitionInfo> getAll(){
        return Lists.newArrayList(dtoMap.values());
    }

}
