package com.sj.cardemo.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ReturnVO {

    private Integer returnId;
    private String orderId;
    private String userName;
    private String userPhone;
    private String carName;
    private BigDecimal carPrice;
    private Integer days;
    private BigDecimal totalPrice;
    private String createTime;
    private String updateTime;
    private String  adminName;
    private Integer adminId;
    private Integer able;
}
