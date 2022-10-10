package com.nowcoder.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.pojo.User;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User getUserById(int id) {
        return userMapper.selectById(id);
    }
}
