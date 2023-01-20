package com.jbv.lebenslauftool.model.helper;

import jakarta.persistence.AttributeConverter;

import java.time.LocalDate;
import java.time.YearMonth;

public class YearMonthConverter implements AttributeConverter<YearMonth, LocalDate> {

    @Override
    public LocalDate convertToDatabaseColumn(YearMonth yearMonth) {
        if (yearMonth != null) {
            return LocalDate.of(yearMonth.getYear(), yearMonth.getMonth(), 1);
        }
        return null;
    }

    @Override
    public YearMonth convertToEntityAttribute(LocalDate date) {
        if (date != null) {
            return YearMonth.from(date);
        }
        return null;
    }
}