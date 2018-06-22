package com.hkbea.odinfw.repositories;

import com.hkbea.odinfw.ui.MenuTree;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Component
public class MenuRepository {
    @Autowired
    protected SqlSession sqlSession;

    public List<MenuTree.Menu> selectByParentId(String parentId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("parentId", parentId);
        List<Map> resultList = sqlSession.selectList("MenuMapper.selectByParentId", paramMap);

        List<MenuTree.Menu> menuList = new LinkedList<>();
        for (Map record : resultList) {
            MenuTree.Menu menu = new MenuTree.Menu((String) record.get("ID"), (String) record.get("TEXT"), "1".equals(record.get("LEAF")), (String) record.get("PARENT_ID"), (String) record.get("FORM_NAME"));

            menuList.add(menu);
        }

        return menuList;
    }
}
