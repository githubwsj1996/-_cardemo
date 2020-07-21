package com.sj.cardemo.vo;

import lombok.Data;

import java.util.List;

@Data
public class CarCountVO {
    public List<CarVO> carVOList;
    public int counts;
}
