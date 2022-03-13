package com.proteantec.tableauwdc.config;

import com.vertica.jdbc.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
@Configuration
public class Config {

    //https://github.com/verticamon/vertica-integration-test/blob/master/src/main/java/com/vertica/test/integration/Database.java
    @Bean
    public javax.sql.DataSource buildDataSource(){
        DataSource dataSource = new DataSource();
        //dataSource.setDatabase("dbadmin");
        dataSource.setURL("jdbc:vertica://localhost:5433/docker");
        dataSource.setUserID("dbadmin");
        return dataSource;
    }
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}
