package com.nowcoder.community.dao;

import org.springframework.stereotype.Repository;

@Repository
public class AlphaMyBatisImpl implements AlphaDao {

    @Override
    public String getUsers() {
        return "hello, MyBatis";
    }
}
