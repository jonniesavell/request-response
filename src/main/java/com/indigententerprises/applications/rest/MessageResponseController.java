package com.indigententerprises.applications.rest;

import com.indigententerprises.applications.entities.MessageResponse;
import com.indigententerprises.applications.serviceinterfaces.MessageResponseService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * not all verbs are supported:
 *
 * GET    : o
 * POST   : X
 * PUT    : X
 * DELETE : X
 **/

@RestController
@RequestMapping("/message-responses")
public class MessageResponseController {

    private final MessageResponseService messageResponseService;

    public MessageResponseController(final MessageResponseService messageResponseService) {
        this.messageResponseService = messageResponseService;
    }

    @GetMapping
    public List<MessageResponse> getMessageResponses() {
        return messageResponseService.findAll();
    }

    @GetMapping("/{id}")
    public MessageResponse getMessageResponse(@PathVariable final String id) {

        try {
            final MessageResponse messageResponse = messageResponseService.findByMessageId(id);
            return messageResponse;
        } catch (NoSuchElementException e) {
            throw new ItemNotFoundException();
        }
    }
}
