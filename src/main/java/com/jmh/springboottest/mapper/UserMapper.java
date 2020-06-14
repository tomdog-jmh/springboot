package com.jmh.springboottest.mapper;

import com.jmh.springboottest.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> selectAll();
}