package me.arganzheng.project.skeleton.mapper;

import me.arganzheng.project.skeleton.model.User;

/**
 * <pre>
 * 为了代替手工使用 SqlSessionDaoSupport 或 SqlSessionTemplate 编写数据访问对象 (DAO)的代码,
 * MyBatis-Spring 提供了一个动态代理的实现:MapperFactoryBean。
 * 如果接口上有写SQL annotation，否则走XML文件（需要配置XML路径）。NameSpace为Mapper.getClass()，这意味着namespace很长。。另外id必须与接口方法名称相同。
 * @see http://mybatis.github.io/spring/zh/mappers.html
 * 
 * </pre>
 * 
 * @author arganzheng
 * @date 2013-12-09
 */
public interface UserMapper {

    public User selectByUsername(String username);

    public Integer insert(User user);

    public boolean update(User user);

    public int delete(Integer id);
}
