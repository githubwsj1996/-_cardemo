package com.sj.cardemo.repository;

import com.sj.cardemo.entity.CarReturn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface CarReturnRepository extends JpaRepository<CarReturn,Integer> {
    @Query(value="select * from car_return where admin_id=?3 and able=?4 limit ?1,?2",nativeQuery=true)
    public List<CarReturn> findAllByAdminIdAndAble(Integer index, Integer size, Integer adminId, Integer able);

    public int countByAdminIdAndAble(Integer adminId,Integer able);

    @Transactional
    @Rollback(value=false)
    @Modifying(clearAutomatically = true)
    @Query(value="update car_return set able=1,create_time=?2,update_time=?3 where order_id=?1",nativeQuery=true)
    public void updateByOrderId(String orderId, Timestamp createTime,Timestamp updateTime);

    //查询所有
    @Query(value="select * from car_return where admin_id=?3 and able=?4 limit ?1,?2",nativeQuery=true)
    public List<CarReturn> findAllByAdminId(Integer index, Integer size, Integer adminId,int able);

    @Query(value="select count(*) from car_return where admin_id=?1 and able=1",nativeQuery=true)
    public int countByAdminId(Integer adminId);
}
