package com.indigententerprises.applications.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="message_request", schema="async")
public class MessageRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long primaryKey;
    private String messageContent;
    private String messageId;

    @Column(name="primary_key")
    public Long getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(Long id) {
        this.primaryKey = id;
    }

    @Column(name="message_content")
    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    @Column(name="message_id")
    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}
