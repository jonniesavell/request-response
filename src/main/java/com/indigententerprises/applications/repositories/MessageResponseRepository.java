package com.indigententerprises.applications.repositories;

import com.indigententerprises.applications.entities.MessageResponse;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MessageResponseRepository<T extends MessageResponse, ID> extends JpaRepository<T, ID> {
    Optional<T> findByMessageId(final String messageId);
}
