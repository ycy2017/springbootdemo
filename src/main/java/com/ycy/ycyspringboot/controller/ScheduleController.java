package com.ycy.ycyspringboot.controller;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.logging.SimpleFormatter;


/**
 * spring 的调度任务
 * (1)  调用任务是由线程来执行的,多个任务可以有一个线程来执行(如果线程池中只有一个线程)
 * (2)　只是说是 fixedRate 任务两次执行时间间隔是任务的开始点，而 fixedDelay 的间隔是前次任务的结束与下次任务的开始。
 */
@Component
public class ScheduleController {

  /**
   * 每分钟触发一次?
   */
  @Scheduled(cron = "0/10 * * * * ? ")
  public void schedule() {
    System.out.println(Thread.currentThread().getName() + " start time " + LocalTime.now() + " 每个10秒做一次");
  }

  /**
   * 每个10秒来一次,如果中间的任务,长耗时超过20秒?? 这个任务是多线程的吗?
   * <p>
   * 调度任务只有一个线程
   */
  @Scheduled(cron = "0/10 * * * * ? ")
  public void schedule2() {
    SimpleDateFormat simpleFormatter = new SimpleDateFormat("HH:mm:ss");
    Long start = System.currentTimeMillis();
    Date st = new Date(start);
    System.out.println(Thread.currentThread().getName() + " start time " + simpleFormatter.format(st) + " 每个20秒做一次");
    try {
      Thread.sleep(20000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    Long end = System.currentTimeMillis();
    Date en = new Date(end);
    System.out.println(Thread.currentThread().getName() + " end time " + en + " 每个20秒做一次,cost " + (end - start));
  }

}
