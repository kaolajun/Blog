package com.yanjin.blog.web.admin;

import com.yanjin.blog.pojo.User;
import com.yanjin.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String loginPage(){
        return "admin/login";
    }

    //检查是否存在这个用户，并且传递到session中
    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpSession session,
                        RedirectAttributes attributes){
        User user = userService.checkUser(username, password);
        if (user != null){
            //不要把密码放到session里面，不安全
            user.setPassword(null);
            session.setAttribute("user", user);
            return "admin/index";
        }else {
            //model不适用于重定向
//            model.addAttribute("message","用户名和密码错误");
            //向前端传递错误信息
            attributes.addFlashAttribute("msg","用户名和密码错误");
            //不存在的用户，重定向到登录页面
            return "redirect:/admin";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}
