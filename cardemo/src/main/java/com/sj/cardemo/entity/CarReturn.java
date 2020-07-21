package com.sj.cardemo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
public class CarReturn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer returnId;

    private String orderId;
    private String userName;
    private String userPhone;
    private String carName;
    private BigDecimal carPrice;
    private Integer days;
    private BigDecimal totalPrice;
    private Date  createTime;
    private Date updateTime;
    private String  adminName;
    private Integer adminId;
    private Integer able;
}
