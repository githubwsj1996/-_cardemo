package com.sj.cardemo.service.impl;


import com.sj.cardemo.entity.CarCategory;
import com.sj.cardemo.entity.CarOrder;
import com.sj.cardemo.repository.CarCategoryRepository;
import com.sj.cardemo.repository.CarMenuRepository;
import com.sj.cardemo.repository.OrderRepository;
import com.sj.cardemo.repository.UserRepository;
import com.sj.cardemo.service.OrderService;
import com.sj.cardemo.vo.BarVO;
import com.sj.cardemo.vo.OrderCountVO;
import com.sj.cardemo.vo.OrderVO;
import com.sj.cardemo.vo.PieVO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarMenuRepository carMenuRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CarCategoryRepository categoryRepository;

    private List<OrderVO> orderVOList = null;

    //用户id查询订单
    @Override
    public OrderCountVO findAllByUid(Integer id, Integer index, Integer size) {
        List<CarOrder> carOrders = orderRepository.findAllByUserId(id, index, size);

        CarOrderToOrderVO(carOrders);

        int count = orderRepository.countByUid(id);

        return new OrderCountVO(orderVOList, count);
    }

    //支付状态查询订单
    @Override
    public OrderCountVO findAllByState(Integer index, Integer size) {
        List<CarOrder> carOrders = orderRepository.findAllByState(index, size);

        CarOrderToOrderVO(carOrders);

        int count = orderRepository.countByState();

        return new OrderCountVO(orderVOList, count);
    }

    //管理员id 查询所有订单
    @Override
    public OrderCountVO findAllByAdminId(Integer adminId, Integer index, Integer size) {
        List<CarOrder> carOrderList = orderRepository.findAllByAdminId(adminId, index, size);

        orderVOList = carOrderList.stream()
                .map(e -> new OrderVO(
                        e.getOrderId(),
                        e.getUserId(),
                        e.getUserAddress(),
                        e.getUserPhone(),
                        e.getCarId(),
                        e.getSrc(),
                        e.getCarPrice(),
                        e.getDays(),
                        e.getTotalPrice(),
                        e.getCreateTime().toString(),
                        e.getUpdateTime().toString(),
                        e.getState().toString(),
                        e.getAdminId()
                )).collect(Collectors.toList());

        for (OrderVO orderVO : orderVOList) {
            orderVO.setUsername(userRepository.findUserById(orderVO.getUserId()).getUsername());
            orderVO.setCarName(carMenuRepository.findAllByCarId(orderVO.getCarId()).getCarName());
            orderVO.setAdminName(userRepository.findById(orderVO.getAdminId()).get().getUsername());
            state(orderVO);
        }

        int count = orderRepository.countBYAdminId(adminId);

        return new OrderCountVO(orderVOList, count);
    }

    //柱形图
    @Override
    public BarVO returnBar() {
        BarVO barVO=new BarVO();
        List<String> name=new ArrayList<>();
        List<Integer> counts=new ArrayList<>();
        List<CarOrder> carOrderList=orderRepository.findByState();
        int[] count1=orderRepository.counts();
        for (int i=0;i<carOrderList.size();i++) {
            String carName=carMenuRepository.findById(carOrderList.get(i).getCarId()).get().getCarName();
            name.add(carName);
            counts.add(count1[i]);
        }
        barVO.setName(name);
        barVO.setCount(counts);
        return barVO;
    }

    //扇形图
    @Override
    public List<PieVO> returnPie() {
        List<PieVO> pieVOList=new ArrayList<>();
        PieVO pieVO=null;
        List<CarOrder> carOrderList=orderRepository.findPie();
        //总销量
        int count=orderRepository.countPie();
        List<Integer> categories=new ArrayList<>();

        //查询租车汽车的分类
        for (CarOrder carOrder : carOrderList) {
            int categoryType=carMenuRepository.findById(carOrder.getCarId()).get().getCategoryType();
            categories.add(categoryType);
        }
        int result1=0;
        int result2=0;
        int result3=0;
        int result4=0;
        for (Integer category : categories) {
            switch (category) {
                case 1:
                    result1++;
                    break;
                case 2:
                    result2++;
                    break;
                case 3:
                    result3++;
                    break;
                case 4:
                    result4++;
                    break;
            }
        }
        int[] result={result1,result2,result3,result4};
        List<CarCategory> carCategoryList=categoryRepository.findAllNot5();
        for (int i = 0; i < carCategoryList.size(); i++) {
            pieVO=new PieVO();
            pieVO.setName(carCategoryList.get(i).getCategoryName());
            pieVO.setValue(result[i]);

            pieVOList.add(pieVO);
        }
        return pieVOList;
    }


    @Override
    public List<OrderVO> findAllByUserId(Integer userId) {
        List<CarOrder> carOrderList=orderRepository.findAllByUserId(userId);
        CarOrderToOrderVO(carOrderList);
        return orderVOList;
    }

    @Override
    public List<OrderVO> findAllByAdminId(Integer adminId) {
        List<CarOrder> carOrderList=orderRepository.findAllByAdminId(adminId);
        CarOrderToOrderVO(carOrderList);
        return orderVOList;
    }


    private void CarOrderToOrderVO(List<CarOrder> carOrders) {
        orderVOList = carOrders.stream()
                .map(e -> new OrderVO(
                        e.getOrderId(),
                        e.getUserId(),
                        e.getUserAddress(),
                        e.getUserPhone(),
                        e.getCarId(),
                        e.getSrc(),
                        e.getCarPrice(),
                        e.getDays(),
                        e.getTotalPrice(),
                        e.getCreateTime().toString(),
                        e.getUpdateTime().toString(),
                        e.getState().toString(),
                        e.getAdminId()
                )).collect(Collectors.toList());

        for (OrderVO orderVO : orderVOList) {
            orderVO.setUsername(userRepository.findUserById(orderVO.getUserId()).getUsername());
            orderVO.setCarName(carMenuRepository.findAllByCarId(orderVO.getCarId()).getCarName());
            state(orderVO);
        }
    }

    private void state(OrderVO orderVO) {
        switch (orderVO.getState()) {
            case "0":
                orderVO.setState("订单未受理");
                break;
            case "1":
                orderVO.setState("订单已受理");
                break;
            case "2":
                orderVO.setState("订单未通过");
                break;
            case "3":
                orderVO.setState("车辆已归还");
                break;
        }
    }


}
