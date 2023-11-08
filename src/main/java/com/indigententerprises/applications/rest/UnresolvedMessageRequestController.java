package com.indigententerprises.applications.rest;

import com.indigententerprises.applications.entities.MessageRequest;
import com.indigententerprises.applications.serviceinterfaces.MessageRequestService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * not all verbs are supported:
 *
 * GET    : o
 * POST   : X
 * PUT    : X
 * DELETE : X
 */
@RestController
@RequestMapping("/unresolved-message-requests")
public class UnresolvedMessageRequestController {

    private final MessageRequestService messageRequestService;

    public UnresolvedMessageRequestController(final MessageRequestService messageRequestService) {
        this.messageRequestService = messageRequestService;
    }

    @GetMapping
    public List<MessageRequest> getUnresolvedMessageRequests() {

        final List<MessageRequest> messages = messageRequestService.findUnresolvedMessageRequests();
        return messages;
    }
}
