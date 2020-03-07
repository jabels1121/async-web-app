package com.jabels.asyncwebapp.services;

import com.jabels.asyncwebapp.model.SimpleMessageCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class AsyncTestService {

    private TaskExecutor taskExecutor;

    public AsyncTestService(@Qualifier("taskExecutor") TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    @Async
    public CompletableFuture<SimpleMessageCommand> computeMessage(final String text) {
        return CompletableFuture.supplyAsync(() -> {
            log.info("Start of computing async message with text={}", text);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                log.error("Error={}", e.getLocalizedMessage());
            }
            log.info("End of computing async message with text={}", text);
            return new SimpleMessageCommand(text);
        });
    }

    @Async
    public CompletableFuture<SimpleMessageCommand> computeMessageAsync(final String text) {
        log.info("Start of computing async message with text={}", text);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            log.error("Error={}", e.getLocalizedMessage());
        }
        log.info("End of computing async message with text={}", text);
        return CompletableFuture.completedFuture(new SimpleMessageCommand(text));
    }

}
