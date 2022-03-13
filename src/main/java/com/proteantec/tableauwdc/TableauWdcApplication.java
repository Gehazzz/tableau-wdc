package com.proteantec.tableauwdc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jdbc.JdbcRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {JdbcRepositoriesAutoConfiguration.class})
public class TableauWdcApplication {

    public static void main(String[] args) {
        SpringApplication.run(TableauWdcApplication.class, args);
    }

}
