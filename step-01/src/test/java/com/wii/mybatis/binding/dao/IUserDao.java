package com.wii.mybatis.binding.dao;

public interface IUserDao {
    String queryUserName(String uid);

    String queryUserAge(String uid);
}
