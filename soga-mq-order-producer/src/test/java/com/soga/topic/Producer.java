package com.soga.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Author: lx
 * Date: 2021/8/5 12:10
 * Content:
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
            String message = "Hello topic 代码测试";

            String exchangeName = "Topic-exchange";
            //6.定义路由key
            String routeKey = "com.order.test.xxx";
            //7.指定交换机的类型
            String type = "topic";

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
