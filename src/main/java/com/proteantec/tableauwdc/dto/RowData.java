package com.proteantec.tableauwdc.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class RowData {
    private Map<String, ColumnData> columns;
}
