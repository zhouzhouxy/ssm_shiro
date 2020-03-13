package com.asura.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

@Data
@TableName("tb_user")
public class TbUser extends Model {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("u_name")
    private String uName;

    @TableField("u_pass")
    private String uPass;

    private Integer status;

    private String salt;

}
