package io.github.jiangood.openadmin.framework.middleware.mq;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MessageQueueTemplateTest {

    @Resource
    private  MessageQueueTemplate messageQueueTemplate;
    @Test
    void send() throws InterruptedException {

        Thread.sleep(5000);
        messageQueueTemplate.send("demo", "", "hello world");

        Thread.sleep(5000);
    }
}