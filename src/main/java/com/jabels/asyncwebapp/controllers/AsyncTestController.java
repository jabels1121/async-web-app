package com.jabels.asyncwebapp.controllers;

import com.jabels.asyncwebapp.model.SimpleMessageCommand;
import com.jabels.asyncwebapp.services.AsyncTestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
@Slf4j
@RequestMapping(path = "/async")
public class AsyncTestController {

    private final AsyncTestService asyncTestService;

    public AsyncTestController(AsyncTestService asyncTestService) {
        this.asyncTestService = asyncTestService;
    }

    @GetMapping(path = "/message", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public DeferredResult<SimpleMessageCommand> getAsyncMessage(@RequestParam final String text) {
        log.info("In async controller called /sync/message?text={}", text);
        return DeferredResultWrapper.from(asyncTestService.computeMessage(text));
    }

}
