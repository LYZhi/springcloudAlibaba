package com.zhi.usercenter.controller;

import com.zhi.usercenter.dao.entity.User;
import com.zhi.usercenter.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author LYZhi
 * @date 2021/4/8 下午2:28
 */
@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/hello")
    public Integer hello(){
        return 1;
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable Integer id){
        log.info("轮询被请求到了，，，");
        return  userService.findById(id);
    }

    @GetMapping("/test5")
    public User query(User user){
        return user;
    }
}
