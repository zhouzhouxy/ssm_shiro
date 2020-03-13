package com.asura.realm;

import com.asura.domain.TbUser;
import com.asura.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

//Shiro从Realm获取安全数据(如用户、角色、权限)
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;


    /**
     * 授权方法
     *  1.该方法什么时候会被调用？
     *      当访问某个资源 被Roles[]过滤器。
     *   2.方法的入参是什么？
     *      主题唯一标识
     * @param principals
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
       //   1.得到当前主题的用户名
        String primaryPrincipal = (String)principals.getPrimaryPrincipal();
        System.out.println(primaryPrincipal);
        //2.根据用户名 查询用户拥有哪些角色,只要包含了管理员角色。那么所有角色该用户都具备
        Set<String> roles = userService.queryRoleNameByUName(primaryPrincipal);

       //如果查询中的角色名包括admin 那么就查询数据库中所有的角色赋予当前用户
       if(roles.contains("admin"))
            roles=userService.queryAllRoles();
        AuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo(roles);

        // set的循环方式
         Iterator<String> it = roles.iterator();
         while(it.hasNext()){
         String next = it.next();
         System.out.println(next);
         }
        return authorizationInfo;
    }
    /**
     * 认证方法
     * 1.该方法什么时候会被调用？
     *      Subject.login(token);
     * 2.方法的入参是什么？
     *      UsernamePasswordToken
     * @param
     * @return
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
       // 1. 得到用户名
        UsernamePasswordToken upToken=(UsernamePasswordToken)token;

        // upToken.getPassword();   前端输入的密码
        String username = upToken.getUsername();
        //2.根据用户名查询数据库 是否存在
        TbUser tbUser = userService.queryUName(username);
        if(tbUser==null){
            throw  new UnknownAccountException("用户名不存在");
        }
        if(tbUser.getStatus()==0){
            throw new LockedAccountException("该账户因异常被锁定");
        }

        //3.密码的比较是由Shiro完成的
        /**
         * 身份验证：一般需要提供如身份ID等一些标识信息来表明登录者的身份，如提供email，用户名/密码来证明。
         * 在shiro中，用户需要提供principal是（身份）和credentials(证明)给shiro，从而应用能验证用户身份：
         * principals：身份，即主体的标识属性，可以是任何属性，如用户名、邮箱等，唯一即可。
         *  一个主体可以有多个principals，但只有一个primary principals，一般是用户名/邮箱/手机号、
         *  credentials：证明/凭证，即只有主体知道的安全值，如密码/数字证书等。
         *  最常见的principals和credentials组合就是用户名/密码了
         */
        Object principal=username;
        Object credentials=tbUser.getUPass();//数据库查询出的密码
        String realmName=super.getName();//realm名称
        //实现的方式是将每一个口令同一个叫做“盐”（salt)的n为随机数相关联
        ByteSource salt = ByteSource.Util.bytes(username);

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, credentials,salt, realmName);
        return info;
    }
     public static void main(String[] args) {
        /**
         * 注册时，完成密码的加密
         * param1 hashAlgorithmName  加密的名称
         * param2 credentials 要加密的字符串
         * param3 hashIterations 加密的次数
         */
        String hashAlgorithmName="MD5";
        Object credentials="123456";
        int hashIterations=1903;
        String uname="zhouyi";
        ByteSource salt = ByteSource.Util.bytes(uname);

        Object object = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
        System.out.println(object);
    }
}
