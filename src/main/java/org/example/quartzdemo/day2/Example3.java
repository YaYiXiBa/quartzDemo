package org.example.quartzdemo.day2;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.KeyMatcher;

import static org.quartz.CronScheduleBuilder.cronSchedule;

public class Example3 {
    public static void main(String[] args) throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        JobDetail jobDetail = JobBuilder.newJob(TimeJob.class).withIdentity("TimeJob", "group1").build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("TimeTrigger", "group1")
                .withSchedule(cronSchedule("5/5 * * * * ?")).build();
        MyJobListener jobListener = new MyJobListener();

        scheduler.getListenerManager().addJobListener(jobListener, KeyMatcher.keyEquals(jobDetail.getKey()));
        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();

    }
}
