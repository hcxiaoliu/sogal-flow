package com.soga.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: lx
 * Date: 2021/8/20 11:35
 * Content:
 */
@Configuration
public class TTLRabbitMQConfiguration{
    // 1.声明注册direct模式的交换机
    @Bean
    public DirectExchange ttldirectExchange(){
        return new DirectExchange("ttl_direct_exchange",true,false);}
    // 2.队列的过期时间
    @Bean
    public Queue directttlQueue(){
        // 设置过期时间
        Map<String,Object> args = new HashMap<>();
        args.put("x-message-ttl", 10000);// 这里一定是int类型
        return new Queue("ttl.direct.queue",true,false,false,args);}

    @Bean
    public Binding ttlBingding(){
        return BindingBuilder.bind(directttlQueue()).to(ttldirectExchange()).with("ttl");
    }


    @Bean
    public Queue directttlMessageQueue(){
        return new Queue("ttlMessage.direct.queue");}

    @Bean
    public Binding ttlBingdingMessageQueue(){
        return BindingBuilder.bind(directttlMessageQueue()).to(ttldirectExchange()).with("ttlmessage");
    }
}
