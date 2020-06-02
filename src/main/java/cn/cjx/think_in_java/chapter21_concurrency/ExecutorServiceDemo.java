package cn.cjx.think_in_java.chapter21_concurrency;

import java.util.concurrent.*;

/**
 * @功能描述:
 * @使用对象:xx系统
 * @接口版本:
 * @创建日期: 2019/5/27 16:49
 * @创建人:陈俊旋
 */
public class ExecutorServiceDemo {
    public static void execute() {
        //为每个任务都创建一个线程
//        ExecutorService executorService = Executors.newCachedThreadPool();
        ExecutorService fs = Executors.newFixedThreadPool(5);
//        ExecutorService ss = Executors.newSingleThreadExecutor();
        fs.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("11");
                System.out.println("12");
            }
        });
        for (int i = 0; i < 500; i++) {
//            final int j = i;
//            ss.execute(() -> System.out.println(Thread.currentThread().getName() + ":" + j));
//            Future<Integer> result = ss.submit(() -> j + 1);
//            try {
//                TimeUnit.MILLISECONDS.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            try {
//                System.out.println(result.get());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
        }
        fs.shutdown();
    }
    public static void daemon() {
        for (int i = 0; i < 5; i++) {
            final int j = i;
            Thread t = new Thread(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                    System.out.println("child" + j + " execute !!!!!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            t.setDaemon(true);
            t.start();
        }
        //若打开下面的注释则不会看到daemon被杀死，因为还有没有执行完成的线程
        Thread t = new Thread(() -> {
            try {
//                TimeUnit.MILLISECONDS.sleep(1000);
                TimeUnit.MILLISECONDS.sleep(800);
                System.out.println("child not daemon execute !!!!!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.start();
        new Thread(() -> System.out.println("child not daemon execute !!!!!")).start();
    }
    public static void main(String[] args) {
        execute();
//        daemon();
        System.out.println("main end!!!");
    }
}
