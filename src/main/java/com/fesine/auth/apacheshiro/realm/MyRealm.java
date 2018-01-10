package com.fesine.auth.apacheshiro.realm;

import com.fesine.auth.apacheshiro.model.Permission;
import com.fesine.auth.apacheshiro.model.Role;
import com.fesine.auth.apacheshiro.model.User;
import com.fesine.auth.apacheshiro.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @description: 自定义shiro验证服务
 * @author: Fesine
 * @createTime:2018/1/10
 * @update:修改内容
 * @author: Fesine
 * @updateTime:2018/1/10
 */
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User) principalCollection.fromRealm(this.getClass().getName()).iterator().next();
        List<String> permissionList = new ArrayList<>();
        List<String> roleNameList = new ArrayList<>();
        Set<Role> roles = user.getRoles();
        if (CollectionUtils.isNotEmpty(roles)) {
            for (Role role : roles) {
                roleNameList.add(role.getRname());
                Set<Permission> permissions = role.getPermissions();
                if (CollectionUtils.isNotEmpty(permissions)) {
                    for (Permission permission : permissions) {
                        permissionList.add(permission.getName());
                    }
                }
            }

        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissionList);
        info.addRoles(roleNameList);
        return info;
    }

    /**
     * 认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        User user = userService.findByUsername(username);
        AuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), this
                .getClass().getName());
        return info;
    }
}
