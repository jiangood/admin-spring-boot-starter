package io.github.jiangood.openadmin.framework.middleware.mq;

import io.github.jiangood.openadmin.lang.SpringTool;
import io.github.jiangood.openadmin.lang.jdbc.DbTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBooleanProperty;
import org.springframework.context.SmartLifecycle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@ConditionalOnBooleanProperty(prefix = "sys", name = "simple-message-queue-enabled", matchIfMissing = true)
@Configuration
public class MqConfig implements SmartLifecycle {

    private MQ mq;


    public MqConfig( DbTool dbTool) {
        this.mq = new MQ(new RepDatabaseImpl(dbTool));
    }

    @Bean
    public MessageQueueTemplate messageQueueTemplate() {
        return new MessageQueueTemplate(mq);
    }

    @Override
    public void start() {
        init();
    }

    private void init() {
        log.info("简单MQ启动...");
        try {
            List<MQListener> listeners = SpringTool.getBeans(MQListener.class);
            for (MQListener listener : listeners) {
                MQTopicListener annotation = listener.getClass().getAnnotation(MQTopicListener.class);
                if (annotation != null) {
                    mq.subscribe(annotation.topic(), listener);
                    log.info("注册队列处理器: topic={}, listener={}", annotation.topic(), listener.getClass().getSimpleName());
                }
            }

            mq.start();
            log.info("简单MQ成功...");
        } catch (Exception e) {
            log.error("简单MQ启动失败", e);
        }
    }

    @Override
    public void stop() {
        log.info("简单MQ停止");
        if (this.isRunning()) {
            mq.shutdown();
            mq = null;
        }
    }

    @Override
    public boolean isRunning() {
        return mq != null && mq.isRunning();
    }
}
