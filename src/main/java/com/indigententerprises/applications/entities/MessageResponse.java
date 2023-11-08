package com.indigententerprises.applications.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(name="message_response", schema="async")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class MessageResponse {

    private Long primaryKey;
    private String messageId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="primary_key")
    public Long getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(Long id) {
        this.primaryKey = id;
    }

    @Column(name="message_id")
    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}
