package com.proteantec.tableauwdc.dto;

import lombok.*;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultSetData {
    private int columnCount;
    private String schemaName;
    private String tableName;
    private String interceptionPoint;
    private List<RowData> rowsData;
    private Map<String, String> schemas;
    private Map<String, String> tableTypes;
    private Map<String, String> tables;
    private Map<String, String> columns;
}
