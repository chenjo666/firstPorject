package com.nowcoder.community;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.pojo.DiscussPost;
import com.nowcoder.community.pojo.User;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class MapperTests {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Test
    public void testSelectUser() {
        User user = userMapper.selectById(101);
        System.out.println(user);

        User user2 = userMapper.selectByName("liubei");
        System.out.println(user2);

        User user3 = userMapper.selectByEmail("nowcoder101@sina.com");
        System.out.println(user3);
    }

    @Test
    public void testInsertUser() {
        User user = new User();
        user.setUsername("nowCoder");
        user.setPassword("123456");
        user.setSalt("hello");
        user.setEmail("nowcoder@qq.com");
        user.setHeaderUrl("http://www.nowcoder.com/101.png");
        user.setCreateTime(new Date());
        int rows = userMapper.insertUser(user);
        System.out.println(rows);
    }

    @Test
    public void testUpdateUser() {
        int rows = userMapper.updatePasswordById(150, "123");
        System.out.println(rows);

        int rows2 = userMapper.updateStatusById(150, 2);
        System.out.println(rows2);

        int rows3 = userMapper.updateHeaderUrlById(150, "http://www.nowcoder.com/102.png");
        System.out.println(rows3);
    }

    @Test
    public void testSelectDiscussPost() {
        // 查询全部记录的10条数据
        List<DiscussPost> list = discussPostMapper.selectDiscussPosts(0, 0, 10);
        for (DiscussPost post : list) {
            System.out.println(post);
        }
        // 查询用户id为133的数据
        List<DiscussPost> list2 = discussPostMapper.selectDiscussPosts(0, 0, 10);
        for (DiscussPost post : list2) {
            System.out.println(post);
        }
        // 查询总数据条数
        int row = discussPostMapper.selectDiscussPostRows(0);
        System.out.println(row);
        // 查询用户id为133的数据条数
        int row2 = discussPostMapper.selectDiscussPostRows(133);
        System.out.println(row2);
    }
}
