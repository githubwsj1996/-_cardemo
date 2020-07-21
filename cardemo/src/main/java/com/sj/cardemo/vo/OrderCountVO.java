package com.sj.cardemo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OrderCountVO {
    private List<OrderVO> orderVO;
    private int count;
}
