package com.fesine.auth.apacheshiro.service;

import com.fesine.auth.apacheshiro.model.User;

/**
 * @description: 用户服务接口
 * @author: Fesine
 * @createTime:2018/1/10
 * @update:修改内容
 * @author: Fesine
 * @updateTime:2018/1/10
 */
public interface UserService {

    User findByUsername(String username);
}
