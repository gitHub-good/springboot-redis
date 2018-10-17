package com.xu.springboot.redis.springbootredis.mapper;

import com.xu.springboot.redis.springbootredis.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Insert("insert into tbl_user_redis(user_id,user_name,sex,email) values (#{userId},#{username},#{sex},#{email})")
    int insertUser(User user);
    @Select("select * from tbl_user_redis where user_id = #{userId}")
    User getUserById(String userId);

    @Delete("delete from tbl_user_redis where user_id = #{userId}")
    int deleteUserById(String userId);

    @Select("select * from tbl_user_redis")
    List<User> listUser();
    @Update("update tbl_user_redis set user_name = #{username}, sex = #{sex}, email = #{email}  where user_id = #{userId}")
    int updateUser(User user);
}
