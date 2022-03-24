package com.proteantec.tableauwdc.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
