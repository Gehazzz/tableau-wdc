package com.proteantec.tableauwdc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Column {
    private String db;
    private String schema;
    private String table;
    private int index;
    private String columnName;
    private String columnType;
    private boolean isNullable;
    private boolean isAutoIncrement;
    private boolean isGeneratedColumn;
    private Map<String, Object> additionalMetrics;
}
