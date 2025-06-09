package org.example.quartzdemo.day2;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

public class MyJobListener implements JobListener {
    @Override
    public String getName() {
        return "name";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext jobExecutionContext) {
        System.out.println("任务要被执行咯");
    }
    //任务被触发器监听器（TriggerListener）否决时调用。
    @Override
    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {
        System.out.println("不能执行捏~");
    }
    //无论任务执行成功或者异常,都会调用这个方法
    @Override
    public void jobWasExecuted(JobExecutionContext jobExecutionContext, JobExecutionException e) {
        System.out.println("任务执行结束");
    }
}
