package io.github.jiangood.openadmin.framework.middleware;

import cn.hutool.core.util.IdUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.*;
import java.util.concurrent.*;

/***
 * 基于内存的mq实现
 */
@Slf4j
public class MQ  {

    // 存储所有topic对应的队列
    private final Map<String, BlockingQueue<Msg>> topicQueues = new HashMap<>();
    private final MultiValueMap<String, Consumer> consumers = new LinkedMultiValueMap<>();

    /**
     * 发送消息到指定topic（自动创建队列）
     */
    public void send(String topic, String tag, String message) throws InterruptedException {
        BlockingQueue<Msg> queue = topicQueues.get(topic);
        if (queue == null) throw new IllegalStateException("消息队列未创建");

        queue.put(new Msg(topic, tag, message));

        log.info("消息发送成功: topic={}, message={}", topic, message);
    }


    /**
     * 订阅指定topic的消息
     */
    public void subscribe(String topic, Consumer consumer) {
        consumers.add(topic,consumer);
    }
   private ExecutorService executorService;
    public void start() {
        if(consumers.isEmpty()) throw new IllegalArgumentException("启动失败：无消费者");
        for (String topic : consumers.keySet()) {
            topicQueues.computeIfAbsent(topic, k -> new LinkedBlockingQueue<>());
        }

        Set<String> topics = topicQueues.keySet();

         executorService = Executors.newFixedThreadPool(topics.size());


        for (String topic : topics) {
            BlockingQueue<Msg> queue = topicQueues.get(topic);
            executorService.submit(()->{
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        Msg message = queue.take();
                        List<Consumer> consumers = this.consumers.get(topic);
                        for (Consumer consumer : consumers) {
                            consumer.consume(message);
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    } catch (Exception e) {
                        System.err.println("消息消费异常: " + e.getMessage());
                    }
                }
            });
        }
    }


    /**
     * 停止所有消费者
     */
    public void shutdown() {
        executorService.shutdown();
        topicQueues.clear();
        consumers.clear();
    }


    @Data
    public class Msg {
        Long id;
        String topic;
        String tag;
        String message;

        public Msg(String topic, String tag, String message) {
            this.id = IdUtil.getSnowflakeNextId();
            this.topic = topic;
            this.tag = tag;
            this.message = message;
        }
    }

    public interface Consumer {

        Result consume(Msg msg);
    }

    public enum Result {

        /**
         * 消费成功
         */
        SUCCESS(true),

        /**
         * 消费失败，需要重试
         */
        RETRY_LATER(false),

        /**
         * 消费失败，无需重试（进入死信队列）
         */
        FAILURE(false),

        /**
         * 重复消息，直接丢弃
         */
        DUPLICATE(true);

        private final boolean success;

        Result(boolean success) {
            this.success = success;
        }

        public boolean isSuccess() {
            return success;
        }
    }


    public static void main(String[] args) throws InterruptedException {
        MQ queue = new MQ();

        // 订阅topic1
        queue.subscribe("周杰伦", message -> {
            System.out.println("周粉丝1: " + message);
            return Result.SUCCESS;
        });
        queue.subscribe("周杰伦", message -> {
            System.out.println("周粉丝2: " + message);
            return Result.SUCCESS;
        });

        // 订阅topic2
        queue.subscribe("蔡琴", message -> {
            System.out.println("蔡琴粉丝: " + message);
            return Result.SUCCESS;
        });

        queue.start();

        queue.send("周杰伦", "","Hello World 1");
        queue.send("蔡琴", "","Test Message");

    }
}