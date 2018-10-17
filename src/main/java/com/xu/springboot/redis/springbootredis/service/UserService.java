package com.xu.springboot.redis.springbootredis.service;

import com.xu.springboot.redis.springbootredis.mapper.UserMapper;
import com.xu.springboot.redis.springbootredis.pojo.User;
import com.xu.springboot.redis.springbootredis.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    UserMapper userMapper;

    public int addUser(User user){
        user.setUserId("xll-"+RandomUtil.getRandom(6,RandomUtil.TYPE.LETTER_NUMBER));
        System.out.println("添加数据---》随机产生Id："+user.getUserId());
        return userMapper.insertUser(user);
    }

    @Cacheable(value = "user",key = "#p0")
    public User getUserById(String userId){
        System.out.println("查询数据库的数据----》id："+userId);
        return userMapper.getUserById(userId);
    }

    @CachePut(value = "user",key = "#user.userId")
    public User updateUser(User user){
        System.out.println("更新数据信息----》id："+user.getUserId());
        User user1 = userMapper.getUserById(user.getUserId());
        if(!("".equals(user.getSex()))){
            user1.setSex(user.getSex());
        }
        if(!("".equals(user.getEmail()))){
            user1.setEmail(user.getEmail());
        }
        if(!("".equals(user.getUsername()))){
            user1.setUsername(user.getUsername());
        }
        int count = userMapper.updateUser(user1);
        return user1;
    }

    @CacheEvict(value = "user", key = "#p0")
    public int deleteUser(String userId){
        System.out.println("删除数据信息！！！----》id："+userId);
        return  userMapper.deleteUserById(userId);
    }
}
