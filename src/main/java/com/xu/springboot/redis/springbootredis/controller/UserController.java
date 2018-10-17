package com.xu.springboot.redis.springbootredis.controller;

import com.xu.springboot.redis.springbootredis.pojo.User;
import com.xu.springboot.redis.springbootredis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {



    @Autowired
    UserService userService;

    @RequestMapping("/add/{userName}/{sex}/{email}")
    public String addUser(@PathVariable("userName") String userName,@PathVariable("sex")String sex,@PathVariable("email")String email){
        User user = new User();
        user.setUsername(userName);
        user.setSex(sex);
        user.setEmail(email);
        Integer count = userService.addUser(user);
        String msg = null;
        if(count>0){
            msg = "数据添加成功！！！";
        }else {
            msg = "数据添加失败！！！";
        }
        return msg;
    }

    @RequestMapping("/getUser")
    public User getUser(@RequestParam(name = "userId", required = false) String userId){
        return userService.getUserById(userId);
    }
    @RequestMapping("/updateUser")
    public User updateUser(@RequestParam("userId")String userId,@RequestParam("sex")String sex,
                           @RequestParam("email")String email,@RequestParam("username")String username){
        User user = new User();
        if(sex != null){
        user.setSex(sex);
        }
        if(email != null){
        user.setEmail(email);
        }
        if(username != null){
        user.setUsername(username);
        }
        user.setUserId(userId);
        user = userService.updateUser(user);
        return user;
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(@RequestParam("userId") String userId){
        int count = userService.deleteUser(userId);
        String msg = null;
        if(count>0){
            msg = "数据删除成功！！！";
        }else {
            msg = "数据删除失败！！！";
        }
        return msg;
    }
}
