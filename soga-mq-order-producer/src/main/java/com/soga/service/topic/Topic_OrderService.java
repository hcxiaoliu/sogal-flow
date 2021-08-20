package com.soga.service.topic;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Author: lx
 * Date: 2021/8/19 14:25
 * Content:
 */
@Service
public class Topic_OrderService {

    // 获取连接对象
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void makeOrderTopic() {
        // 1.根据商品id查询库存是否足够
        // 2.保存订单
        String orderId = UUID.randomUUID().toString();
        System.out.println("订单生产成功："+orderId);
        // 3.通过MQ来完成消息的分发
        // 参数1：交换机 参数2：路由key/queue队列名称 参数3：消息内容
        String exchangeName = "topic_order_exchange";
        String routingKey = "com.duanxin";// 短信和sms可以收到
        rabbitTemplate.convertAndSend(exchangeName,routingKey, orderId);
    }

    public void mekteMessage(){
            //1.根据商品id查询库存是否足够
            //2.保存订单
            String orderId = UUID.randomUUID().toString();
        System.out.println("订单生产成功："+orderId);
            //3.通过MQ来完成消息的分发
            //参数1：交换机 参数2：路由key/queue队列名称 参数3：消息内容
            String exchangeName = "ttl_order_exchange";
            String routingKey = "ttlmessage";
            //给消息设置过期时间
            MessagePostProcessor messagePostProcessor = new MessagePostProcessor(){
                @Override
                public Message postProcessMessage(Message message){
                    //这里就是字符串
                    message.getMessageProperties().setExpiration("5000");
                    message.getMessageProperties().setContentEncoding("UTF-8");
                    return message;
                }
            };
            rabbitTemplate.convertAndSend(exchangeName,routingKey,orderId,messagePostProcessor);

    }
}
