package me.arganzheng.project.skeleton.dao;

import me.arganzheng.project.skeleton.model.User;

import org.springframework.stereotype.Repository;

/**
 * <pre>
 * 使用SqlSession接口达到ibatis的使用效果 
 * @see http://mybatis.github.io/spring/zh/sqlsession.html
 * 
 * </pre>
 * 
 * @author arganzheng
 * @date 2013-12-09
 */
@Repository
public class AccountDao extends BaseDao {

    public User selectByUsername(String name) {
        return (User) sqlSession.selectOne("me.arganzheng.project.skeleton.mapper.UserMapper.selectByUsername",
                                              name);
    }
}
