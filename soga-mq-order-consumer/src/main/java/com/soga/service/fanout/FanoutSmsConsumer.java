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
@RabbitListener(queues = {"sms.fanout.queue"})// 对应队列名
public class FanoutSmsConsumer{
    @RabbitHandler
    // 该方法的参数就是接收的消息
    public void reviceMessage(String message){
        System.out.println("sms接收到了的订单信息是："+message);
    }
}