package com.indigententerprises.applications.serviceimplementations;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.function.Function;

@Component
public class LongRunningTaskWorker implements Function<String, String> {

    // hashing is merely a simulation of a long-running process
    public String apply(final String text) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(text.getBytes());
            final byte [] hashedBytes = messageDigest.digest();
            final StringBuilder stringBuilder = new StringBuilder();

            for (final byte b : hashedBytes) {
                stringBuilder.append(String.format("%02x", b));
            }

            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
