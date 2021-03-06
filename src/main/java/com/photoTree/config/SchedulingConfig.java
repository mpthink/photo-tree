package com.photoTree.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
public class SchedulingConfig {

	@Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        //定时任务执行线程池核心线程数
        taskScheduler.setPoolSize(16);
        taskScheduler.setWaitForTasksToCompleteOnShutdown(true);
        taskScheduler.setRemoveOnCancelPolicy(true);
        taskScheduler.setThreadNamePrefix("Dapp-Scheduler-");
        return taskScheduler;
    }
}
