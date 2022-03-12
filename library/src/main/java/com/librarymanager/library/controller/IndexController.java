package com.librarymanager.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Controller
public class IndexController {

    /**
     * @return 设置首页
     */
    @RequestMapping({"/","/index"})
    public String index(){
        return "views/index";
    }

    /**
     * @return 跳转登录页
     */
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "views/login";
    }

    @GetMapping("/unAuthorize")
    public String unAuthorize(){
        return "views/unAuthorize";
    }

    //自动装配redis配置类
    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/sendCode")
    @ResponseBody
    public String sendCode(String phone_no){
        //获取随机验证码
        String code = getCode(6);
        //拼接key
        String codeKey = "verifyCode:" + phone_no + ":code";
        String countKey = "verifyCode" + phone_no + ":count";

        String count = (String) redisTemplate.boundValueOps(countKey).get();
        //获取key的自增后端值
        RedisAtomicLong redisAtomicLong = new RedisAtomicLong(countKey, redisTemplate.getConnectionFactory());
        long countLong = redisAtomicLong.incrementAndGet();
        System.out.println(countLong);
        //判断时候是第一次
        if (countLong == 0) {
            redisTemplate.boundValueOps(countKey).set(1);
        }else if (countLong >3){
            return "limit";
        }
        //向redis中存储，以手机号为键
       redisTemplate.boundSetOps(codeKey).add(code);
        System.out.println(redisTemplate.boundSetOps(codeKey).members());
        //设置过期时间
       redisTemplate.expire(codeKey,2, TimeUnit.MINUTES);
        return "true";
    }
    
    @RequestMapping("/verifyCode")
    @ResponseBody
    public String verifyCode(String phone_no,String verify_code){
        String codeKey = "verifyCode:" + phone_no + ":code";
        String countKey = "verifyCode:" + phone_no + ":count";
        Set<String> members = redisTemplate.boundSetOps(codeKey).members();
        //判断存储的验证码是否包含输入的验证码
        if (members.contains(verify_code)){
            return "true";
        }
        return "false";
    }
    
    //随机生成验证码
    public String getCode(int length){
        String code = "" ;
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int rand = random.nextInt(10);
            code += rand;
        }
        return code;
    }

    //秒杀
    @PostMapping("/secKill")
    @ResponseBody
    public String secKill(String pid){
        //随机生成uid
        Random random = new Random();
        int uid = random.nextInt(5000);
        //拼接key
        String kcKey = "secKill:"+pid+":kc";
        String userKey = "secKill:" + pid + ":user";
        //获取库存
        String kc = (String) redisTemplate.boundValueOps(kcKey).get();
        System.out.println("商品库存数量--->"+kc);
        //秒杀未开始
        if (kc == null){
            System.out.println("秒杀活动未开始！");
            return "1";
        }
        //已经秒杀成功,表示秒杀成功后存储user在set中已经存在秒杀成功后的uid
        if (redisTemplate.boundSetOps(userKey).isMember(uid)) {
            System.out.println("已经秒杀过了，请勿重复！");
            return "2";
        }
        //秒杀成功 判断库存 大于0 则减库存；若小于0，秒杀结束
        if (Integer.parseInt(kc) == 0){
            System.out.println("库存为0，秒杀结束");
            return "3";
        }
        //如果库存大于0，减库存，加人
        if (Integer.parseInt(kc) > 0){
            redisTemplate.boundSetOps(userKey).add(uid);
            redisTemplate.boundValueOps(kcKey).decrement();
            Set secKillSuccessUsers = redisTemplate.boundSetOps(userKey).members();
            secKillSuccessUsers.forEach(System.out::println);
            System.out.println("---秒杀成功---");
            return "4";
        }
        return "1";
    }

}
