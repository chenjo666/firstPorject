package com.nowcoder.community.dao;

import org.apache.ibatis.annotations.Mapper;
import com.nowcoder.community.pojo.User;

@Mapper
public interface UserMapper {

    User selectById(int id);

    User selectByName(String username);

    User selectByEmail(String email);

    int insertUser(User user);

    int updateStatusById(int id, int status);

    int updateHeaderUrlById(int id, String headerUrl);

    int updatePasswordById(int id, String password);

}
