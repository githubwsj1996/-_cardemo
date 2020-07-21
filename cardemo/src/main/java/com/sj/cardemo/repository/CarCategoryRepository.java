package com.sj.cardemo.repository;

import com.sj.cardemo.entity.CarCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CarCategoryRepository  extends JpaRepository<CarCategory,Integer> {

    @Query(value="select * from car_category where category_id!=5 ",nativeQuery=true)
    public List<CarCategory> findAllNot5();



}
