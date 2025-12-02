package io.admin.modules.flowable.demo;

import io.admin.modules.flowable.core.FlowableEventType;
import io.admin.modules.flowable.core.definition.ProcessListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class LeaveProcessListener implements ProcessListener {

    @Override
    public void onProcessEvent(FlowableEventType type, String initiator, String businessKey, Map<String, Object> variables) {

    }
}
