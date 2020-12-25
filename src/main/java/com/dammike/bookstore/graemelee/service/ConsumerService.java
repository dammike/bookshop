package com.dammike.bookstore.graemelee.service;

import com.dammike.bookstore.graemelee.model.Consumer;
import com.dammike.bookstore.graemelee.repository.ConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConsumerService {
    @Autowired
    private ConsumerRepository consumerRepository;

    public void createConsumer(Consumer consumer) {
        consumerRepository.save(consumer);
    }

    public void updateConsumer(Consumer consumer) {
        Consumer result = consumerRepository.findById(consumer.getId()).orElseThrow();
        result.setFirstName(consumer.getFirstName());
        result.setLastName(consumer.getLastName());
        result.setClubMember(consumer.isClubMember());
        //todo: more to follow
        consumerRepository.save(result);
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
