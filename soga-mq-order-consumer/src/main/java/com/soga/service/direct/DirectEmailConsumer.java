package com.soga.service.direct;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Author: lx
 * Date: 2021/8/19 16:55
 * Content:
 */
@Component
@RabbitListener(queues = {"email.direct.queue"})
public class DirectEmailConsumer {
    @RabbitHandler
    public void reviceMessage(String message){
        System.out.println("email direct 接收到了的订单信息是："+message);
    }
}