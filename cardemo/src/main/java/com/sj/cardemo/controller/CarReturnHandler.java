package com.sj.cardemo.controller;


import com.sj.cardemo.entity.CarReturn;
import com.sj.cardemo.repository.CarMenuRepository;
import com.sj.cardemo.repository.CarReturnRepository;
import com.sj.cardemo.repository.OrderRepository;
import com.sj.cardemo.service.ReturnService;
import com.sj.cardemo.service.impl.ReturnServiceImpl;
import com.sj.cardemo.util.ResultUtil;
import com.sj.cardemo.vo.ResultVO;
import com.sj.cardemo.vo.ReturnCountVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
@RequestMapping("/return")
public class CarReturnHandler {

    @Autowired
    private CarReturnRepository carReturnRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CarMenuRepository carMenuRepository;

    @Autowired
    private ReturnService returnService=new ReturnServiceImpl();

    @PostMapping("/saveReturn")
    public ResultVO saveReturn(@RequestBody CarReturn carReturn){
        carReturn.setCreateTime(new Timestamp(System.currentTimeMillis()));
        carReturn.setAble(0);
        carReturnRepository.save(carReturn);
        return ResultUtil.success(null);
    }

    @GetMapping("/findAllByAdminId/{page}/{size}/{adminId}")
    public ResultVO findAllByAdminIdAndAble(@PathVariable("page") Integer page,
                                     @PathVariable("size") Integer size,
                                     @PathVariable("adminId") Integer adminId ){
        int index=(page-1)*size;
        ReturnCountVO returnCountVO=returnService.findAllByAdminIdAndAble(index,size,adminId,0);
        return  ResultUtil.success(returnCountVO);
    }

    //还车审核
    @PutMapping("/returnCar/{orderId}")
    public ResultVO returnCar(@PathVariable("orderId") String orderId){
        carReturnRepository.updateByOrderId(orderId,new Timestamp(System.currentTimeMillis()),new Timestamp(System.currentTimeMillis()));
        orderRepository.updateByOrderId(orderId,new Timestamp(System.currentTimeMillis()));

        int carId=orderRepository.findAllById(orderId).getCarId();
        carMenuRepository.updateCar(carId);

        return ResultUtil.success(null);
    }


    //查询所有还车信息
    @GetMapping("/findAll/{page}/{size}/{adminId}")
    public ResultVO findAllByAdminId(@PathVariable("page") Integer page,
                                     @PathVariable("size") Integer size,
                                     @PathVariable("adminId") Integer adminId){
        int index=(page-1)*size;
        return  ResultUtil.success(returnService.findAllByAdminId(index,size,adminId,1));
    }

}
