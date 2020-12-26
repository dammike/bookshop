package com.dammike.bookstore.graemelee.service;

import com.dammike.bookstore.graemelee.model.Publisher;
import com.dammike.bookstore.graemelee.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PublisherService {
    @Autowired
    private PublisherRepository publisherRepository;

    public void addPublisher(Publisher publisher) {
        publisherRepository.save(publisher);
    }

    public void updatePublisher(Publisher publisher) {
        Publisher result = publisherRepository.findById(publisher.getId()).orElseThrow();
        result.setName(publisher.getName());
        result.setDescription(publisher.getDescription());
        publisherRepository.save(result);
    }

    public void deletePublisher(Long id) {
        Publisher result = publisherRepository.findById(id).get();
        publisherRepository.delete(result);
    }

    public Publisher getPublisherById(Long id) {
        return publisherRepository.findById(id).get();
    }

    public List<Publisher> getAllPublishers() {
        List<Publisher> publishers = new ArrayList<>();
        publisherRepository.findAll().forEach(publishers::add);
        return publishers;
    }
}
