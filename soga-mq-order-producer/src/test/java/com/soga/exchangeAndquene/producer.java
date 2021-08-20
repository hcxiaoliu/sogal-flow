package com.soga.exchangeAndquene;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Author: lx
 * Date: 2021/8/5 16:19
 * Content:
 */
public class producer {
    public static void main(String[] args) {
        // 连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.142.110");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        connectionFactory.setVirtualHost("/");
        Connection connection = null;
        Channel channel = null;
        try {
            // 连接
            connection = connectionFactory.newConnection("生产者");
            // 信道
            channel = connection.createChannel();
            // 定义队列
            String queueName = "queue4";
            channel.queueDeclare(queueName, false, false, false, null);
            // 定义交换机
            String exchangeName = "direct_message_exchange";
            String exchangeType = "direct";
            channel.exchangeDeclare(exchangeName, exchangeType, true);//第三个参数是否持久化
            // 绑定交换机和队列
            channel.queueBind("queue4", exchangeName, "order");
            // 绑定上面的各个参数,发送消息
            String message = "Hello";
            channel.basicPublish(exchangeName, "order", null, message.getBytes());
            System.out.println("消息发送成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (channel != null && channel.isOpen()) {
                try {
                    channel.close();
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
