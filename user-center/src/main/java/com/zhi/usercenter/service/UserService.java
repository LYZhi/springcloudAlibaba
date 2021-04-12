package com.zhi.usercenter.service;

import com.zhi.usercenter.dao.UserMapper;
import com.zhi.usercenter.dao.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author LYZhi
 * @date 2021/4/8 下午2:24
 */
@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public User findById(Integer id){
        return userMapper.selectByPrimaryKey(id);
    }
}
