package com.nowcoder.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.pojo.DiscussPost;

import java.util.List;

@Service
public class DiscussPostService {

    @Autowired
    private DiscussPostMapper discussPostMapper;
    // 获取帖子
    public List<DiscussPost> getDiscussPost(int userId, int offset, int limit) {
        return discussPostMapper.selectDiscussPosts(userId, offset, limit);
    }
    // 获取帖子总数
    public int getRows(int userId) {
        return discussPostMapper.selectDiscussPostRows(userId);
    }
}
