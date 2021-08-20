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
@RabbitListener(queues = {"duanxin.fanout.queue"})
public class FanoutDuanxinConsumer{
    @RabbitHandler
    public void reviceMessage(String message){
        System.out.println("duanxin接收到了的订单信息是："+message);
    }
}