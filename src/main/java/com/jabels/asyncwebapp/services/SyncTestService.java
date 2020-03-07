package com.jabels.asyncwebapp.services;

import com.jabels.asyncwebapp.model.SimpleMessageCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class SyncTestService {

    public SimpleMessageCommand computeMessage(String text) {
        log.info("Start of computing sync message with text={}", text);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            log.error("InterruptedException={}", e.getLocalizedMessage());
        }
        log.info("End of computing sync message with text={}", text);
        return new SimpleMessageCommand(text);
    }
}
