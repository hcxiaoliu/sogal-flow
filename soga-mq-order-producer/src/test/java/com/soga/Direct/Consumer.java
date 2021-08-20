package com.soga.Direct;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * Author: lx
 * Date: 2021/8/5 12:17
 * Content:
 */
public class Consumer {
    public static void main(String[] args) {
        new Thread(runnable, "queue1").start();
        new Thread(runnable, "queue2").start();
        new Thread(runnable, "queue3").start();
    }

    private static Runnable runnable = new Runnable() {
        @Override
        public void run() {
            ConnectionFactory connectionFactory = new ConnectionFactory();
            connectionFactory.setHost("192.168.142.110");
            connectionFactory.setPort(5672);
            connectionFactory.setUsername("admin");
            connectionFactory.setPassword("admin");
            connectionFactory.setVirtualHost("/");
            Connection connection = null;
            Channel channel = null;
            final String queueName = Thread.currentThread().getName();
            try {
                connection = connectionFactory.newConnection("消费者");
                channel = connection.createChannel();
                // 这里我们并没有做交换机与度列的绑定,因为在web管理端已经绑定好了
                // 所以以后在实际生产中也可以使用这种图形+代码相结合的方式
                channel.basicConsume(queueName, true, new DeliverCallback() {
                    @Override
                    public void handle(String s, Delivery delivery) throws IOException {
                        System.out.println(queueName + " :消息已接收," + new String(delivery.getBody(), "UTF-8"));
                    }
                }, new CancelCallback() {
                    @Override
                    public void handle(String s) throws IOException {
                        System.out.println("接收消息失败");
                    }
                });
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
    };
}
