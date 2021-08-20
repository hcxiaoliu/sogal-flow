package com.soga.Direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Author: lx
 * Date: 2021/8/5 12:10
 * Content: 通过交换机 绑定队列的路由key 进行生产和消费  信息
 */
public class Producer {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.142.110");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        connectionFactory.setVirtualHost("/");
        Connection connection = null;
        Channel channel = null;
        try {
            connection = connectionFactory.newConnection("生产者");
            channel = connection.createChannel();
            // fanout 无需指定队列和路由
            // 发送的消息内容
            String message = "Hello direct 代码测试";
            String exchangeName = "Direct-exchange";
            String routeKey = "qq";
            // String type = "fanout";
            channel.basicPublish(exchangeName, routeKey, null, message.getBytes());
            System.out.println("消息发送成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (channel != null && channel.isOpen()) {
                try {
                    // 6.关闭通道,注意关闭顺序
                    channel.close();
                    // 7.关闭连接
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
