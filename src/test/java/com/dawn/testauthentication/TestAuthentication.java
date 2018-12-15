package com.dawn.testauthentication;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by chenyu on 2018-12-15.
 * 基本的认证、授权
 */
public class TestAuthentication {
    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    @Before
    public void addUser(){
        // 验证 授权
        simpleAccountRealm.addAccount("Dawn","root","admin","user");
    }
    @Test
    public void authentication(){
        // 实例SecurityManager
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(simpleAccountRealm);

        // 主题认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject =SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("Dawn","root");
        // 登录验证
        subject.login(token);
        System.out.println("is Authenticated:" + subject.isAuthenticated());
//        subject.logout();
//        System.out.println("is Authenticated:" + subject.isAuthenticated());
        // 检查是否有改角色权限
       subject.checkRole("admin");
//       多个角色
        subject.checkRoles("admin","user");
    }
}
