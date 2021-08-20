package com.soga.bootOrder;

import com.soga.service.ttl.Ttl_makeOrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Author: lx
 * Date: 2021/8/5 12:17
 * Content:
 */
@SpringBootTest
public class ttl_oderServcice {

    @Autowired
    private Ttl_makeOrderService ttlMakeOrderService;
    //五秒就消失了

    @Test
    void contextLoads() {
        ttlMakeOrderService.makeOrderTtl();
        ttlMakeOrderService.makettlmsg();
    }

}
