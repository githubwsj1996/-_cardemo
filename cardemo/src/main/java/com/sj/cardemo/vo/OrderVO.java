package com.sj.cardemo.vo;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class OrderVO {
    private String orderId;
    private Integer userId;
    private String username;
    private String userAddress;
    private String userPhone;
    private Integer carId;
    public  String carName;
    private String src;
    private BigDecimal carPrice;
    private Integer days;
    private BigDecimal totalPrice;
    private String createTime;
    private String updateTime;
    private String state;
    private Integer adminId;
    private String adminName;

    public OrderVO(String orderId, Integer userId, String userAddress, String userPhone, Integer carId, String src, BigDecimal carPrice, Integer days, BigDecimal totalPrice, String createTime, String updateTime, String state, Integer adminId) {
        this.orderId = orderId;
        this.userId = userId;
        this.userAddress = userAddress;
        this.userPhone = userPhone;
        this.carId = carId;
        this.src = src;
        this.carPrice = carPrice;
        this.days = days;
        this.totalPrice = totalPrice;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.state = state;
        this.adminId = adminId;
    }
}
