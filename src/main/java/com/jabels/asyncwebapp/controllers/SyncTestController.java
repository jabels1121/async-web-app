package com.jabels.asyncwebapp.controllers;

import com.jabels.asyncwebapp.model.SimpleMessageCommand;
import com.jabels.asyncwebapp.services.AsyncTestService;
import com.jabels.asyncwebapp.services.SyncTestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(path = "/sync")
public class SyncTestController {

    private final SyncTestService syncTestService;

    public SyncTestController(SyncTestService syncTestService ) {
        this.syncTestService = syncTestService;
    }

    @GetMapping(path = "/message", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public SimpleMessageCommand getSyncMessage(@RequestParam final String text) {
        log.info("In sync controller called /sync/message?text={}", text);
        return syncTestService.computeMessage(text);
    }

}
