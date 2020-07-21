package com.sj.cardemo.service.impl;

import com.sj.cardemo.entity.User;
import com.sj.cardemo.repository.UserRepository;
import com.sj.cardemo.service.UserService;
import com.sj.cardemo.util.ResultUtil;
import com.sj.cardemo.vo.AccountVO;
import com.sj.cardemo.vo.ResultVO;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    //检查用户是否存在
    @Override
    public ResultVO newName(String username) {
        User user=userRepository.findAllByUsername(username);
        if(user==null){
           return ResultUtil.error("用户名不存在,请先注册！");
        } else {
            User user1=new User();
            user1.setId(user.getId());
            user1.setSrc(user.getSrc());
            return ResultUtil.success(user1);
        }
    }

    //注册， 用户修改
    @Override
    public Map<String,Integer> register(User user) {
        user.setRole("user");
        user.setPerm("0");
        User result=null;
        String salt= new SecureRandomNumberGenerator().nextBytes().toString();
        //注册
        if(user.getId()==null){
            int times=2;
            user.setSalt(salt);
            String encodePassword=new SimpleHash("md5",user.getPassword(),salt,times).toString();
            user.setPassword(encodePassword);
            user.setCheckPassword(encodePassword);
            result=userRepository.save(user);
        }
        //修改
        if(user.getId()!=null){
            User user1=userRepository.findById(user.getId()).get();
            if(user.getPassword().equals(user1.getPassword())){
                user.setSalt(user1.getSalt());
                result=userRepository.save(user);
            }
            else if(!user.getPassword().equals(user1.getPassword())){
                int times=2;
                user.setSalt(salt);
                String encodePassword=new SimpleHash("md5",user.getPassword(),salt,times).toString();
                user.setPassword(encodePassword);
                user.setCheckPassword(encodePassword);
                result=userRepository.save(user);
            }
        }
        Map<String, Integer> data=new HashMap<>();
        assert result != null;
        data.put("user_id",result.getId());
        return data;
    }

    //管理员添加
    @Override
    public ResultVO adminEdit(User user) {
        user.setRole("admin");
        user.setPerm("1");
        if(user.getId()!=null){
            User user1=userRepository.findById(user.getId()).get();
            if(user.getPassword().equals(user1.getPassword())){
                user.setSalt(user1.getSalt());
                userRepository.save(user);
            }
            else if(!user.getPassword().equals(user1.getPassword())){
                int times=2;
                String salt=new SecureRandomNumberGenerator().nextBytes().toString();
                user.setSalt(salt);
                String encodePassword=new SimpleHash("md5",user.getPassword(),salt,times).toString();
                user.setPassword(encodePassword);
                user.setCheckPassword(encodePassword);
                userRepository.save(user);
            }
        }
        return ResultUtil.success("修改成功,请重新登录");
    }
}
