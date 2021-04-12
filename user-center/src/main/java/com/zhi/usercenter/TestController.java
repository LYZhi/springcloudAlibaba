package com.zhi.usercenter;

import com.zhi.usercenter.dao.UserMapper;
import com.zhi.usercenter.dao.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author LYZhi
 * @date 2021/4/8 上午11:43
 */
@RestController
public class TestController {

    @Resource
    private UserMapper userMapper;

    @GetMapping("/test")
    public User testInter(){
        User user = new User();
        user.setBonus(100);
        user.setAvatarUrl("zzzzz");
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());

        this.userMapper.insertSelective(user);
        return user;

    }
}
