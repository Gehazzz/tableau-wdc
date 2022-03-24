package com.proteantec.tableauwdc.service;

import com.proteantec.tableauwdc.dto.ColumnData;
import com.proteantec.tableauwdc.dto.ResultSetData;
import com.proteantec.tableauwdc.model.Column;

import java.util.List;

public interface ColumnsService {
    List<Column> extractColumns(ResultSetData resultSetData);
    String db();
}
