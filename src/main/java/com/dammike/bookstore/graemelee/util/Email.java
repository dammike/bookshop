package com.dammike.bookstore.graemelee.util;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ToString
public class Email {
    private static Email instance;

    private Email() {
        if (instance == null) {
            instance = new Email();
        }
    }

    public static Email getInstance() {
        return instance;
    }

    public void sendConfirmation(String email) {
        send(email);
    }

    private String send(String address) {
        //todo
        log.debug("sending email to: " + address);
        log.info("email sent");
        return "status.ok";
    }
}
