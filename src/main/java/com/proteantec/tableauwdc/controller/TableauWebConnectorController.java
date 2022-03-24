package com.proteantec.tableauwdc.controller;

import com.proteantec.tableauwdc.dto.*;
import com.proteantec.tableauwdc.model.Column;
import com.proteantec.tableauwdc.service.ColumnsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/v1")
public class TableauWebConnectorController {

    private final JdbcTemplate jdbcTemplate;

    private final Map<String, ColumnsService> columnsServices;

    @Autowired
    public TableauWebConnectorController(JdbcTemplate jdbcTemplate, Set<ColumnsService> columnsServices) {
        this.jdbcTemplate = jdbcTemplate;
        this.columnsServices = columnsServices.stream().collect(Collectors.toMap(ColumnsService::db, Function.identity()));
    }

    @PostMapping(value = "/tables/{db}")
    public TablesToHide tables(@RequestHeader Map<String, String> headers, @RequestBody ResultSetData data, @RequestParam("action") Action action) {
        //if(action.equals(Action.HIDE))
        log.info("{}", action);
        log.info("{}", data);
        return TablesToHide.builder().hiddenTables(Arrays.asList("failedpn")).build();
    }

    @PostMapping(value = "/columns/{db}")
    public ColumnsToHide columns(@RequestHeader Map<String, String> headers, @RequestBody ResultSetData data, @PathVariable("db") String db, @RequestParam("action") Action action) {
        //TODO: Check if the metaData.getColumns returns the same resultSet structure for all databases
        List<Column> columns = columnsServices.get(db).extractColumns(data);
        //if(action.equals(Action.HIDE))
        log.info("{}", columns);
        log.info("{}", action);
        log.info("{}", data);
        return ColumnsToHide.builder().hiddenColumns(Arrays.asList("device_token")).build();
    }

    @PostMapping("/log")
    public String log(@RequestBody LogMessage logMessage) {
        //log.info(headers.toString());
        log.info("Interception point: {}, Message: {}", logMessage.getExecutionPoint(), logMessage.getMessage());
        return log.toString();
    }

    @PostMapping("/analysis")
    public String analysis(@RequestHeader Map<String, String> headers, @RequestBody String data) {
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
