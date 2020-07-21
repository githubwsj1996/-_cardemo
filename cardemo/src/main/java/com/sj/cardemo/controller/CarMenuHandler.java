package com.sj.cardemo.controller;

import com.sj.cardemo.entity.CarMenu;
import com.sj.cardemo.repository.CarMenuRepository;
import com.sj.cardemo.service.CarMenuService;
import com.sj.cardemo.service.impl.CaMenuServiceImpl;
import com.sj.cardemo.util.KeyUtil;
import com.sj.cardemo.util.ResultUtil;
import com.sj.cardemo.vo.CarCountVO;
import com.sj.cardemo.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;


@RestController
@RequestMapping("/car")
public class CarMenuHandler {

    @Autowired
    private CarMenuRepository carMenuRepository;

    @Autowired
    private CarMenuService carMenuService = new CaMenuServiceImpl();

    //用户首页
    @GetMapping("/findAll/{page}/{size}")
    public ResultVO findAllByAble(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<CarMenu> data = carMenuRepository.findAllByAble(pageable, 1);
        return ResultUtil.success(data);
    }

    //分类查询
    @GetMapping("/findByType/{index}/{page}/{size}")
    public ResultVO findAllByType(@PathVariable("index") Integer index,
                                  @PathVariable("page") Integer page,
                                  @PathVariable("size") Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return ResultUtil.success(carMenuRepository.findByCategoryTypeAndAble(index, pageable, 1));
    }

    //搜索框
    @GetMapping("/findByName/{carName}")
    public ResultVO findByName(@PathVariable("carName") String carName) {
        return ResultUtil.success(carMenuRepository.findByCarNameContaining(carName));
    }

    //走马灯图片显示
    @GetMapping("/carousel")
    public  ResultVO  findSrcByCarId(){
        return carMenuService.findSrcByCarId();
    }

    //根据carId查询able=1
    @GetMapping("/carousel/{carId}")
    public  ResultVO findCarouselData(@PathVariable("carId") Integer carId){
        return ResultUtil.success(carMenuRepository.findById(carId).get());
    }

    //管理员收首页
    @GetMapping("/findCarManage/{page}/{size}")
    public ResultVO findCarManage(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        int index=(page-1)*size;
        CarCountVO carVOList = carMenuService.findAll(index, size, 1);
        return ResultUtil.success(carVOList);
    }

    //汽车图片上传
    @PostMapping("/toUpload")
    public ResultVO upload(MultipartFile file){
        String tType = file.getContentType();
        tType = file.getContentType().substring(tType.indexOf("/") + 1);

        if ("jpeg".equals(tType)) {
            tType = ".jpg";
        }

        String folder=System.getProperty("user.dir")+"_images";
        File imageFolder=new File(folder);
        String fileName = "T_" +  KeyUtil.createId()  + tType;
        File f=new File(imageFolder,fileName);

        if(!f.getParentFile().exists()){
            f.getParentFile().mkdirs();
        }

        try {
            file.transferTo(f);
            String imgUrl = "http://39.106.154.21:8181/img/file/" + f.getName();
            return ResultUtil.success(imgUrl);
        } catch (IOException e) {
            e.printStackTrace();
            return ResultUtil.error("上传失败");
        }
    }

    //管理员添加汽车信息
    @PostMapping("/saveCar")
    public ResultVO saveCar(@RequestBody CarMenu carMenu){
        if(carMenu.getCarId()!=null){
            carMenu.setAble(1);
            carMenu.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        } else{
            carMenu.setAble(1);
            carMenu.setCreateTime(new Timestamp(System.currentTimeMillis()));
            carMenu.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        }
        carMenuRepository.save(carMenu);
        return ResultUtil.success(null);
    }

    //CarId查询汽车信息
    @GetMapping("/findByCarId/{id}")
    public ResultVO findByCarId(@PathVariable("id") Integer id){
        CarMenu carMenu=carMenuRepository.findById(id).get();
        return ResultUtil.success(carMenu);
    }

    //删除汽车信息
    @DeleteMapping("/deleteByCarId/{id}")
    public ResultVO deleteByCarId(@PathVariable("id") Integer id){
        carMenuRepository.deleteById(id);
        return ResultUtil.success(null);
    }



}
