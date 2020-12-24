package com.dammike.bookstore.graemelee.util;

import com.dammike.bookstore.graemelee.model.BookCondition;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class BookConditionConverter implements AttributeConverter<BookCondition, String> {
    @Override
    public String convertToDatabaseColumn(BookCondition bookCondition) {
        if (bookCondition == null) {
            return null;
        }
        return bookCondition.getStatus();
    }

    @Override
    public BookCondition convertToEntityAttribute(String status) {
        if (status == null) {
            return null;
        }

        return Stream.of(BookCondition.values())
                .filter(bookCondition -> bookCondition.getStatus().equals(status))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
