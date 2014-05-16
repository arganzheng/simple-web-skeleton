package me.arganzheng.project.skeleton.mapper;

import me.arganzheng.project.skeleton.BaseSpringTestCase;
import me.arganzheng.project.skeleton.mapper.UserMapper;
import me.arganzheng.project.skeleton.model.User;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

public class AccountMapperTest extends BaseSpringTestCase {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectUserByUsername() {
        User user = userMapper.selectByUsername("arganzheng");
        Assert.notNull(user);
    }

}
