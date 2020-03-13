package com.asura.controller.shiro;

import com.asura.domain.TbResources;
import com.asura.service.ResourcesService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FilterChainDefinitionMapBuild {

    /**
     * 方法： 在数据库中查询所有的资源相关配置
     * /list.jsp = anon
     *      /user/login= anon
     *      /aaa/logout= logout
     *      /teacher.jsp=roles[tea]
     *      /student.jsp=roles[stu]
     *      /admin.jsp=roles[tea,stu]
     *      /** = authc
     *      map.put("/list.jsp","anon");
     *      map.put("/user/login","anon");
     *      map.put("/aaa/logout","logout");
     *      map.put("/teacher.jsp","roles[tea]");
     *      map.put("/student.jsp","roles[stu]");
     *      map.put("/admin.jsp","roles[tea,stu]");
     *      map.put("/**","authc");
     */
    @Autowired
    private ResourcesService resourcesService;

    public Map<String,String> build(){
        //查询数据库的所有数据
        Map<String,String> map=new LinkedHashMap<String,String>();
        List<TbResources> list =
                resourcesService.selectList();
        for (TbResources tbResources : list) {
            System.out.println(tbResources.getKeyurl()+"---->"+tbResources.getFilterName());
            map.put(tbResources.getKeyurl(),tbResources.getFilterName());
        }
        return map;
    }
}
