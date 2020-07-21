package com.sj.cardemo.service;

import com.sj.cardemo.vo.ReturnCountVO;

public interface ReturnService {
    public ReturnCountVO findAllByAdminIdAndAble(Integer index,Integer size,Integer adminId,Integer able);
    public ReturnCountVO findAllByAdminId(Integer index,Integer size,Integer adminId,int able);
}
