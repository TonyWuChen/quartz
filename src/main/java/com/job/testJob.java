package com.job;

import org.quartz.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
* 任务
* 2019年4月15日11:47:38
* */

@PersistJobDataAfterExecution   //多次调用Job是，会对job持久化
public class testJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        // 输出当前时间的任务
        Date date = new Date();
        // 转换时间输出格式  转成String类型
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        String fdate=dateFormat.format(date);
        //获取JobDetail中的内容
        JobKey jobKey=jobExecutionContext.getJobDetail().getKey();
        System.out.println(jobKey.getName());
        // 获取触发器的内容
        TriggerKey triggerKey = jobExecutionContext.getTrigger().getKey();
        System.out.println(triggerKey.getName());
        // 从JobDetail获取jobDateMap的数据
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        String jobDateMessage = jobDataMap.getString("message");
        System.out.println("任务数据的参数值："+jobDateMessage);
        // 获取Trigger对象的内容
        JobDataMap triggerDataMap1 = jobExecutionContext.getTrigger().getJobDataMap();
        System.out.println("触发器数据的参数值："+triggerDataMap1.getString("message"));
        // 获取其他的内容
        System.out.println("当前的执行时间"+dateFormat.format(jobExecutionContext.getFireTime()));
        System.out.println("下一次任务的执行时间"+dateFormat.format(jobExecutionContext.getNextFireTime()));
        // 工作内容
        System.out.println("数据库定时备份，备份时间"+fdate);
        JobDataMap jobDataMap2 = jobExecutionContext.getJobDetail().getJobDataMap();
        int count = jobDataMap.getInt("count");
        ++count;
        jobExecutionContext.getJobDetail().getJobDataMap().put("count",count);
        System.out.println("任务执行次数"+count);
        System.out.println("=========================================================");
    }
}
