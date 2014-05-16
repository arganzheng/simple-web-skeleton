package me.arganzheng.project.skeleton.dao;

import me.arganzheng.project.skeleton.BaseSpringTestCase;
import me.arganzheng.project.skeleton.model.User;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

/**
 * TODO 类实现描述
 * 
 * @author arganzheng
 * @date 2013-12-18
 */
public class AccountDaoTest extends BaseSpringTestCase {

	@Autowired
	private AccountDao accountDao;

	@Test
	public void testSelectAccountByName() {
		User user = accountDao.selectByUsername("arganzheng");
		Assert.notNull(user);

	}
}
