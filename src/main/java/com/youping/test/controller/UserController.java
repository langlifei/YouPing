package com.youping.test.controller;

import com.youping.test.entities.User;
import com.youping.test.service.UserService;
import com.youping.test.vo.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Zeng Zhuo
 * @Date 2020/6/17 16:31
 * @Describe
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseBean register(@RequestBody User user){
        if(userService.insert(user)>0)
            return new ResponseBean(200,"注册成功!",null);
        else
            return new ResponseBean(400,"注册失败!",null);
    }

    @GetMapping("/users/{userId}")
    public ResponseBean getUser(@PathVariable("userId") Integer userId){
        User user = userService.getUserById(userId);
        return new ResponseBean(200,"获取用户成功",user);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseBean deleteUser(@PathVariable("userId") Integer userId){
        if(userService.deleteById(userId)>0)
            return new ResponseBean(200,"删除成功!",null);
        else
            return new ResponseBean(400,"删除失败!",null);
    }

    @PutMapping("/users/{userId}")
    public ResponseBean updateUser(@PathVariable("userId") Integer userId,@RequestBody User user){
        user.setUserId(userId);
        if(userService.updateUser(user)>0)
            return new ResponseBean(200,"修改成功!",null);
        else
            return new ResponseBean(400,"修改失败!",null);
    }
}
