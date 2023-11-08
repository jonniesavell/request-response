package com.indigententerprises.applications.serviceimplementations;

import com.indigententerprises.applications.entities.MessageRequest;
import com.indigententerprises.applications.entities.MessageResponseFailure;
import com.indigententerprises.applications.entities.MessageResponseSuccess;
import com.indigententerprises.applications.repositories.MessageRequestRepository;
import com.indigententerprises.applications.repositories.MessageResponseFailureRepository;
import com.indigententerprises.applications.repositories.MessageResponseSuccessRepository;
import com.indigententerprises.applications.serviceinterfaces.DuplicateIdentifierException;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;

@Service
public class MessageRequestService implements
        com.indigententerprises.applications.serviceinterfaces.MessageRequestService {
    private final MessageRequestRepository messageRequestRepository;
    private final MessageResponseFailureRepository messageResponseFailureRepository;
    private final MessageResponseSuccessRepository messageResponseSuccessRepository;
    private final Function<String, String> worker;
    private final ExecutorService executorService;

    public MessageRequestService(
            final MessageRequestRepository messageRequestRepository,
            final MessageResponseFailureRepository messageResponseFailureRepository,
            final MessageResponseSuccessRepository messageResponseSuccessRepository,
            final Function<String, String> worker
    ) {
        this.messageRequestRepository = messageRequestRepository;
        this.messageResponseFailureRepository = messageResponseFailureRepository;
        this.messageResponseSuccessRepository = messageResponseSuccessRepository;
        this.worker = worker;
        this.executorService = Executors.newSingleThreadExecutor();
    }

    public MessageRequest findByMessageId(final String messageId) throws NoSuchElementException {
        final Optional<MessageRequest> messageRequest = messageRequestRepository.findByMessageId(messageId);
        if (messageRequest.isPresent()) {
            return messageRequest.get();
        } else {
            throw new NoSuchElementException();
        }
    }

    public List<MessageRequest> findAll() {
        return messageRequestRepository.findAll();
    }

    public MessageRequest insert(final MessageRequest messageRequest) throws DuplicateIdentifierException {
        // id must be unique
        final Optional<MessageRequest> existingMessageRequest =
                messageRequestRepository.findByMessageId(messageRequest.getMessageId());
        if (existingMessageRequest.isPresent()) {
            throw new DuplicateIdentifierException(messageRequest.getMessageId() + " already present");
        } else {
            final MessageRequest result = messageRequestRepository.save(messageRequest);

            // executor-service represents our work queue. longer-running tasks would be better
            //   served by a persistent distributed queue.
            executorService.execute(
                    new Runnable() {
                        @Override
                        public void run() {
                            try {
                                final String messageContent = messageRequest.getMessageContent();
                                final String output = worker.apply(messageContent);
                                final MessageResponseSuccess messageResponseSuccess = new MessageResponseSuccess();
                                messageResponseSuccess.setMessageId(messageRequest.getMessageId());
                                messageResponseSuccess.setMessageHash(output);

                                try {
                                    // need not return the saved object; correlation occurs through identifiers.
                                    messageResponseSuccessRepository.save(messageResponseSuccess);
                                } catch (RuntimeException e) {
                                    // persistence failure ...
                                    // cannot inform the client directly. inform the observability platform and
                                    //   allow ops personnel to detect the problem.
                                }
                            } catch (RuntimeException e) {
                                // exception occurred during long-running task or during object persistence.
                                // need to persist information about the exception; otherwise, client polls
                                //   endlessly.
                                final MessageResponseFailure messageResponseFailure = new MessageResponseFailure();
                                messageResponseFailure.setCode("async-operation-failure");
                                messageResponseFailure.setMessage("background job failed");
                                messageResponseFailure.setDetails(e.getMessage());

                                try {
                                    messageResponseFailureRepository.save(messageResponseFailure);
                                } catch (RuntimeException e2) {
                                    // persistence failure ...
                                    // cannot inform the client directly. inform the observability platform and
                                    //   allow ops personnel to detect the problem.
                                }
                            }
                        }
                    }
            );

            return result;
        }
    }
}
