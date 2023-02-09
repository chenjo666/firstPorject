package com.nowcoder.community.util;

public class RedisKeyUtil {

    private static final String SPLIT = ":";
    private static final String PREFIX_ENTITY_LIKE = "like:entity";


    public static String getEntityKey(int entityType, int entityId) {
        return PREFIX_ENTITY_LIKE + SPLIT + entityType + SPLIT + entityId;
    }

    private static final String PREFIX_USER_LIKE = "like:user";
    public static String getUserLikeKey(int userId) {
        return PREFIX_USER_LIKE + SPLIT + userId;
    }

    private static final String PREFIX_FOLLOWEE = "followee";
    private static final String PREFIX_FOLLOWER = "follower";
    public static String getFolloweeKey(int userId, int entityType) {
        return PREFIX_FOLLOWEE + SPLIT + userId + SPLIT + entityType;
    }
    public static String getFollowerKey(int entityType, int entityId) {
        return PREFIX_FOLLOWER + SPLIT + entityType + SPLIT + entityId;
    }

    private static final String PREFIX_KAPTCHA = "kaptcha";
    public static String getKaptchaKey(String owner) {
        return PREFIX_KAPTCHA + SPLIT + owner;
    }

    private static final String PREFIX_TICKET = "ticket";
    public static String getTicketKey(String ticket) {
        return PREFIX_TICKET + SPLIT + ticket;
    }

    private static final String PREFIX_USER = "user";
    public  static String getUserKey(int userId) {
        return PREFIX_USER + SPLIT + userId;
    }

    // 单日 UV(Unique Visitor)
    public static String getUVKey(String date) {
        return PREFIX_USER + SPLIT + date;
    }

    // 区间 UV(Unique Visitor)
    public static String getUVKey(String startDate, String endDate) {
        return PREFIX_USER + SPLIT + startDate + SPLIT + endDate;
    }

    // 单日 DAU(Daily Active User)
    public static String getDAUKey(String date) {
        return PREFIX_USER + SPLIT + date;
    }

    // 区间 DAU(Daily Active User)
    public static String getDAUKey(String startDate, String endDate) {
        return PREFIX_USER + SPLIT + startDate + SPLIT + endDate;
    }

    private static final String PREFIX_POST = "post";
    // 帖子分数
    public static String getPostScoreKey() {
        return PREFIX_POST + SPLIT + "score";
    }
}
