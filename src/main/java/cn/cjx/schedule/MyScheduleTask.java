//package cn.cjx.schedule;
//
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
///**
// * @description:
// * @author: 陈俊旋
// * @date: 2019-04-14 23:42
// */
//@Component
//public class MyScheduleTask {
//
//    @Scheduled(fixedDelay = 1000)
//    public void fixedDelay() {
//        System.out.println("---------------------");
//        System.out.println("fixedDelay arraysDemo per 1s!!!!!!!!!");
//    }
//
//    @Scheduled(fixedDelayString = "${MyScheduleTask.fixedDelayString}")
//    public void fixedDelayStr() {
//        System.out.println("---------------------");
//        System.out.println("fixedDelayString per 1s!!!!!!!!!");
//    }
//
//    //延迟n毫秒后才执行
//    @Scheduled(fixedRate = 1000,initialDelay = 2000)
//    public void fixedRate() {
//        System.out.println("---------------------");
//        System.out.println("fixedRate initialDelay arraysDemo per 1s!!!!!!!!!");
//    }
//
//    @Scheduled(fixedRateString = "${MyScheduleTask.fixedRateString}")
//    public void fixedRateStr() {
//        System.out.println("---------------------");
//        System.out.println("fixedRateString arraysDemo per 1s!!!!!!!!!");
//    }
//
//    @Scheduled(cron = "*/2 * * * * *")
//    public void cron() {
//        System.out.println("---------------------");
//        System.out.println("cron task arraysDemo per 2s!!!!!!!!!");
//    }
//
//    @Scheduled(cron = "${MyScheduleTask.cron.expression}")
//    public void cronStr() {
//        System.out.println("---------------------");
//        System.out.println("cronStr task arraysDemo per 1s!!!!!!!!!");
//    }
//
////    public static void main(String[] args) {
////        Integer i = 1000;
////        Integer j = 1000;
////        Integer m = 100;
////        Integer n = 100;
////        System.out.println(i==j);
////        System.out.println(m==n);
////    }
//
//}
