package com.yanjin.blog.web.admin;

import com.yanjin.blog.pojo.Type;
import com.yanjin.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    /**springboot将前端配置好的页面自动配置到传入pageable中，实现分页
     * 配置一页十条数据，按id倒序排列
     */
    public String types(@PageableDefault(size = 10,sort = {"id"},direction = Sort.Direction.DESC)
                                    Pageable pageable,
                                    Model model){
        //给前端传递page对象
        model.addAttribute("page",typeService.listType(pageable));

        return "admin/types";
    }

    @GetMapping("/types/input")
    public String input(Model model){
        model.addAttribute("type", new Type());
        return "admin/types-input";
    }

    /**restful,新增type
     *  type和result对象一定要挨在一起写才会生效
     * @param type 输入的type对象
     * @param result 用于校验功能的对象
     * @param attributes 用于传递错误代码
     * @return 页面地址
     */

    @PostMapping("/types")
    public String post(@Valid Type type, BindingResult result, RedirectAttributes attributes){
        Type type1 = typeService.getTypeByName(type.getName());
        if (type1 != null){
            result.rejectValue("name", "nameError","不能重复添加分类！");
            return "admin/types-input";
        }

        //后端校验Type是否为空（blank）
        if (result.hasErrors()) {
            return "admin/types-input";
        }

        //操作数据库
        Type t = typeService.saveType(type);
        if (t == null){
            attributes.addFlashAttribute("message", "新增失败");
        }else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/types";
    }

    /**
     * 提交修改type,修改和
     * @param type 输入的type对象
     * @param result 用于校验功能的对象
     * @param id 被修改的type对象的id
     * @param attributes 用于传递错误代码
     * @return 页面地址
     */
    @PostMapping("/types/{id}")
    public String editPost(@Valid Type type, BindingResult result,@PathVariable("id") Long id, RedirectAttributes attributes){
        Type type1 = typeService.getTypeByName(type.getName());
        if (type1 != null){
            result.rejectValue("name", "nameError","不能重复添加分类！");
            return "admin/types-input";
        }

        //后端校验Type是否为空（blank）
        if (result.hasErrors()) {
            return "admin/types-input";
        }

        //操作数据库
        Type t = typeService.updateType(id,type);
        if (t == null){
            attributes.addFlashAttribute("message", "编辑失败");
        }else {
            attributes.addFlashAttribute("message", "编辑成功");
        }
        return "redirect:/admin/types";
    }

    //修改页面
    @GetMapping("/types/{id}")
    public String toEditPage(@PathVariable("id") Long id,Model model){
        Type t = typeService.getType(id);
        model.addAttribute("type", t);
        //重用添加页面
        return "admin/types-input";
    }

    //提交修改分类
//    @PutMapping("/types")
//    public String update(Type type){
//        typeService.updateType(type.getId(), type);
//    }

    //要配置允许自定义请求方法才能使用delete访问到
    @DeleteMapping("/types/{id}")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/types";
    }

}
