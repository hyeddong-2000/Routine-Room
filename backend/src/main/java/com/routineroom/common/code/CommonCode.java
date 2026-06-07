package com.routineroom.common.code;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CommonCode {

    private static final String PREFIX = "code:";
    private static final String SELECT_SQL =
            "SELECT code_cd, code_nm FROM conf_code_set WHERE use_yn = 'Y'";

    private final DataSource dataSource;
    private final RedisIO redisIO;

    public CommonCode(DataSource dataSource, RedisIO redisIO) {
        this.dataSource = dataSource;
        this.redisIO = redisIO;
        loadCommonCodeToRedis();
    }

    private void loadCommonCodeToRedis() {
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_SQL)) {

            while (rs.next()) {
                String codeCd = rs.getString("code_cd");
                String codeNm = rs.getString("code_nm");
                redisIO.setValue(PREFIX + codeCd, codeNm);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public String getCodeName(String codeCd) {
        return redisIO.getValue(PREFIX + codeCd);
    }
}
