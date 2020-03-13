package com.asura.service.impl;

import com.asura.domain.TbResources;
import com.asura.mapper.ResourcesMapper;
import com.asura.service.ResourcesService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourcesServiceImpl implements ResourcesService {

    @Autowired
    private ResourcesMapper resourcesMapper;

    @Override
    public List<TbResources> selectList() {
        QueryWrapper<TbResources> query = new QueryWrapper<TbResources>();
        query.orderByAsc("sortnum");
        query.eq("status",1);
        return resourcesMapper.selectList(query);
    }
}
