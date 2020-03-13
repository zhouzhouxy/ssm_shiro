package com.asura.service;

import com.asura.domain.TbUser;

import java.util.Set;

public interface UserService {

    /**
     * 查询用户名是否存在
     * @param uName
     * @return
     */
    TbUser queryUName(String uName);

    /**
     * 根据用户名密码查询
     * @param uName
     * @param password
     * @return
     */
    Integer queryUser(String uName,String password);

    Set<String> queryRoleNameByUName(String uName);

    Set<String> queryAllRoles();
}
