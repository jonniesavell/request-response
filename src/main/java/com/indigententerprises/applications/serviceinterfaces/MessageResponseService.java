package com.indigententerprises.applications.serviceinterfaces;

import com.indigententerprises.applications.entities.MessageResponse;
import com.indigententerprises.applications.entities.MessageResponseFailure;
import com.indigententerprises.applications.entities.MessageResponseSuccess;

import java.util.List;
import java.util.NoSuchElementException;

public interface MessageResponseService {
    MessageResponse findByMessageId(final String messageId) throws NoSuchElementException;

    List<MessageResponse> findAll();

    MessageResponseFailure insert(final MessageResponseFailure messageResponseFailure);

    MessageResponseSuccess insert(final MessageResponseSuccess messageResponseSuccess);
}
