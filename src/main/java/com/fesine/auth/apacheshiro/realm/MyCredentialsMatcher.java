package com.fesine.auth.apacheshiro.realm;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * @description: 自定义密码校验规则
 * @author: Fesine
 * @createTime:2018/1/10
 * @update:修改内容
 * @author: Fesine
 * @updateTime:2018/1/10
 */
public class MyCredentialsMatcher extends SimpleCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken token1 = (UsernamePasswordToken) token;
        String password = new String(token1.getPassword());
        String dbPassword = (String) info.getCredentials();
        return this.equals(password, dbPassword);
    }
}
