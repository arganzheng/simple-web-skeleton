package me.arganzheng.project.skeleton.dao;

import me.arganzheng.project.skeleton.BaseSpringTestCase;
import me.arganzheng.project.skeleton.model.User;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

public class MyBatisSqlSessionTest extends BaseSpringTestCase {

    @Autowired
    private SqlSession sqlSession;

    @Test
    public void testSelectUserByUsernameWithDao() {
        User user = (User) sqlSession.selectOne("me.arganzheng.project.skeleton.mapper.UserMapper.selectAccountByName",
                                                      "arganzheng");
        Assert.notNull(user);
    }
}
