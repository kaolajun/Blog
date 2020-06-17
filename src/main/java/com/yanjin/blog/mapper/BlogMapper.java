package com.yanjin.blog.mapper;

import com.yanjin.blog.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

//注解@Mapper表示本类是一个mybatis的mapper类；DAO
@Mapper
@Repository
public interface BlogMapper {
    List<User> queryUserList();

    User queryUserById(int id);
}
