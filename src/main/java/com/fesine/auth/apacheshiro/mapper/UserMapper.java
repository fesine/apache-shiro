package com.fesine.auth.apacheshiro.mapper;

import com.fesine.auth.apacheshiro.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @description: 用户mapper接口
 * @author: Fesine
 * @createTime:2018/1/10
 * @update:修改内容
 * @author: Fesine
 * @updateTime:2018/1/10
 */
@Repository
public interface UserMapper {

    User findByUsername(@Param("username") String username);
}
