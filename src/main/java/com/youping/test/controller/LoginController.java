package com.youping.test.controller;

import com.youping.test.entities.User;
import com.youping.test.service.UserService;
import com.youping.test.util.JwtUtil;
import com.youping.test.vo.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Zeng Zhuo
 * @Date 2020/6/17 16:31
 * @Describe
 */
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public ResponseBean login(@RequestBody User user, HttpServletResponse response){
        String password = userService.getPassword(user.getUsername());
        if(user.getPassword().equals(password)){
            Map<String,String> map = new HashMap<>();
            map.put("username",user.getUsername());
            //进行签名
            String token = JwtUtil.sign(map);
            response.setHeader(JwtUtil.TOKEN_HEADER,token);
            //允许前端访问response中的Authorization字段.
            response.setHeader("Access-Control-Expose-Headers", JwtUtil.TOKEN_HEADER);
            return new ResponseBean(200,"登录成功",null);
        }else{
            return new ResponseBean(400,"密码错误",null);
        }
    }
}
