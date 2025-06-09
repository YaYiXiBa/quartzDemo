package org.example.quartzdemo.day2;

import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.TriggerListener;

public class MyTriggerListener implements TriggerListener {
    //监听器的唯一标识,方便后续使用调度器管理
    @Override
    public String getName() {
        return "TriggerListener--job";
    }

    //触发器达预设时间,Job任务被执行之前会调用此方法
    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext jobExecutionContext) {
        String name = jobExecutionContext.getJobDetail().getKey().getName();
        System.out.println("准备好了吗?任务即将执行...");
    }

    //如果你希望在job被执行之前,在某些情况下阻止任务的执行,比如判断一下资源情况之类的
    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext jobExecutionContext) {
        return false;
    }

    //发生misfired的情况时
    @Override
    public void triggerMisfired(Trigger trigger) {
        System.out.println("man , misFired !!!");
    }
    //触发器完成时执行
    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext jobExecutionContext, Trigger.CompletedExecutionInstruction completedExecutionInstruction) {
        System.out.println("真是一次完美的触发呢");
    }
}
