package com.sj.cardemo.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KeyUtil {

    //随机订单时间+6位随机数
    public static synchronized String createId(){
        Random random=new Random();
        Integer id=random.nextInt(900000)+100000;
        return  System.currentTimeMillis()+String.valueOf(id);
    }

    //随机生成数组中指定子项
    public static  synchronized  List<Integer> cars(List<Integer> list, int count){
        List<Integer> objectList=new ArrayList<>();
        Random random=new Random();
        int backNum=0;
        if(list.size()>=count){
            backNum=count;
        }else{
            backNum=list.size();
        }

        for(int i=0;i<backNum;i++){
            int target=random.nextInt(list.size());
            objectList.add(list.get(target));
            list.remove(target);
        }
        return objectList;
    }

}
