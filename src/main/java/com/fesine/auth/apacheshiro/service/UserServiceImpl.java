package com.fesine.auth.apacheshiro.service;

import com.fesine.auth.apacheshiro.mapper.UserMapper;
import com.fesine.auth.apacheshiro.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description: 用户服务实现类
 * @author: Fesine
 * @createTime:2018/1/10
 * @update:修改内容
 * @author: Fesine
 * @updateTime:2018/1/10
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }
}
