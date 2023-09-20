package com.indigententerprises.applications.repositories;

import com.indigententerprises.applications.entities.MessageRequest;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MessageRequestRepository extends JpaRepository<MessageRequest, Long> {
    Optional<MessageRequest> findByMessageId(final String messageId);
}
