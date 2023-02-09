package com.nowcoder.community.dao.elasticsearch;

import com.nowcoder.community.pojo.DiscussPost;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * 泛型参数
 * 参数一：实体类型
 * 参数二：主键类型
 */
@Repository
public interface DiscussPostRepository extends ElasticsearchRepository<DiscussPost, Integer> {
}
