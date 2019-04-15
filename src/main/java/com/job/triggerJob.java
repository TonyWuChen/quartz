package com.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.TriggerKey;

import java.text.SimpleDateFormat;
import java.util.Date;

public class triggerJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        // 输出当前时间的任务
        Date date = new Date();
        // 转换时间输出格式  转成String类型
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        String fdate=dateFormat.format(date);
        System.out.println("数据库定时备份，备份时间"+fdate);
        System.out.println("开始执行时间"+jobExecutionContext.getTrigger().getStartTime());
        System.out.println("结束执行时间"+jobExecutionContext.getTrigger().getEndTime());
    }
}
