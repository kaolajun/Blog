package com.yanjin.blog.service;

import com.yanjin.blog.dao.UserRepository;
import com.yanjin.blog.pojo.User;
import com.yanjin.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    //注入数据库的操作
    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findByEmailAndPassword(username, MD5Utils.code(password));
        if (user == null){
            user = userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        }
        return user;
    }
}
