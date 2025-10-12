package com.flic.courseRegister.config;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.boot.jdbc.DataSourceBuilder;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MultiDbManager {

    private final Map<String, JdbcTemplate> jdbcTemplates = new HashMap<>();

    // Constructor khởi tạo các DB
    public MultiDbManager() {
        // DB1 thiên fake
        DataSource ds1 = DataSourceBuilder.create()
                .url("jdbc:mysql://trolley.proxy.rlwy.net:16442/railway?useSSL=false&serverTimezone=UTC")
                .username("root")
                .password("eKFRFTCgDfhvuEjjKaJcvdtJinnGwsku")
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
        jdbcTemplates.put("db1", new JdbcTemplate(ds1));

        // DB2 tuấn
        DataSource ds2 = DataSourceBuilder.create()
                .url("jdbc:mysql://shortline.proxy.rlwy.net:44969/railway?useSSL=false&serverTimezone=UTC")
                .username("root")
                .password("lGzRacjpiSAiupVIEvtDkonPLyghJNsS")
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
        jdbcTemplates.put("db2", new JdbcTemplate(ds2));

        // DB3 quỳnh
        DataSource ds3 = DataSourceBuilder.create()
                .url("jdbc:mysql://shinkansen.proxy.rlwy.net:33999/railway?useSSL=false&serverTimezone=UTC")
                .username("root")
                .password("JtRSMdjRmdLCPeJcHjXKSrUlNbEmLmpM")
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
        jdbcTemplates.put("db3", new JdbcTemplate(ds3));

        // DB4 kha
        DataSource ds4 = DataSourceBuilder.create()
                .url("jdbc:mysql://tramway.proxy.rlwy.net:20480/railway?useSSL=false&serverTimezone=UTC")
                .username("root")
                .password("fNhwWloELchWlJJMaCJzfhqOOdanuzDu")
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
        jdbcTemplates.put("db4", new JdbcTemplate(ds4));
        // DB4 thu
        DataSource ds5 = DataSourceBuilder.create()
                .url("jdbc:mysql://tramway.proxy.rlwy.net:56200/railway?useSSL=false&serverTimezone=UTC")
                .username("root")
                .password("hWiSRYtqmzXGWbGJGTQUyusvyYMziTLf")
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
        jdbcTemplates.put("db5", new JdbcTemplate(ds5));
    }

    public JdbcTemplate getJdbcTemplate(String dbName) {
        JdbcTemplate jt = jdbcTemplates.get(dbName);
        if (jt == null) throw new RuntimeException("No JdbcTemplate found for DB: " + dbName);
        return jt;
    }
    public Set<String> getAllDbNames() {
        return jdbcTemplates.keySet();
    }
}

