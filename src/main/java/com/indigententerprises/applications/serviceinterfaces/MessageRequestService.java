package com.indigententerprises.applications.serviceinterfaces;

import com.indigententerprises.applications.entities.MessageRequest;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * findByMessageId and findAll
 * insert
 */
public interface MessageRequestService {

    MessageRequest findByMessageId(final String messageId) throws NoSuchElementException;
    List<MessageRequest> findAll();
    MessageRequest insert(final MessageRequest messageRequest);
}
