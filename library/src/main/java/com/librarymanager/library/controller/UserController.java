package com.librarymanager.library.controller;
import com.librarymanager.library.pojo.User;
import com.librarymanager.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    /**
     * @param model 存储用户信息
     * @return 跳转用户信息页
     */
    @RequestMapping("/toTable")
    public String toTable(Model model){
        List<User> users = userService.queryAllUsers();
        model.addAttribute("users",users);
        return "views/user_tables";
    }

    /**
     *
     * @return 跳转添加用户页
     */
    @RequestMapping("/toAdd")
    public String toAdd(){
        return "views/user_insert";
    }

    /**
     *
     * @param username 用户名
     * @param password  密码
     * @param name 昵称
     * @param tel 手机号
     * @param email 邮箱
     * @param level 用户级别
     * @param model 添加信息
     * @return 添加用户成功重定向用户信息页，失败则跳转添加页
     */
    @RequestMapping("/add")
    public String add(@RequestParam("username") String username,
                      @RequestParam("password") String password,
                      @RequestParam("name") String name,
                      @RequestParam("tel") String tel,
                      @RequestParam("email") String email,
                      @RequestParam("level") String level,
                      Model model){
        if (level==null||level.equals("")){
            return "redirect:/user/toTable";
        }
        int i = userService.saveUser(new User(null, username, password, name, tel, email, Integer.parseInt(level), null, null, null, null));
        if (i == 0){
            model.addAttribute("msg","user insert fail!");
            return "views/user_insert";
        }
        return "redirect:/user/toTable";
    }

    /**
     *
     * @param id 用户id
     * @param model 删除信息
     * @return 删除用户成功重定向用户信息页，失败则跳转添加页
     */
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id, Model model){
        int i = userService.deleteUserById(Integer.parseInt(id));
        if (i == 0){
            model.addAttribute("msg","user delete fail!");
            return "views/user_insert";
        }
        return "redirect:/user/toTable";
    }

    /**
     *
     * @param id 用户 id
     * @param model 存储用户信息
     * @return  跳转修改页
     */
    @RequestMapping("/toUpdate/{id}")
    public String toUpdate(@PathVariable("id") String id,Model model){
        User user = userService.queryUserById(Integer.parseInt(id));
        model.addAttribute("user",user);
        return "views/user_update";
    }

    /**
     * @param  id 用户id
     * @param username 用户名
     * @param password  密码
     * @param name 昵称
     * @param tel 手机号
     * @param email 邮箱
     * @param level 用户级别
     * @param model 修改信息
     * @return 修改用户成功重定向用户信息页，失败则跳转添加页
     */
    @RequestMapping("/update")
    public String update(@RequestParam("id") String id,
                         @RequestParam("username") String username,
                         @RequestParam("password") String password,
                         @RequestParam("name") String name,
                         @RequestParam("tel") String tel,
                         @RequestParam("email") String email,
                         @RequestParam("level") String level,
                         Model model){
        int i = userService.updateUserById(new User(Integer.parseInt(id), username, password, name, tel, email, Integer.parseInt(level), null, null, null, null));
        if (i == 0){
            model.addAttribute("msg","user update fail!");
            return "views/user_update";
        }
        return "redirect:/user/toTable";
    }

}
