package com.proteantec.tableauwdc.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ColumnData {
    private int index;
    private boolean isAutoIncrement;
    private String columnLabel;
    private String columnName;
    private int columnType;
    private String columnTypeName;
    private String catalogName;
    private String columnClassName;
    private int columnDisplaySize;
    private int precision;
    private int scale;
    private String value;
}
