package com.youping.test.service;

import com.youping.test.entities.User;

/**
 * @Author Zeng Zhuo
 * @Date 2020/6/17 16:39
 * @Describe
 */

public interface UserService {

    String getPassword(String name);
    int insert(User user);
    int updateUser(User user);
    int deleteById(Integer userId);
    User getUserById(Integer id);

}
