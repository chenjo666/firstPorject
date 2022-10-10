package com.nowcoder.community.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.nowcoder.community.pojo.DiscussPost;

import java.util.List;

@Mapper
public interface DiscussPostMapper {

    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit);

    // @Param注解用于给参数取别名
    // 如果参数只有一个且在<if>中使用，必须使用@Param
    int selectDiscussPostRows(@Param("userId") int userId);
}
