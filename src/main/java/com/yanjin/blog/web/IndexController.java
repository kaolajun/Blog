package com.yanjin.blog.web;

import com.yanjin.blog.exception.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(){
//        String blog = null;
//        if (blog == null){
//            throw new NotFoundException("页面不存在!");
//        }
        return "index";
    }
    @GetMapping("/about")
    public String about(){
//        String blog = null;
//        if (blog == null){
//            throw new NotFoundException("页面不存在!");
//        }
        return "about";
    }
    @GetMapping("/archives")
    public String archives(){
//        String blog = null;
//        if (blog == null){
//            throw new NotFoundException("页面不存在!");
//        }
        return "archives";
    }
    @GetMapping("/blog")
    public String blog(){
//        String blog = null;
//        if (blog == null){
//            throw new NotFoundException("页面不存在!");
//        }
        return "blog";
    }
    @GetMapping("/tags")
    public String tags(){
//        String blog = null;
//        if (blog == null){
//            throw new NotFoundException("页面不存在!");
//        }
        return "tags";
    }
    @GetMapping("/types")
    public String types(){
//        String blog = null;
//        if (blog == null){
//            throw new NotFoundException("页面不存在!");
//        }
        return "types";
    }

}
