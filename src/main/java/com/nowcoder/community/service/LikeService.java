package com.nowcoder.community.service;

import com.nowcoder.community.util.HostHolder;
import com.nowcoder.community.util.RedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Service;

@Service
public class LikeService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private HostHolder hostHolder;
    // 点赞业务
    public void like(int userId, int entityType, int entityId, int entityUserId) {
        redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                String entityLikeKey = RedisKeyUtil.getEntityKey(entityType, entityId);
                String userLikeKey = RedisKeyUtil.getUserLikeKey(entityUserId);
                boolean isMember = operations.opsForSet().isMember(entityLikeKey, userId);
                operations.multi();
                if (isMember) {
                    operations.opsForSet().remove(entityLikeKey, userId);
                    operations.opsForValue().decrement(userLikeKey);
                } else {
                    operations.opsForSet().add(entityLikeKey, userId);
                    operations.opsForValue().increment(userLikeKey);
                }
                return operations.exec();
            }
        });
    }
    // 统计点赞个数
    public long getEntityLikeCount(int entityType, int entityId) {
        String entityKey = RedisKeyUtil.getEntityKey(entityType, entityId);
        return redisTemplate.opsForSet().size(entityKey);
    }

    // 统计某人对点赞的状态
    public int getEntityLikeStatus(int userId, int entityType, int entityId) {
        String entityKey = RedisKeyUtil.getEntityKey(entityType, entityId);
        return redisTemplate.opsForSet().isMember(entityKey, userId) ? 1 : 0;
    }

    // 统计某个用户获得的赞
    public int getUserLikeCount(int userId) {
        String userLikeKey = RedisKeyUtil.getUserLikeKey(userId);
        Integer count = (Integer) redisTemplate.opsForValue().get(userLikeKey);
        return count == null ? 0 : count.intValue();
    }

}
