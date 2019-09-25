package com.jdh.ecommerce.bean;

import lombok.Data;

@Data
public class Dto {
    private String token;
    private Long tokenCreatedDate;
    private Long tokenExpiryDate;
    private String isLogin;
}
