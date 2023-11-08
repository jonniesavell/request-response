package com.indigententerprises.applications.repositories;

import com.indigententerprises.applications.entities.MessageRequest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MessageRequestRepository extends JpaRepository<MessageRequest, Long> {
    Optional<MessageRequest> findByMessageId(final String messageId);

    @Query("select mreq from MessageRequest mreq where not exists (select 1 from MessageResponse mres where mres.messageId = mreq.messageId)")
    List<MessageRequest> findUnresolvedMessageRequests();
}
