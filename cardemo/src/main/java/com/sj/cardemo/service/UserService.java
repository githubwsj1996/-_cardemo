package com.sj.cardemo.service;

import com.sj.cardemo.entity.User;
import com.sj.cardemo.vo.ResultVO;

import java.util.Map;

public interface UserService {
    public ResultVO newName(String username);
    public Map<String,Integer> register(User user);
    public ResultVO adminEdit(User user);
}
