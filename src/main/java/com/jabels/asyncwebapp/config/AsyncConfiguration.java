package com.jabels.asyncwebapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
@EnableAsync
@EnableScheduling
public class AsyncConfiguration {

    private static final String TASK_EXECUTOR_NAME_PREFIX = "async-test-exec-";
    private static final String TASK_SCHEDULER_NAME_PREFIX = "async-test-sched-";

    private static final int POOL_SIZE = Runtime.getRuntime().availableProcessors();
    private static final int MAX_POOL_SIZE = POOL_SIZE * 2;

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(POOL_SIZE);
        executor.setMaxPoolSize(MAX_POOL_SIZE);
        executor.setThreadNamePrefix(TASK_EXECUTOR_NAME_PREFIX);
        executor.initialize();
        return executor;
    }

    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(MAX_POOL_SIZE);
        scheduler.setThreadNamePrefix(TASK_SCHEDULER_NAME_PREFIX);
        scheduler.initialize();
        return scheduler;
    }

}
