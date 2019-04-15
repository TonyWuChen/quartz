package com.scheduler;

import com.job.testJob;
import com.job.triggerJob;
import org.quartz.*;

import java.util.Date;

public class triggetDemo {

    public static void main(String[] args) throws Exception {

        Date startDate = new Date();
        //推迟3s执行
        startDate.setTime(startDate.getTime()+3000);
        Date endDate = new Date();
        // 推迟10s 执行
        endDate.setTime(endDate.getTime()+10000);

        // 1.调度器（Scheduler），从工厂中获取点的实例
        SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();
        Scheduler sched = schedFact.getScheduler();
        // 2.任务实例（JobDetail）
        JobDetail jobDetail=JobBuilder.newJob(triggerJob.class)   // 加载任务类
                .withIdentity("job1","group1")//参数一 ：任务的名称（唯一实例） 参数二：任务组的名称
                .usingJobData("message","打印日志")
                .build();
        System.out.println(jobDetail.getKey().getName());
        // 3 触发器
        Trigger ti=TriggerBuilder.newTrigger()
                .withIdentity("trigger1","group1")
                //.startNow()   //马上执行-
                .startAt(startDate)
                .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(5).withRepeatCount(2))  //重复执行三次
                .endAt(endDate)    // 结束时间优先于重复次数
                .build();
        // 让调度器关联任务和触发器，保证按照触发器定义的条件执行任务
        sched.scheduleJob(jobDetail,ti);
        // 启动
        sched.start();
    }
    }

