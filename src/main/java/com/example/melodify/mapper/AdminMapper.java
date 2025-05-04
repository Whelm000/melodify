package com.example.melodify.mapper;



/**
 * 管理员Dao
 */

public interface AdminMapper {
    /**
     *验证密码是否正确
     */
    public int verifyPassword(String username,String password);
}
