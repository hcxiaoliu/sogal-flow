package com.soga.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author: lx
 * Date: 2021/8/19 14:29
 * Content:
 */
// 配置类配置具体的rabbitmq属性参数
/*@Configuration*/
public class RabbitMqConfiguration {
    // 1.声明注册fanout模式的交换机
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanout_order_exchange", true, false);
    }

    // 2.声明队列
    @Bean
    public Queue smsQueue() {
        return new Queue("sms.fanout.queue", true);
    }

    @Bean
    public Queue duanxinQueue() {
        return new Queue("duanxin.fanout.queue", true);
    }

    @Bean
    public Queue emailQueue() {
        return new Queue("email.fanout.queue", true);
    }

    // 3.完成绑定关系
    @Bean
    public Binding smsBingding() {
        return BindingBuilder.bind(smsQueue()).to(fanoutExchange());
    }

    @Bean
    public Binding duanxinBingding() {
        return BindingBuilder.bind(duanxinQueue()).to(fanoutExchange());
    }

    @Bean
    public Binding emailBingding() {
        return BindingBuilder.bind(emailQueue()).to(fanoutExchange());
    }
}
