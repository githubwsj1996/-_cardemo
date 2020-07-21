package com.sj.cardemo.service;

import com.sj.cardemo.vo.CarCountVO;
import com.sj.cardemo.vo.ResultVO;

import java.util.List;


public interface CarMenuService {
    public CarCountVO findAll(Integer index, Integer limit, Integer able);

    public ResultVO findSrcByCarId();
}
