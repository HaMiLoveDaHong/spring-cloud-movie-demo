package com.zhw.cloud.consumermoviefegincustomzing.vo;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author zhw
 * @since 2021/10/31
 */
@Data
public class UserVo implements Serializable {

    private long id;

    private String username;

    private String name;

    private Integer age;

    private BigDecimal balance;

}
