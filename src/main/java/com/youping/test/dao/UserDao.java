package com.youping.test.dao;

import com.youping.test.entities.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @Author Zeng Zhuo
 * @Date 2020/6/17 17:26
 * @Describe
 */

public interface UserDao {

    @Insert("insert into test_user(username,password,email,status) values(#{username},#{password},#{email},#{status})")
    int insert(User user);
    @Update("update test_user set password=#{password} where userId = #{userId}")
    int update(User user);
    @Delete("delete from test_user where userId = #{userId}")
    int delete(Integer userId);
    @Select("select * from test_user where userId = #{id}")
    User selectOneById(Integer id);
    @Select("select * from test_user where username = #{name}")
    User selectOneByUsername(String name);
}
