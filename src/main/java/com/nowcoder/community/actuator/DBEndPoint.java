package com.nowcoder.community.actuator;

import com.nowcoder.community.util.CommunityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
@Endpoint(id = "database")
public class DBEndPoint {
    private static Logger logger = LoggerFactory.getLogger(DBEndPoint.class);

    @Autowired
    private DataSource dataSource;

    @ReadOperation
    public String testConnection() {
        try (
                Connection connection = dataSource.getConnection();
                ) {
            return CommunityUtil.getJSONString( 0, "连接成功!");
        } catch (SQLException e) {
            logger.error("连接失败！");
            return CommunityUtil.getJSONString(1, "获取连接失败！");
        }
    }
}
