package com.soga.service.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Author: lx
 * Date: 2021/8/19 15:23
 * Content:
 */
@Component
@RabbitListener(queues = {"email.fanout.queue"})
public class FanoutEmailConsumer{
    @RabbitHandler
    public void reviceMessage(String message){
        System.out.println("email接收到了的订单信息是："+message);
    }
}