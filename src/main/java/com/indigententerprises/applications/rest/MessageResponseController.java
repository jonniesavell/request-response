package com.indigententerprises.applications.rest;

import com.indigententerprises.applications.entities.MessageResponse;

import com.indigententerprises.applications.repositories.MessageResponseRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * GET    : o
 * POST   : X
 * PUT    : X
 * DELETE : X
 **/

@RestController
@RequestMapping("/message-responses")
public class MessageResponseController {

    private final MessageResponseRepository messageResponseRepository;

    public MessageResponseController(final MessageResponseRepository messageResponseRepository) {
        this.messageResponseRepository = messageResponseRepository;
    }

    @GetMapping("")
    public List<MessageResponse> getMessageResponses() {
        return messageResponseRepository.findAll();
    }

    @GetMapping("/{id}")
    public MessageResponse getMessageResponse(@PathVariable final String id) {
        final Optional<MessageResponse> messageResponse = messageResponseRepository.findByMessageId(id);

        if (messageResponse.isPresent()) {
            return messageResponse.get();
        } else {
            throw new ItemNotFoundException();
        }
    }
}
