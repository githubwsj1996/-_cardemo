package com.sj.cardemo.vo;

import lombok.Data;

import java.util.List;

@Data
public class ReturnCountVO {
    public List<ReturnVO> returnVOList;
    public int counts;
}
