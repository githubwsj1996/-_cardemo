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
public class CarMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer carId;

    private String carName;
    private Integer categoryType;
    private String src;
    private String srcList;
    private String carFactory;
    private String displacement;
    private String kilometers;
    private String oliConsumption;
    private String carDesc;
    private BigDecimal carPrice;
    private Date createTime;
    private Date updateTime;
    private Integer able;
}
