package com.flic.courseRegister.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class MultiDbManager {

    private final Map<String, JdbcTemplate> jdbcTemplates = new HashMap<>();

    @Value("${MULTI_DB_CONFIG:}")
    private String multiDbConfig;

    @PostConstruct
    public void init() {
        if (multiDbConfig == null || multiDbConfig.isBlank()) {
            return; // không có DB phụ thì thôi
        }

        // mỗi DB cách nhau bằng ;
        String[] dbEntries = multiDbConfig.split(";");

        for (String entry : dbEntries) {
            String[] parts = entry.split("\\|");

            if (parts.length != 4) {
                throw new RuntimeException("Invalid MULTI_DB_CONFIG entry: " + entry);
            }

            String dbName = parts[0];
            String url = parts[1];
            String username = parts[2];
            String password = parts[3];

            DataSource ds = DataSourceBuilder.create()
                    .url(url)
                    .username(username)
                    .password(password)
                    .driverClassName("com.mysql.cj.jdbc.Driver")
                    .build();

            jdbcTemplates.put(dbName, new JdbcTemplate(ds));
        }
    }

    public JdbcTemplate getJdbcTemplate(String dbName) {
        JdbcTemplate jt = jdbcTemplates.get(dbName);
        if (jt == null)
            throw new RuntimeException("No JdbcTemplate found for DB: " + dbName);
        return jt;
    }

    public Set<String> getAllDbNames() {
        return jdbcTemplates.keySet();
    }
}
