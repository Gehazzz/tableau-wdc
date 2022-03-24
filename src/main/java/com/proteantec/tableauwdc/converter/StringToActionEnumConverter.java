package com.proteantec.tableauwdc.converter;

import com.proteantec.tableauwdc.dto.Action;
import org.springframework.core.convert.converter.Converter;

public class StringToActionEnumConverter implements Converter<String, Action> {
    @Override
    public Action convert(String source) {
        return Action.valueOf(source.toUpperCase());
    }
}
