package com.nowcoder.community;

import com.nowcoder.community.dao.LoginTicketMapper;
import com.nowcoder.community.dao.MessageMapper;
import com.nowcoder.community.pojo.LoginTicket;
import com.nowcoder.community.pojo.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.pojo.DiscussPost;
import com.nowcoder.community.pojo.User;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = NowcoderApplication.class)
public class MapperTests {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Autowired
    private LoginTicketMapper loginTicketMapper;
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
        List<DiscussPost> list = discussPostMapper.selectDiscussPosts(0, 0, 10, 0);
        for (DiscussPost post : list) {
            System.out.println(post);
        }
        // 查询用户id为133的数据
        List<DiscussPost> list2 = discussPostMapper.selectDiscussPosts(0, 0, 10, 0);
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
    // 测试插入功能
    @Test
    public void testInsertLoginTicket() {
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setUserId(101);
        loginTicket.setTicket("abc");
        loginTicket.setStatus(0);
        loginTicket.setExpired(new Date(System.currentTimeMillis() + 1000 * 60 * 10));

        loginTicketMapper.insertLoginTicket(loginTicket);
    }

    // 测试查询和删除功能
    @Test
    public void testUpdateAndUpdateLoginTicket() {
        LoginTicket loginTicket = loginTicketMapper.selectByTicket("abc");
        System.out.println(loginTicket);

        loginTicketMapper.updateStatus("abc", 1);
        loginTicket = loginTicketMapper.selectByTicket("abc");
        System.out.println(loginTicket);
    }

    @Autowired
    private MessageMapper messageMapper;
    @Test
    public void testMessageMapper() {
        // test1
        List<Message> messageList = messageMapper.selectConversations(111, 0, 10);
        for (Message message : messageList) {
            System.out.println(message);
        }
        // test2
        int count = messageMapper.selectConversationCount(111);
        System.out.println(count);
        // test3
        List<Message> letters = messageMapper.selectLetters("111_112", 0, 10);
        for (Message message : letters) {
            System.out.println(message);
        }
        // test4
        int count2 = messageMapper.selectLetterCount("111_112");
        System.out.println(count2);
        // test5
        int count3 = messageMapper.selectLetterUnreadCount(131, "111_131");
        System.out.println(count3);
    }


}
