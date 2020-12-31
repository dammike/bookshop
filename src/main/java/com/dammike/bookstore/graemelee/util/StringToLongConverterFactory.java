package com.dammike.bookstore.graemelee.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;


@Configuration
class StringToLongConverterFactory implements ConverterFactory<String, Long> {

    public <T extends Long> Converter<String, T> getConverter(Class<T> targetType) {
        return new StringToLongConverter(targetType);
    }

    private final class StringToLongConverter<T extends Long> implements Converter<String, T> {

        private Class<T> longType;

        public StringToLongConverter(Class<T> longType) {
            this.longType = longType;
        }

        public T convert(String source) {
            return (T) Long.valueOf(source.trim());
        }
    }
}
