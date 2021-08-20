package com.soga.bootOrder;

import com.soga.service.fanout.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Author: lx
 * Date: 2021/8/5 12:17
 * Content:
 */
@SpringBootTest
public class fanout_oderServcice {

    @Autowired
    private OrderService orderService;

    @Test
    void contextLoads() {
        orderService.makeOrder("1", "1", 66);
    }
}
