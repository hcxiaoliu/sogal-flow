package com.soga.bootOrder;

import com.soga.service.Direct.Direct_OrderService;
import com.soga.service.topic.Topic_OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Author: lx
 * Date: 2021/8/5 12:17
 * Content:
 */
@SpringBootTest
public class topic_oderServcice {

    @Autowired
    private Topic_OrderService topicOrderService;

    @Test
    void contextLoads() {
        topicOrderService.makeOrderTopic();
    }
}
