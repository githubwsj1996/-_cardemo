package com.sj.cardemo.controller;

import com.sj.cardemo.entity.User;
import com.sj.cardemo.exception.UserException;
import com.sj.cardemo.repository.UserRepository;
import com.sj.cardemo.service.UserService;
import com.sj.cardemo.service.impl.UserServiceImpl;
import com.sj.cardemo.util.KeyUtil;
import com.sj.cardemo.util.ResultUtil;
import com.sj.cardemo.vo.ResultVO;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@RestController
@RequestMapping("/account")
@Slf4j
public class UserHandler {


    @Autowired
    private UserRepository userRepository;


    @Autowired
    private final UserService userService = new UserServiceImpl();

    @PostMapping("/login")
    public ResultVO login(@RequestBody User user) {
        String username = user.getUsername();
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, user.getPassword());
        //usernamePasswordToken.setRememberMe(true);
        try {
            subject.login(usernamePasswordToken);
            return ResultUtil.success(userRepository.findAllByUsername(username));
        } catch (AuthenticationException e) {
            String message = "账号密码错误";
            return ResultUtil.error(message);
        }
    }

    //检查用户是否存在
    @GetMapping("/newName/{username}")
    public ResultVO newName(@PathVariable("username") String username) {
        return userService.newName(username);
    }

    //用户信息  保存/修改
    @PostMapping("/saveUser")
    public ResultVO saveUser(@RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("[用户注册]存在错误,user={}", user);
            throw new UserException(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        return ResultUtil.success(userService.register(user));
    }

    //通过user的id查询
    @GetMapping("/getUser/{id}")
    public ResultVO getUser(@PathVariable("id") Integer id) {
        User user = userRepository.findById(id).get();
        return ResultUtil.success(user);
    }

    //管理员修改
    @PutMapping("/adminEdit")
    public ResultVO saveAdmin(@RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("[用户注册]存在错误,user={}", user);
            throw new UserException(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        return ResultUtil.success(userService.adminEdit(user).getData());
    }


    //查询所有用户
    @GetMapping("/findAllUser/{page}/{size}")
    public ResultVO findAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<User> userPage = userRepository.findAll(pageable);
        for (User user : userPage) {
            if (user.getGender().equals("0")) {
                user.setGender("女");
            } else {
                user.setGender("男");
            }
        }
        return ResultUtil.success(userRepository.findAll(pageable));
    }


    //删除用户
    @DeleteMapping("/deleteUser/{id}")
    public ResultVO deleteUser(@PathVariable("id") Integer id) {
        userRepository.deleteById(id);
        return ResultUtil.success(null);
    }

    //头像上传
    @PostMapping("/uploadHeadImg")
    public ResultVO toUpload(MultipartFile file) throws Exception {
        String tType = file.getContentType();
        tType = tType.substring(tType.indexOf("/") + 1);

        if ("jpeg".equals(tType)) {
            tType = ".jpg";
        }

        String folder=System.getProperty("user.dir")+"_images";
        File imageFolder=new File(folder);
        String fileName = "T_" + KeyUtil.createId() + tType;
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


}
