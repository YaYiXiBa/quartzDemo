package org.example.quartzdemo.day01;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.time.LocalDate;
import java.util.Date;

import static org.quartz.DateBuilder.futureDate;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

public class Example01 {
    public static void main(String[] args) {
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            //你要做的任务是什么?
            //实际上你新建的是一个jobdetail实例,而该实例的执行逻辑写在了Myjob.class中
            JobDetail jobDetail = JobBuilder.newJob(Myjob.class)
                    .withIdentity("name1", "group1")
                    .usingJobData("data1", "value1")
                    .usingJobData("data2", "value2")
                    .build();
            //持久性?
            jobDetail.isDurable();
//            Trigger trigger = TriggerBuilder.newTrigger().forJob(jobDetail).
//                    withIdentity("mytrigger", "group2")
//                    .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
//                    .startAt(new Date())
//                    .endAt(new Date())
//                    .withPriority(9)  //任务优先级
//                    .build();
            Trigger trigger = (SimpleTrigger)TriggerBuilder.newTrigger().forJob(jobDetail)
                    .startAt(futureDate(5, DateBuilder.IntervalUnit.SECOND)).withSchedule(
                            SimpleScheduleBuilder.simpleSchedule()
                                    .withIntervalInSeconds(5).withRepeatCount(10)
                    ).build();
            scheduler.scheduleJob(jobDetail, trigger);

        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }


    }
}
