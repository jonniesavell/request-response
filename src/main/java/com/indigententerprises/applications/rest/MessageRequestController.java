package com.indigententerprises.applications.rest;

import com.indigententerprises.applications.entities.MessageRequest;
import com.indigententerprises.applications.repositories.MessageRequestRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * GET    : o
 * POST   : X
 * PUT    : o (upsert, specifically creation)
 * DELETE : X
 */
@RestController
@RequestMapping("/message-requests")
public class MessageRequestController {

    private final MessageRequestRepository messageRequestRepository;

    public MessageRequestController(final MessageRequestRepository messageRequestRepository) {
        this.messageRequestRepository = messageRequestRepository;
    }

    @GetMapping("")
    public List<MessageRequest> getMessageRequests() {
        final List<MessageRequest> messages = messageRequestRepository.findAll();
        final ArrayList<MessageRequest> messageRequests = new ArrayList<>(messages.size());
        return messageRequests;
    }

    @GetMapping("/{id}")
    public MessageRequest getMessageRequest(@PathVariable final String id) {
        final Optional<MessageRequest> messageRequest = messageRequestRepository.findByMessageId(id);

        if (messageRequest.isPresent()) {
            return messageRequest.get();
        } else {
            throw new ItemNotFoundException();
        }
    }

    @PutMapping("")
    public MessageRequest upsertMessageRequest(@RequestBody MessageRequest messageRequest) {
        return  messageRequestRepository.save(messageRequest);
    }
}
