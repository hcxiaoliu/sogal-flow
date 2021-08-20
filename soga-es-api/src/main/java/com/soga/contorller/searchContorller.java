package com.soga.contorller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Author: lx
 * Date: 2021/7/30 9:51
 * Content:
 */
@Controller
public class searchContorller {

    @RequestMapping({"/", "/index"})
    public String index() {
        return "index";
    }
}
