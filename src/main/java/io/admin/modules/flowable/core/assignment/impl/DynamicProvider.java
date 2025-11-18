package io.admin.modules.flowable.core.assignment.impl;

import io.admin.modules.flowable.core.assignment.AssignmentTypeProvider;
import io.admin.modules.flowable.core.assignment.Identity;
import org.springframework.stereotype.Component;

import java.util.*;

import static io.admin.modules.flowable.FlowableConsts.VAR_DEPT_LEADER;

@Component
public class DynamicProvider implements AssignmentTypeProvider {

    /**
     * 表达式，显示名
     */
    private static final Map<String, String> EXPR_MAP = new LinkedHashMap<>();


    static {
        EXPR_MAP.put("${INITIATOR}", "发起人");
        EXPR_MAP.put("${" + VAR_DEPT_LEADER + "}", "部门领导");
    }


    @Override
    public String getCode() {
        return "dynamic";
    }

    @Override
    public String getLabel() {
        return "动态办理人";
    }

    @Override
    public int getOrder() {
        return 10;
    }

    @Override
    public boolean isMultiple() {
        return false;
    }


    @Override
    public XmlAttribute getXmlAttribute() {
        return XmlAttribute.assignee;
    }

    @Override
    public Collection<Identity> findAll() {
        List<Identity> list = new ArrayList<>();
        for (Map.Entry<String, String> e : EXPR_MAP.entrySet()) {
            list.add(new Identity(e.getKey(), null, e.getValue(), true, true));
        }

        return list;
    }

    @Override
    public String getLabelById(String id) {
        return "部门领导";
    }
}
