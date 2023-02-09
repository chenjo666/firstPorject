package com.nowcoder.community.service;

import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.pojo.DiscussPost;
import com.nowcoder.community.pojo.User;
import com.nowcoder.community.util.CommunityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Date;

@Service
public class AlphaService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private TransactionTemplate transactionTemplate;
    /**
     * @Param isolation: 事务隔离级别
     * @Param propagation: 事务传播级别
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Object useAnnotation() {
        User user = new User();
        user.setUsername("张三");
        user.setPassword("123");
        user.setSalt(CommunityUtil.generateUUID().substring(0, 5));
        user.setEmail("123@qq.com");
        user.setHeaderUrl("http://image.nowcoder.com/head/99t.png");
        user.setCreateTime(new Date());
        userMapper.insertUser(user);

        DiscussPost discussPost = new DiscussPost();
        discussPost.setUserId(user.getId());
        discussPost.setTitle("hallo");
        discussPost.setContent("注解事务咯");
        discussPost.setCreateTime(new Date());
        discussPostService.addDiscussPost(discussPost);

        int n = 10 / 0;

        return "ok";
    }

    public Object useCode() {
        transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        return transactionTemplate.execute(new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus status) {
                User user = new User();
                user.setUsername("李三");
                user.setPassword("123");
                user.setSalt(CommunityUtil.generateUUID().substring(0, 5));
                user.setEmail("123@qq.com");
                user.setHeaderUrl("http://image.nowcoder.com/head/99t.png");
                user.setCreateTime(new Date());
                userMapper.insertUser(user);

                DiscussPost discussPost = new DiscussPost();
                discussPost.setUserId(user.getId());
                discussPost.setTitle("hallo");
                discussPost.setContent("注解事务咯");
                discussPost.setCreateTime(new Date());
                discussPostService.addDiscussPost(discussPost);

                int n = 10 / 0;

                return "ok";
            }
        });
    }

    private static final Logger logger = LoggerFactory.getLogger(AlphaService.class);

    @Async
    public void execute1() {
        logger.debug("execute1");
    }
//    @Scheduled(initialDelay = 10000, fixedRate = 1000)
//    public void execute2() {
//        logger.debug("execute2");
//    }
}
