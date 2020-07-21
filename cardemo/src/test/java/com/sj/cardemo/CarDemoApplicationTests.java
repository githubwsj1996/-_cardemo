package com.sj.cardemo;

import com.sj.cardemo.repository.CarMenuRepository;
import com.sj.cardemo.repository.OrderRepository;
import com.sj.cardemo.util.KeyUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest

class CarDemoApplicationTests {

    @Autowired
    private CarMenuRepository carMenuRepository;

    @Test
    void contextLoads() {
        System.out.println(carMenuRepository.getAllCarId());
    }



}
