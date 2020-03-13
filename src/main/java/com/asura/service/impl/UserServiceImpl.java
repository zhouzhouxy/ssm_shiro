package com.asura.service.impl;

import com.asura.domain.TbUser;
import com.asura.mapper.UserMapper;
import com.asura.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public TbUser queryUName(String uName) {
        QueryWrapper<TbUser> query = new QueryWrapper<TbUser>();
        query.eq("u_name",uName);
        return userMapper.selectOne(query);
    }

    public Integer queryUser(String uName, String password) {
        QueryWrapper<TbUser> query = new QueryWrapper<TbUser>();
        query.eq("u_name",uName).eq("u_pass",password);
        return userMapper.selectCount(query);
    }

    @Override
    public Set<String> queryRoleNameByUName(String uName) {
        return userMapper.queryRoleNameByUName(uName);
    }

    @Override
    public Set<String> queryAllRoles() {
        return userMapper.queryAllRoles();
    }
}
