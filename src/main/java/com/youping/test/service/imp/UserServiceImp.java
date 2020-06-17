package com.youping.test.service.imp;

import com.youping.test.dao.UserDao;
import com.youping.test.entities.User;
import com.youping.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Zeng Zhuo
 * @Date 2020/6/17 16:40
 * @Describe
 */
@Service
public class UserServiceImp implements UserService {

    @Autowired(required = false)
    private UserDao userDao;

    @Override
    public String getPassword(String name) {
        User user = userDao.selectOneByUsername(name);
        if(user!=null)
            return user.getPassword();
        else
            return null;
    }

    @Override
    public int insert(User user) {
        return userDao.insert(user);
    }

    @Override
    public int updateUser(User user) {
        return userDao.update(user);
    }

    @Override
    public int deleteById(Integer userId) {
        return userDao.delete(userId);
    }

    @Override
    public User getUserById(Integer id) {
        return userDao.selectOneById(id);
    }
}
