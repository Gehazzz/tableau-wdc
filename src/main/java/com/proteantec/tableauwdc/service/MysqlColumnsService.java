package com.proteantec.tableauwdc.service;

import com.proteantec.tableauwdc.dto.ColumnData;
import com.proteantec.tableauwdc.dto.ResultSetData;
import com.proteantec.tableauwdc.model.Column;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MysqlColumnsService implements ColumnsService {
    @Override
    public List<Column> extractColumns(ResultSetData resultSetData) {
        return resultSetData.getRowsData().stream().map(rowData -> {
            Map<String, ColumnData> columns = rowData.getColumns();
            String tableName = columns.get("TABLE_NAME").getValue();
            String tableCategory = columns.get("TABLE_CAT").getValue();
            int index = Integer.parseInt(columns.get("ORDINAL_POSITION").getValue());
            boolean isAutoIncrement = Optional.ofNullable(columns.get("IS_AUTOINCREMENT").getValue())
                    .map(value -> value.equalsIgnoreCase("yes")).orElse(false);
            String columnName = columns.get("COLUMN_NAME").getValue();
            int sqlDataType = Integer.parseInt(columns.get("SQL_DATA_TYPE").getValue());
            String typeName = columns.get("TYPE_NAME").getValue();
            String scopeTable = columns.get("SCOPE_TABLE").getValue();
            String scopeCatalog = columns.get("SCOPE_CATALOG").getValue();
            boolean isNullable = columns.get("IS_NULLABLE").getValue().equalsIgnoreCase("yes");
            int bufferLength = Integer.parseInt(columns.get("BUFFER_LENGTH").getValue());
            String columnDef = columns.get("COLUMN_DEF").getValue();
            String tableSchem = columns.get("TABLE_SCHEM").getValue();
            String remarks = columns.get("REMARKS").getValue();
            String decimalDigits = columns.get("DECIMAL_DIGITS").getValue();
            String sqlDatetimeSub = columns.get("SQL_DATETIME_SUB").getValue();
            String numPrecRadix = columns.get("NUM_PREC_RADIX").getValue();
            boolean isGeneratedColumn = Optional.ofNullable(columns.get("IS_GENERATEDCOLUMN").getValue())
                    .map(value -> value.equalsIgnoreCase("yes")).orElse(false);
            String charOctetLength = columns.get("CHAR_OCTET_LENGTH").getValue();
            String sourceDataType = columns.get("SOURCE_DATA_TYPE").getValue();
            String scopeSchema = columns.get("SCOPE_SCHEMA").getValue();
            String dataType = columns.get("DATA_TYPE").getValue();
            String columnSize = columns.get("COLUMN_SIZE").getValue();

            Map<String, Object> additionalMetrics = new HashMap<String, Object>() {{
                put("sqlDataType", sqlDataType);
                put("scopeTable", scopeTable);
                put("scopeCatalog", scopeCatalog);
                put("bufferLength", bufferLength);
                put("columnDef", columnDef);
                put("tableSchem", tableSchem);
                put("remarks", remarks);
                put("decimalDigits", decimalDigits);
                put("sqlDatetimeSub", sqlDatetimeSub);
                put("numPrecRadix", numPrecRadix);
                put("charOctetLength", charOctetLength);
                put("sourceDataType", sourceDataType);
                put("scopeSchema", scopeSchema);
                put("dataType", dataType);
                put("columnSize", columnSize);
            }};

            return Column.builder()
                    .db(this.db())
                    .schema(tableCategory)
                    .table(tableName)
                    .index(index)
                    .columnName(columnName)
                    .columnType(typeName)
                    .isAutoIncrement(isAutoIncrement)
                    .isNullable(isNullable)
                    .isGeneratedColumn(isGeneratedColumn)
                    .additionalMetrics(additionalMetrics)
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public String db() {
        return "mysql";
    }
}
