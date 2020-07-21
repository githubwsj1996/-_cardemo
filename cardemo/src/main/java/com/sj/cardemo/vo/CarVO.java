package com.sj.cardemo.vo;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class CarVO {
    private Integer carId;
    private String carName;
    private Integer categoryType;
    private String categoryName;
    private String src;
    private String srcList;
    private String carFactory;
    private String displacement;
    private String kilometers;
    private String oliConsumption;
    private String carDesc;
    private BigDecimal carPrice;
    private String createTime;
    private String updateTime;
    private Integer able;
}
