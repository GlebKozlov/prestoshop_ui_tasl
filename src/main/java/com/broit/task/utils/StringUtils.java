package com.broit.task.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StringUtils {

    public Integer getNumbers(String data) {
        return Integer.valueOf(data.replaceAll("\\D+", ""));
    }
}
