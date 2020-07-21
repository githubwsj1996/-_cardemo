package com.sj.cardemo.service.impl;

import com.sj.cardemo.entity.CarMenu;
import com.sj.cardemo.repository.CarCategoryRepository;
import com.sj.cardemo.repository.CarMenuRepository;
import com.sj.cardemo.service.CarMenuService;
import com.sj.cardemo.util.KeyUtil;
import com.sj.cardemo.util.ResultUtil;
import com.sj.cardemo.vo.CarCountVO;
import com.sj.cardemo.vo.CarVO;
import com.sj.cardemo.vo.CarouselVO;
import com.sj.cardemo.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class CaMenuServiceImpl implements CarMenuService {

    @Autowired
    private CarMenuRepository carMenuRepository;

    @Autowired
    private CarCategoryRepository categoryRepository;

    @Override
    public CarCountVO findAll(Integer index, Integer limit, Integer able) {
        List<CarMenu> carMenuList = carMenuRepository.findAllByAble(index, limit, able);
        List<CarVO> carVOList = new ArrayList<>();
        for (CarMenu carMenu : carMenuList) {
            String createTime = carMenu.getCreateTime().toString();
            String updateTime = carMenu.getUpdateTime().toString();
            CarVO carVO = new CarVO();
            BeanUtils.copyProperties(carMenu, carVO);
            carVO.setCategoryName(categoryRepository.findById(carMenu.getCategoryType()).get().getCategoryName());
            carVO.setCreateTime(createTime);
            carVO.setUpdateTime(updateTime);
            carVOList.add(carVO);
        }
        int count = carMenuRepository.countByAble(1);
        CarCountVO carCountVO = new CarCountVO();
        carCountVO.setCarVOList(carVOList);
        carCountVO.setCounts(count);

        return carCountVO;
    }

    @Override
    public ResultVO findSrcByCarId() {

        List<Integer> carMenuList = carMenuRepository.getAllCarId();
        List<Integer> carIds = new ArrayList<>(carMenuList);

        List<Integer> carsList = KeyUtil.cars(carIds, 4);

        List<CarouselVO> carouselVOList = new ArrayList<>();
        CarouselVO carouselVO = null;
        for (Integer id : carsList) {
            carouselVO = new CarouselVO();
            List<CarMenu> carMenuList1=carMenuRepository.findSrc(id);
            for (CarMenu carMenu : carMenuList1) {
                carouselVO.setCarId(carMenu.getCarId());
                carouselVO.setCarName(carMenu.getCarName());
                carouselVO.setSrc(carMenu.getSrc());
            }
            carouselVOList.add(carouselVO);
        }
        return ResultUtil.success(carouselVOList);
    }
}
