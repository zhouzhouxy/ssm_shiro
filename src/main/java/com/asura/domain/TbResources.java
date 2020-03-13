package com.asura.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_resources")
public class TbResources {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String keyurl;

    @TableField("filtername")
    private String filterName;

    @TableField("sortnum")
    private Integer sortNum;

    @TableField("status")
    private Integer status;
}
