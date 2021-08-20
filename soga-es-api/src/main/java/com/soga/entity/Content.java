package com.soga.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: lx
 * Date: 2021/7/30 10:06
 * Content:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Content {
    private String title;
    private String img;
    private String price;
    private String shopName;
}
