package com.yanjin.blog.dao;

import com.yanjin.blog.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

//利用JPA的api操作User的dao
public interface UserRepository extends JpaRepository<User,Long> {

    //根据用户名/邮箱+密码查询数据库
    User findByUsernameAndPassword(String username,String password);
    User findByEmailAndPassword(String email,String password);
}
