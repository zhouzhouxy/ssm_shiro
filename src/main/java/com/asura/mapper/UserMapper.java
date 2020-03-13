package com.asura.mapper;

import com.asura.domain.TbUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

public interface UserMapper extends BaseMapper<TbUser>{

    @Select("select r.rolename from tb_roles r, tb_role_user ru,tb_user u\n" +
            "where r.id=ru.rid and u.id=ru.uid\n" +
            "and u.u_name = #{uname}")
    Set<String> queryRoleNameByUName(String uname);

    @Select("select rolename from tb_roles")
    Set<String> queryAllRoles();
}
