package com.weixin.platform.dao.postgresql;

import org.logicalcobwebs.proxool.ProxoolDataSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * Created by Administrator on 2015/4/20.
 */
public class DbOperator {

    private static ProxoolDataSource dataSource;
    private static final String driver = "org.postgresql.Driver";
    private static final String url = "jdbc:postgresql://115.28.43.59:5432/job";
    private static final String user = "job";
    private static final String passwd = "job";

    private static ProxoolDataSource dataSource() {
        if(dataSource == null) {
            dataSource = new ProxoolDataSource();
            dataSource.setDriver(driver);
            dataSource.setDriverUrl(url);
            dataSource.setUser(user);
            dataSource.setPassword(passwd);
            dataSource.setHouseKeepingTestSql("select 1");
        }
        return dataSource;
    }

    public static NamedParameterJdbcTemplate getReadJdbc() {
        return new NamedParameterJdbcTemplate(dataSource());
    }

    public static NamedParameterJdbcTemplate getWriteJdbc() {
        return new NamedParameterJdbcTemplate(dataSource());
    }

}
