package com.sj.cardemo.vo;

import com.sj.cardemo.entity.Admin;
import com.sj.cardemo.entity.User;
import lombok.Data;

@Data
public class AccountVO {
    private User user;
    private Admin admin;
}
