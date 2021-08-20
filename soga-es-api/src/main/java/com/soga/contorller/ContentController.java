package com.soga.contorller;

import com.soga.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Author: lx
 * Date: 2021/7/30 10:13
 * Content:
 */
@Controller
public class ContentController {
    @Autowired
    private ContentService contentService;

    // 先从京东抓取数据并存入es中
    @GetMapping("/parse/{keyword}")
    @ResponseBody
    public Boolean parse(@PathVariable("keyword") String keywords) throws Exception {
        return contentService.parseContent(keywords);
    }
//    // 在es中搜索数据返回结果
//    @GetMapping("/search/{keyword}/{pageNo}/{pageSize}")
//    public List<Map<String, Object>> search(@PathVariable("keyword") String keyword,
//                                            @PathVariable("pageNo") int pageNo,
//                                            @PathVariable("pageSize") int pageSize) throws IOException {
//        return contentService.searchPage(keyword, pageNo, pageSize);
//    }

    // 在es中搜索数据返回结果
    @GetMapping("/search/{keyword}/{pageNo}/{pageSize}")
    @ResponseBody
    public List<Map<String, Object>> search(@PathVariable("keyword") String keyword,
                                            @PathVariable("pageNo") int pageNo,
                                            @PathVariable("pageSize") int pageSize) throws IOException {
        return contentService.highlightSearch(keyword, pageNo, pageSize);
    }
}
