package com.indigententerprises.applications.serviceimplementations;

import com.indigententerprises.applications.entities.MessageResponse;
import com.indigententerprises.applications.repositories.MessageResponseRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;

@Service
public class MessageResponseService
        implements com.indigententerprises.applications.serviceinterfaces.MessageResponseService {
    private final MessageResponseRepository messageResponseRepository;

    public MessageResponseService(final MessageResponseRepository messageResponseRepository) {
        this.messageResponseRepository = messageResponseRepository;
    }

    public MessageResponse findByMessageId(final String messageId) throws NoSuchElementException {
        final Optional<MessageResponse> messageResponse = messageResponseRepository.findByMessageId(messageId);

        if (messageResponse.isPresent()) {
            return messageResponse.get();
        } else {
            throw new NoSuchElementException();
        }
    }

    public List<MessageResponse> findAll() {
        return messageResponseRepository.findAll();
    }

    public MessageResponse insert(final MessageResponse messageResponse) {
        return messageResponseRepository.save(messageResponse);
    }
}
