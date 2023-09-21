package com.indigententerprises.applications.rest;

import com.indigententerprises.applications.entities.MessageRequest;
import com.indigententerprises.applications.serviceinterfaces.DuplicateIdentifierException;
import com.indigententerprises.applications.serviceinterfaces.MessageRequestService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * not all verbs are supported:
 *
 * GET    : o
 * POST   : X
 * PUT    : o (insert)
 * DELETE : X
 */
@RestController
@RequestMapping("/message-requests")
public class MessageRequestController {

    private final MessageRequestService messageRequestService;

    public MessageRequestController(final MessageRequestService messageRequestService) {
        this.messageRequestService = messageRequestService;
    }

    @GetMapping
    public List<MessageRequest> getMessageRequests() {

        final List<MessageRequest> messages = messageRequestService.findAll();
        return messages;
    }

    @GetMapping("/{id}")
    public MessageRequest getMessageRequest(@PathVariable final String id) {

        try {
            final MessageRequest messageRequest = messageRequestService.findByMessageId(id);
            return messageRequest;
        } catch (NoSuchElementException e) {
            throw new ItemNotFoundException();
        }
    }

    @PutMapping
    public MessageRequest insertMessageRequest(@RequestBody MessageRequest messageRequest) {

        try {
            return messageRequestService.insert(messageRequest);
        } catch (DuplicateIdentifierException e) {
            throw new ItemAlreadyPresentException();
        }
    }
}
