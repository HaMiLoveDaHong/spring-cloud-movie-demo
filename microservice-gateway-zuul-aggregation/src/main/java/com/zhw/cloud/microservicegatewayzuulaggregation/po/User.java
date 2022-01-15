package com.zhw.cloud.microservicegatewayzuulaggregation.po;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author zhw
 * @since 2021/10/31
 */
@Data
public class User implements Serializable {

    private long id;


    private String username;


    private String name;


    private Integer age;


    private BigDecimal balance;

}
