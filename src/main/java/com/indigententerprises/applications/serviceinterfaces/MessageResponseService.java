package com.indigententerprises.applications.serviceinterfaces;

import com.indigententerprises.applications.entities.MessageResponse;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * findByMessageId and findAll
 * insert
 */
public interface MessageResponseService {
    MessageResponse findByMessageId(final String messageId) throws NoSuchElementException;

    List<MessageResponse> findAll();

    MessageResponse insert(final MessageResponse messageResponse);
}
