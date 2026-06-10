package com.Luojia.service;


import com.Luojia.dto.UserLoginDTO;
import com.Luojia.entity.User;

public interface UserService {

    /**
     * 微信用户登录
     * @param userLoginDTO
     * @return
     */
    public User wxLogin(UserLoginDTO userLoginDTO);
}
