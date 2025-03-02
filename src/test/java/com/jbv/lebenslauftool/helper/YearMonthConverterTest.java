package com.jbv.lebenslauftool.helper;

import com.jbv.lebenslauftool.model.helper.YearMonthConverter;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.YearMonth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class YearMonthConverterTest {

    private final YearMonthConverter yearMonthConverter = new YearMonthConverter();

    @Test
    void convertToDatabaseColumn_test() {
        YearMonth yearMonth = YearMonth.of(2022, 10);
        var result = yearMonthConverter.convertToDatabaseColumn(yearMonth);
        assertEquals(LocalDate.of(2022, 10, 1), result);
    }

    @Test
    void convertToDatabaseColumnNullValue_test() {
        var result = yearMonthConverter.convertToDatabaseColumn(null);
        assertNull(result);
    }

    @Test
    void convertToEntityAttribute_test() {
        LocalDate localDate = LocalDate.of(2022, 10, 1);
        var result = yearMonthConverter.convertToEntityAttribute(localDate);
        assertEquals(YearMonth.of(2022, 10), result);
    }

    @Test
    void convertToEntityAttributeWithNullValue_test() {
        var result = yearMonthConverter.convertToEntityAttribute(null);
        assertNull(result);
    }
}
