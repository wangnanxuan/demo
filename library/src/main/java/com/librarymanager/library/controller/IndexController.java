package com.librarymanager.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    //首页
    @RequestMapping({"/","/index"})
    public String index(){
        return "views/index";
    }

    //登陆页
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "views/login";
    }
    //权限页面
    @GetMapping("/unAuthorize")
    public String unAuthorize(){
        return "views/unAuthorize";
    }

}
