package com.nowcoder.community.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.nowcoder.community.pojo.DiscussPost;

import java.util.List;

@Mapper
public interface DiscussPostMapper {

    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit, int orderMode);

    // @Param注解用于给参数取别名
    // 如果参数只有一个且在<if>中使用，必须使用@Param
    int selectDiscussPostRows(@Param("userId") int userId);

    int insertDiscussPost(DiscussPost discussPost);

    // 查询帖子
    DiscussPost selectDiscussPostById(int id);

    // 更新帖子数量
    int updateCommentCount(int id, int commentCount);

    // 更新帖子类型
    int updateType(int id, int type);

    // 更新帖子状态
    int updateStatus(int id, int status);

    // 更新帖子分数
    int updateScore(int id, double score);


}
