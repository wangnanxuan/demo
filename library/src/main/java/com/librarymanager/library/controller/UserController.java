package com.librarymanager.library.controller;

import com.librarymanager.library.mapper.UserMapper;
import com.librarymanager.library.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    /**
     * @param model 存储用户信息
     * @return 跳转用户信息页
     */
    @RequestMapping("/toTable")
    public String toTable(Model model) {
        List<User> users = userMapper.selectList(null);
        model.addAttribute("users", users);
        return "views/user_tables";
    }

    /**
     * @return 跳转添加用户页
     */
    @RequestMapping("/toAdd")
    public String toAdd() {
        return "views/user_insert";
    }

    //添加用户
    @RequestMapping("/add")
    public String add(User user, Model model) {
        int i = userMapper.insert(user);
        if (i == 0) {
            return "views/user_insert";
        }
        return "redirect:/user/toTable";
    }

    /**
     * @param id    用户id
     * @param model 删除信息
     * @return 删除用户成功重定向用户信息页，失败则跳转添加页
     */
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id, Model model) {
        int i = userMapper.deleteById(Long.parseLong(id));
        if (i == 0) {
            return "views/user_insert";
        }
        return "redirect:/user/toTable";
    }

    /**
     * @param id    用户 id
     * @param model 存储用户信息
     * @return 跳转修改页
     */
    @RequestMapping("/toUpdate/{id}")
    public String toUpdate(@PathVariable("id") String id, Model model) {
        User user = userMapper.selectById(Long.parseLong(id));
        model.addAttribute("user", user);
        return "views/user_update";
    }

    //修改
    @RequestMapping("/update")
    public String update(User user,
                         Model model) {
        int i = userMapper.updateById(user);
        if (i == 0) {
            return "views/user_update";
        }
        return "redirect:/user/toTable";
    }

}
