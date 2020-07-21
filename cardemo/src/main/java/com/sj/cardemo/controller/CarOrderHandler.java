package com.sj.cardemo.controller;

import com.sj.cardemo.entity.CarOrder;
import com.sj.cardemo.repository.CarMenuRepository;
import com.sj.cardemo.repository.OrderRepository;
import com.sj.cardemo.service.OrderService;
import com.sj.cardemo.service.impl.OrderServiceImpl;
import com.sj.cardemo.util.KeyUtil;
import com.sj.cardemo.util.ResultUtil;
import com.sj.cardemo.vo.OrderVO;
import com.sj.cardemo.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("order")
public class CarOrderHandler {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CarMenuRepository carMenuRepository;

    @Autowired
    private OrderService orderService=new OrderServiceImpl();

    //用户创建订单
    @PostMapping("/createOrder")
    public ResultVO createOrder(@RequestBody CarOrder carOrder) {
        //orderId
        carOrder.setOrderId(KeyUtil.createId());
        //createTime updateTime
        carOrder.setCreateTime(new Date());
        int day=carOrder.getDays();
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR,calendar.get(Calendar.DAY_OF_YEAR)+day);
        carOrder.setUpdateTime(calendar.getTime());
        //state
        carOrder.setState(0);

        CarOrder data = orderRepository.save(carOrder);
        Map<String, String> orderId = new HashMap<>();
        orderId.put("orderId", data.getOrderId());

        //修改able
        carMenuRepository.updateByCarId(carOrder.getCarId());

        return ResultUtil.success(orderId);
    }

    //用户查询订单
    @GetMapping("/findOrderById/{page}/{size}/{id}")
    public ResultVO findById(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @PathVariable("id") Integer id) {
        int index=(page-1)*size;
        return ResultUtil.success(orderService.findAllByUid(id,index,size));
    }

    //管理员根据订单状态查询订单
    @GetMapping("/findAllByState/{page}/{size}")
    public ResultVO findAllByState(@PathVariable("page") Integer page,@PathVariable("size") Integer size){
        int index=(page-1)*size;
        return ResultUtil.success(orderService.findAllByState(index,size));
    }

    //同意 ---->然后创建对应还车记录
    @PostMapping("/agree")
    public ResultVO agreeOrder(@RequestBody CarOrder carOrder){
        orderRepository.save(carOrder);
        return ResultUtil.success(null);
    }

    //拒绝
    @PutMapping("/disagree/{carId}/{orderId}/{adminId}")
    public ResultVO disagreeOrder(@PathVariable("carId")Integer carId,
                                  @PathVariable("orderId") String orderId,
                                  @PathVariable("adminId") Integer adminId){
        carMenuRepository.updateCar(carId);
        orderRepository.updateById(orderId,adminId);
        return ResultUtil.success(null);
    }


    //通过adminId查询所有订单
    @GetMapping("/findAllByAdminId/{adminId}/{page}/{size}")
    public ResultVO findAllByAdminId(@PathVariable("adminId") Integer adminId,@PathVariable("page") Integer page,@PathVariable("size") Integer size){
        int index=(page-1)* size;
        return  ResultUtil.success(orderService.findAllByAdminId(adminId,index,size));
    }

    //柱状图
    @GetMapping("/getBar")
    public ResultVO getBar(){
        return ResultUtil.success(orderService.returnBar());
    }

    //扇形图
    @GetMapping("/getPie")
    public ResultVO getPie(){
        return ResultUtil.success(orderService.returnPie());
    }

    //导出用户所有订单
    @GetMapping("/findAllByUserId/{userId}")
    public ResultVO findAllByUserId(@PathVariable("userId") Integer userId){
        List<OrderVO> orderVOList=orderService.findAllByUserId(userId);
        return ResultUtil.success(orderVOList);
    }

    //管理员导出所有订单
    @GetMapping("/findAllOrder/{adminId}")
    public ResultVO findAllByAdminId(@PathVariable("adminId") Integer adminId){
        List<OrderVO> orderVOList=orderService.findAllByAdminId(adminId);
        return ResultUtil.success(orderVOList);
    }
}
