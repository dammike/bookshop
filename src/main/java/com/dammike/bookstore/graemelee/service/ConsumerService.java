package com.dammike.bookstore.graemelee.service;

import com.dammike.bookstore.graemelee.model.Consumer;
import com.dammike.bookstore.graemelee.repository.ConsumerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ConsumerService {
    @Autowired
    private ConsumerRepository consumerRepository;

    public void createConsumer(Consumer consumer) {
        consumerRepository.save(consumer);
    }

    public void updateConsumer(Consumer consumer) {
        log.debug("Updating consumer: " + consumer.getId());
        consumerRepository.save(consumer);
    }

    public void deleteConsumer(Long id) {
        Consumer consumer = consumerRepository.findById(id).orElseThrow();
        consumerRepository.delete(consumer);
    }

    public Consumer getConsumerById(Long id) {
        return consumerRepository.findById(id).orElseThrow();
    }

    public List<Consumer> getAllConsumers() {
        List<Consumer> consumers = new ArrayList<>();
        consumerRepository.findAll().forEach(consumers::add);
        return consumers;
    }

    public Consumer getConsumerByEmail(String email) {
        Consumer consumer = consumerRepository.findByEmailContaining(email).orElseThrow();
        return consumer;
    }
}
