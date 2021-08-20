package com.soga.service.topic;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Author: lx
 * Date: 2021/8/20 9:40
 * Content:
 */
@Component
@RabbitListener(bindings = @QueueBinding(
        value = @Queue(value = "sms.topic.queue",durable = "true",autoDelete = "false"),
        exchange = @Exchange(value = "topic_order_exchange", type = ExchangeTypes.TOPIC),
        key = "com.#"
))
public class TopicSmsConsumer {
    @RabbitHandler
    // 该方法的参数就是接收的消息
    public void reviceMessage(String message){
        System.out.println("sms接收到了的订单信息是："+message);
    }
}
