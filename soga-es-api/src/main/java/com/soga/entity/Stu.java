package com.soga.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Author: lx
 * Date: 2021/7/29 13:54
 * Content:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Stu {
    private String stuId;
    private Integer age;
    private String name;
    private Double money;
    private String sign;
    private String description;

}
