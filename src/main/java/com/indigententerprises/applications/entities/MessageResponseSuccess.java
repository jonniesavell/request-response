package com.indigententerprises.applications.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name="message_response_success", schema="async")
@PrimaryKeyJoinColumn(name="primary_key")
public class MessageResponseSuccess extends MessageResponse {

    private String messageHash;

    @Column(name="message_hash")
    public String getMessageHash() {
        return messageHash;
    }

    public void setMessageHash(final String messageHash) {
        this.messageHash = messageHash;
    }
}
