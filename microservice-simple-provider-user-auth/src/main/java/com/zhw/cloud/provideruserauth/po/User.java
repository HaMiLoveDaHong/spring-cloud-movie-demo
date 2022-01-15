package com.zhw.cloud.provideruserauth.po;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @Author zhw
 * @since 2021/10/31
 */
@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String username;

    @Column
    private String name;

    @Column
    private Integer age;

    @Column
    private BigDecimal balance;

}
