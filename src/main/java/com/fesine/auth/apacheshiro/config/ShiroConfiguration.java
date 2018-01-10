package com.fesine.auth.apacheshiro.config;

import com.fesine.auth.apacheshiro.realm.MyCredentialsMatcher;
import com.fesine.auth.apacheshiro.realm.MyRealm;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;

import java.util.LinkedHashMap;

/**
 * @description: bean的定义自下而上
 * @author: Fesine
 * @createTime:2018/1/10
 * @update:修改内容
 * @author: Fesine
 * @updateTime:2018/1/10
 */
@Configuration
public class ShiroConfiguration {

    @Bean
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager
                                                      securityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager);
        /**
         * 定义跳转url
         */
        bean.setLoginUrl("/login");
        bean.setSuccessUrl("/index");
        bean.setUnauthorizedUrl("/unauthorized");

        /**
         * 定义访问权限
         * authc anon 为defaultFilter中的值
         * @Link DefaultFilter
         */
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/index", "authc");
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/loginUser", "anon");
        filterChainDefinitionMap.put("/druid/*", "anon");
        filterChainDefinitionMap.put("/admin", "roles[admin]");
        filterChainDefinitionMap.put("/edit", "perms[edit]");
        filterChainDefinitionMap.put("/**", "user");
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return bean;
    }

    @Bean("securityManager")
    public SecurityManager securityManager(@Qualifier("myRealm") MyRealm myRealm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(myRealm);
        return manager;
    }

    @Bean("myRealm")
    public MyRealm myRealm(@Qualifier("credentialsMatcher") MyCredentialsMatcher
                                   credentialsMatcher) {
        MyRealm myRealm = new MyRealm();
        myRealm.setCredentialsMatcher(credentialsMatcher);
        //使用缓存
        myRealm.setCacheManager(new MemoryConstrainedCacheManager());
        return myRealm;
    }

    /**
     * 自定义密码校验bean
     *
     * @return
     */
    @Bean("credentialsMatcher")
    public MyCredentialsMatcher credentialsMatcher() {
        return new MyCredentialsMatcher();
    }

    @Bean
    AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier
                                                                                    ("securityManager")
                                                                                    SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    @Bean
    DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }
}
