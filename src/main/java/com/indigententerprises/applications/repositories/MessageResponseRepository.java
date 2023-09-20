package com.indigententerprises.applications.repositories;

import com.indigententerprises.applications.entities.MessageResponse;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MessageResponseRepository extends JpaRepository<MessageResponse, Long> {
    Optional<MessageResponse> findByMessageId(final String messageId);
}
