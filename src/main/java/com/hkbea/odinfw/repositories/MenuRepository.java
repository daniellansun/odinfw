package com.hkbea.odinfw.repositories;

import com.hkbea.odinfw.ui.MenuTree;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MenuRepository {
    @Autowired
    protected SqlSession sqlSession;

    public List<MenuTree.Menu> selectByParentId(String parentId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("parentId", parentId);

        return sqlSession.selectList("MenuMapper.selectByParentId", paramMap);
    }
}
