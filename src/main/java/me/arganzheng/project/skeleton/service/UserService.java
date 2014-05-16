package me.arganzheng.project.skeleton.service;

import me.arganzheng.project.skeleton.exception.ResourceAlreadyExistException;
import me.arganzheng.project.skeleton.mapper.UserMapper;
import me.arganzheng.project.skeleton.model.User;
import me.arganzheng.project.skeleton.util.PasswordUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userDao;

    public Integer addUser(User user) {
        if (userExist(user)) {
            throw new ResourceAlreadyExistException(user.getUsername() + " aleardy exist!");
        }
        user.setPassword(PasswordUtils.generatePasswordHash(user.getPassword()));
        return userDao.insert(user);
    }

    public boolean userExist(User user) {
        if (userDao.selectByUsername(user.getUsername()) != null) {
            return true;
        }
        return false;
    }

    public User getByUsername(String name) {
        return userDao.selectByUsername(name);
    }

    public User verifyPassword(String username, String password) {
        User user = getByUsername(username);
        boolean pass = user != null && PasswordUtils.checkPasswordHash(password, user.getPassword());
        return pass ? user : null;
    }
}
