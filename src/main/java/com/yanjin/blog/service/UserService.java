package com.yanjin.blog.service;

import com.yanjin.blog.pojo.User;

public interface UserService {
    User checkUser(String username,String password);
}
