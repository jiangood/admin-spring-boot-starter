package io.github.jiangood.openadmin.framework.middleware.mq;

import org.springframework.stereotype.Component;

@Component
@MQTopicListener(topic = "demo")
public class DemoMqListener implements MQListener {


    @Override
    public Result onMessage(Message msg) {
        System.out.println("消费消息" + msg);
        return Result.SUCCESS;
    }
}
