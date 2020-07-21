package com.sj.cardemo.service.impl;

import com.sj.cardemo.entity.CarReturn;
import com.sj.cardemo.repository.CarReturnRepository;
import com.sj.cardemo.service.ReturnService;
import com.sj.cardemo.vo.ReturnCountVO;
import com.sj.cardemo.vo.ReturnVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReturnServiceImpl implements ReturnService {
    @Autowired
    private CarReturnRepository carReturnRepository;

    private List<ReturnVO> returnVOList=null;
    @Override
    public ReturnCountVO findAllByAdminIdAndAble(Integer index, Integer size, Integer adminId, Integer able) {
        List<CarReturn> returnList=carReturnRepository.findAllByAdminIdAndAble(index,size,adminId,able);
        CarReturnToReturnVO(returnList);
        int count=carReturnRepository.countByAdminIdAndAble(adminId,able);

        ReturnCountVO returnCountVO=new ReturnCountVO();
        returnCountVO.setReturnVOList(returnVOList);
        returnCountVO.setCounts(count);
        return returnCountVO;
    }

    @Override
    public ReturnCountVO findAllByAdminId(Integer index, Integer size, Integer adminId,int able) {
        List<CarReturn> returnList=carReturnRepository.findAllByAdminId(index,size,adminId,able);
        CarReturnToReturnVO(returnList);
        int count=carReturnRepository.countByAdminId(adminId);

        ReturnCountVO returnCountVO=new ReturnCountVO();
        returnCountVO.setReturnVOList(returnVOList);
        returnCountVO.setCounts(count);
        return returnCountVO;
    }

    private void CarReturnToReturnVO(List<CarReturn> returnList) {
        returnVOList=new ArrayList<>();
        for (CarReturn carReturn : returnList) {
            String createTime=carReturn.getCreateTime().toString();
            String updateTime=carReturn.getUpdateTime().toString();
            ReturnVO returnVO=new ReturnVO();
            BeanUtils.copyProperties(carReturn,returnVO);
            returnVO.setCreateTime(createTime);
            returnVO.setUpdateTime(updateTime);
            returnVOList.add(returnVO);
        }
    }
}
