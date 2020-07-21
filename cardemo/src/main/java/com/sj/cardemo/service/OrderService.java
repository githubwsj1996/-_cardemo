package com.sj.cardemo.service;

import com.sj.cardemo.vo.BarVO;
import com.sj.cardemo.vo.OrderCountVO;
import com.sj.cardemo.vo.OrderVO;
import com.sj.cardemo.vo.PieVO;

import java.util.List;


public interface OrderService {
    public OrderCountVO findAllByUid(Integer id, Integer index, Integer size);
    public OrderCountVO findAllByState(Integer index,Integer size);
    public OrderCountVO findAllByAdminId(Integer adminId,Integer index,Integer size);
    public BarVO  returnBar();
    public List<PieVO> returnPie();
    public List<OrderVO> findAllByUserId(Integer userId);
    public List<OrderVO> findAllByAdminId(Integer adminId);
}
