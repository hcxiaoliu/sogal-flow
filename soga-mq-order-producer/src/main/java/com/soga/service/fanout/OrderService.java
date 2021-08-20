package com.soga.service.fanout;

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
public class OrderService {

    // 获取连接对象
    @Autowired
    private RabbitTemplate rabbitTemplate;

    // 模拟用户下单
    public void makeOrder(String userid, String productid, int num) {
        // 1.根据商品id查询库存是否足够
        // 2.保存订单
        String orderId = UUID.randomUUID().toString();
        System.out.println("订单生产成功：" + orderId);
        // 3.通过MQ来完成消息的分发
        // 参数1：交换机 参数2：路由key/queue队列名称 参数3：消息内容
        String exchangeName = "fanout_order_exchange";
        String routingKey = "";
        rabbitTemplate.convertAndSend(exchangeName, routingKey, orderId);
    }
}
