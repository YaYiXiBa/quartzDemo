package org.example.quartzdemo.day01;

import org.quartz.*;

//两个注解最好同时使用,不然可能会导致并发问题

//不允许并发执行
//实际限制: 可以有多个不同的jobjetail的任务在执行,但是不能够有多个相同的jobnndetails(体会一下)
@DisallowConcurrentExecution
//persist续,在执行完之后更新更新JobDetail中JobDataMap的数据
@PersistJobDataAfterExecution
public class Myjob implements Job {
    //注意一下可能会抛出的异常
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //任务被强制关闭后会重载
        boolean recovering = jobExecutionContext.isRecovering();


        JobDataMap map = jobExecutionContext.getJobDetail().getJobDataMap();
        System.out.println("JobDataMap: ");
        map.forEach((k,v)->{
            String value = v.toString();
            System.out.println(k+":"+value);
        });
        System.out.println("-----");
        System.out.println("Myjob executed : " + jobExecutionContext.getJobDetail().getKey());
    }
}
