package com.soga.util;

import com.soga.entity.Content;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: lx
 * Date: 2021/7/30 10:06
 * Content:
 */
@Component
public class HtmlParseUtil {
    // 测试一下
    public static void main(String[] args) throws IOException {
        HtmlParseUtil.parseJD("vue").forEach(System.out::println);
    }

    public static List<Content> parseJD(String keyword) throws IOException {
        /// 使用前需要联网
        // 请求url
        String url = "http://search.jd.com/search?keyword=" + keyword;
        // 1.解析网页(jsoup 解析返回的对象是浏览器Document对象)
        Document document = Jsoup.parse(new URL(url), 30000);
        // 使用document可以使用在js对document的所有操作
        // 2.获取元素（通过id）,id自己查网页
        Element j_goodsList = document.getElementById("J_goodsList");
        // 3.获取J_goodsList ul 每一个
        Elements lis = j_goodsList.getElementsByTag("li");
        System.out.println(lis);
        if (lis.isEmpty()) {
            return null;
        }

        // 4.获取li下的 img、price、name
        // list存储所有li下的内容
        List<Content> contents = new ArrayList<Content>();
        for (Element li : lis) {
            // 由于网站图片使用懒加载，将src属性替换为data-lazy-img
            String img = li.getElementsByTag("img").eq(0).attr("data-lazy-img");// 获取li下 第一张图片
            String name = li.getElementsByClass("p-name").eq(0).text();
            String price = li.getElementsByClass("p-price").eq(0).text();
            String shopName = li.getElementsByClass("p-shopnum").eq(0).text();
            // 封装为对象
            Content content = new Content(name, img, price, shopName);
            // 添加到list中
            contents.add(content);
        }
        // System.out.println(contents);
        // 5.返回 list
        return contents;
    }
}
