package com.proteantec.tableauwdc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

@Slf4j
@RestController
public class TableauWebConnectorController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/analysis")
    public String analysis(@RequestHeader Map<String, String> headers, @RequestBody String data){
        //log.info(headers.toString());
        log.info(data);
        return data;
    }
/*
* getCatalogs
* getSchemas
* getTableTypes
* getTables
* https://docs.microsoft.com/en-us/sql/connect/jdbc/modifying-result-set-data-sample?view=sql-server-ver15
* */
    @GetMapping("/v1/query")
    public ResultSet query() throws SQLException {
       /* jdbcTemplate.queryForObject(
                "SELECT * FROM bi_pipeline.failedpn", Integer.class);*/
        jdbcTemplate.getDataSource().getConnection().createStatement().execute("CREATE schema bi_pipeline;\n" +
                "\n" +
                "CREATE TABLE bi_pipeline.failedpn\n" +
                "(\n" +
                "    metric_date int NOT NULL,\n" +
                "    metric_hour int,\n" +
                "    game_id int,\n" +
                "    game_id_str varchar(32),\n" +
                "    user_id int,\n" +
                "    event_ts timestamp,\n" +
                "    bundle_id varchar(32),\n" +
                "    mkt int,\n" +
                "    mkt_str varchar(100),\n" +
                "    device_token varchar(1000)\n" +
                ")\n" +
                "PARTITION BY (failedpn.metric_date);\n" +
                "\n" +
                "INSERT INTO bi_pipeline.failedpn (metric_date, metric_hour, game_id, game_id_str, user_id, event_ts, bundle_id, mkt, mkt_str, device_token) VALUES (20171103, 2017110301, 23, 'niso', 1, '2017-11-03 00:01:01', 'bundle-1', 1, 'apple', 'abcdef');");

        ResultSet result = jdbcTemplate.getDataSource().getConnection().createStatement().executeQuery(
                "SELECT * FROM bi_pipeline.failedpn;");
        return result;
    }

}
