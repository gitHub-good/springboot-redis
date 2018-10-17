package com.xu.springboot.redis.springbootredis.pojo;

import java.io.Serializable;

public class User implements Serializable {

    private String userId;
    private String username;
    private String sex;
    private String email;

    public User(){
    }
    public User(String userId, String username, String sex, String email) {
        this.userId = userId;
        this.username = username;
        this.sex = sex;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
