package com.sj.cardemo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
public class CarOrder {
    @Id
    private String orderId;
    private Integer userId;
    private String userAddress;
    private String userPhone;
    private Integer carId;
    private String src;
    private BigDecimal carPrice;
    private Integer days;
    private BigDecimal totalPrice;
    private Date createTime;
    private Date updateTime;
    private Integer state;
    private Integer adminId;
}
