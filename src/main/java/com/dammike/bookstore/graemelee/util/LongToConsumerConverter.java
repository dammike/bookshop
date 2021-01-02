package com.dammike.bookstore.graemelee.util;

import com.dammike.bookstore.graemelee.model.Consumer;
import com.dammike.bookstore.graemelee.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

@Configuration
public class LongToConsumerConverter<T extends Consumer>
        implements Converter<Long, T> {

    @Autowired
    ConsumerService consumerService;

    public T convert(Long source) {
        return (T) consumerService.getConsumerById(source);
    }

}