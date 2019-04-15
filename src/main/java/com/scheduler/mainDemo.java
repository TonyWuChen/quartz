package com.scheduler;

import com.job.testJob;
import org.quartz.*;

public class mainDemo {
    private int count;

    public void setCount(int count) {
        this.count = count;
    }

    public static void main(String[] args) throws Exception {
        // 1.调度器（Scheduler），从工厂中获取点的实例
        SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();

        Scheduler sched = schedFact.getScheduler();

        sched.start();
        // 2.任务实例（JobDetail）
        JobDetail jobDetail=JobBuilder.newJob(testJob.class)   // 加载任务类
                .withIdentity("job1","group1")//参数一 ：任务的名称（唯一实例） 参数二：任务组的名称
                .usingJobData("message","打印日志")
                .usingJobData("count",1)
                .build();
        System.out.println(jobDetail.getKey().getName());
        // 3 触发器
        Trigger ti=TriggerBuilder.newTrigger()
                .withIdentity("trigger1","group1")
                .startNow()   //马上执行-
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().repeatSecondlyForever(5))
                .usingJobData("message","simple触发器")
                .build();
        // 让调度器关联任务和触发器，保证按照触发器定义的条件执行任务
        sched.scheduleJob(jobDetail,ti);
        // 启动
        sched.start();
    }
}
