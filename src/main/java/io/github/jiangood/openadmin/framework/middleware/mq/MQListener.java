package io.github.jiangood.openadmin.framework.middleware.mq;

public interface MQListener {



    Result onMessage(Message msg);
}
