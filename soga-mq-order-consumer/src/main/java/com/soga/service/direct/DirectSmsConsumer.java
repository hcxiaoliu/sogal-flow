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
@RabbitListener(queues = {"sms.direct.queue"})// 对应队列名
public class DirectSmsConsumer {
    @RabbitHandler
    // 该方法的参数就是接收的消息
    public void reviceMessage(String message){
        System.out.println("sms direct 接收到了的订单信息是："+message);
    }
}