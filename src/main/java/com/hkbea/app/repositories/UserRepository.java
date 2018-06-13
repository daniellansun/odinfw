package com.hkbea.app.repositories;

import com.hkbea.app.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRepository {
    @Autowired
    protected SqlSession sqlSession;

    public List<User> selectAll(User user) {
        return sqlSession.selectList("UserMapper.selectAll", user);
    }
}
