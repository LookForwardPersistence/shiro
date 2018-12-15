package com.dawn.testauthentication;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * Created by chenyu on 2018-12-15.
 * IniRealm  验证、授权
 */
public class IniRealmTest {

    IniRealm iniRealm = new IniRealm("classpath:user.iniRealm");
    @Test
    public void testAuthentication () {
        // 实例SercurityManager
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(iniRealm);
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        // 主题提交验证
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("Dawn","root");
        subject.login(token);
        System.out.println("is Authenticates: " + subject.isAuthenticated());
        subject.checkRole("admin");
        subject.checkRoles("admin","user");
        // 用户权限检查
        subject.checkPermission("user:delete");
        subject.checkPermissions("user:delete","user:update");
    }
}
