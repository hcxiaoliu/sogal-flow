package com.soga.service.topic;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Author: lx
 * Date: 2021/8/20 9:41
 * Content:
 */
@Component
@RabbitListener(bindings = @QueueBinding(
        value = @Queue(value = "duanxin.topic.queue",durable = "true",autoDelete = "false"),
        exchange = @Exchange(value = "topic_order_exchange", type = ExchangeTypes.TOPIC),
        key = "#.duanxin.#"
))
public class TopicDuanxinConsumer {
    @RabbitHandler
    public void reviceMessage(String message){
        System.out.println("duanxin接收到了的订单信息是："+message);
    }
}