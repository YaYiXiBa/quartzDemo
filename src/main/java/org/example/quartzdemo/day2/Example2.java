package org.example.quartzdemo.day2;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.KeyMatcher;

import java.util.HashSet;
import java.util.Set;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobKey.jobKey;

public class Example2 {
    public static void main(String[] args) throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();


        JobDetail job = JobBuilder.newJob(TimeJob.class).withIdentity("job1", "group1").build();
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .forJob(job)
                .withSchedule(cronSchedule("5/5 * * * * ?")).build();

        Trigger trigger2 = TriggerBuilder.newTrigger()
                .withIdentity("trigger2", "group2")
                .forJob(job)
                .withSchedule(cronSchedule("5/10 * * * * ?")).build();

        MyTriggerListener listener = new MyTriggerListener();
        //不指定任务或者trigger的话会全局生效哦
        TriggerKey triggerKey = new TriggerKey("trigger1", "group1");
        scheduler.getListenerManager().addTriggerListener(listener, KeyMatcher.keyEquals(triggerKey));


        Set<Trigger> triggers = new HashSet<>();
        triggers.add(trigger);
        triggers.add(trigger2);
        scheduler.scheduleJob(job, triggers, true);

        scheduler.start();
    }
}
