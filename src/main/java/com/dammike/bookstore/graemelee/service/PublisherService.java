package com.dammike.bookstore.graemelee.service;

import com.dammike.bookstore.graemelee.model.Publisher;
import com.dammike.bookstore.graemelee.repository.PublisherRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PublisherService {
    @Autowired
    private PublisherRepository publisherRepository;

    public void save(Publisher publisher) {
        publisherRepository.save(publisher);
    }

    public void updatePublisher(Publisher publisher) {
        log.debug("Updating publisher: "+ publisher.getId());
        publisherRepository.save(publisher);
    }

    public void delete(Long id) {
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
