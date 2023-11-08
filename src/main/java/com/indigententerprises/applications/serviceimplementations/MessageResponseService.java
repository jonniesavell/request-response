package com.indigententerprises.applications.serviceimplementations;

import com.indigententerprises.applications.entities.MessageResponse;
import com.indigententerprises.applications.entities.MessageResponseFailure;
import com.indigententerprises.applications.entities.MessageResponseSuccess;
import com.indigententerprises.applications.repositories.MessageResponseFailureRepository;
import com.indigententerprises.applications.repositories.MessageResponseRepository;

import com.indigententerprises.applications.repositories.MessageResponseSuccessRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;

@Service
public class MessageResponseService
        implements com.indigententerprises.applications.serviceinterfaces.MessageResponseService {
    private final MessageResponseRepository messageResponseRepository;
    private final MessageResponseFailureRepository messageResponseFailureRepository;
    private final MessageResponseSuccessRepository messageResponseSuccessRepository;

    public MessageResponseService(
            final MessageResponseRepository messageResponseRepository,
            final MessageResponseFailureRepository messageResponseFailureRepository,
            final MessageResponseSuccessRepository messageResponseSuccessRepository
    ) {
        this.messageResponseRepository = messageResponseRepository;
        this.messageResponseFailureRepository = messageResponseFailureRepository;
        this.messageResponseSuccessRepository = messageResponseSuccessRepository;
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

    public MessageResponseSuccess insert(final MessageResponseSuccess messageResponseSuccess) {
        return messageResponseSuccessRepository.save(messageResponseSuccess);
    }

    public MessageResponseFailure insert(final MessageResponseFailure messageResponseFailure) {
        return messageResponseFailureRepository.save(messageResponseFailure);
    }
}
