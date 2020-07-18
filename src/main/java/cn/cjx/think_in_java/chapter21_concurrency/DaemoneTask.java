package cn.cjx.think_in_java.chapter21_concurrency;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @功能描述:
 * @使用对象:xx系统
 * @创建日期: 2020/7/17 15:07
 * @创建人:陈俊旋
 */
public class DaemoneTask {

//    public static void main(String[] args) throws InterruptedException {
//        Thread thread = new Thread(new CountDown());
//        thread.setDaemon(true);
//        thread.start();
//        TimeUnit.SECONDS.sleep(2);
//        System.out.println("main thread released!");
//    }

    public static void main(String[] args)
    {
        System.out.println(fun());
    }

    public static int fun()
    {
        int i = 10;
        try
        {
            //doing something

            return i;
        }catch(Exception e){
            return i;
        }finally{
            System.out.println(200);
        }
    }

    public static class CountDown implements Runnable {
        @Override
        public void run() {
            while (true){
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            }
        }
    }

    public static class DaemonThreadFactory implements ThreadFactory {
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setDaemon(true);
            thread.setName("cjx-test");
            return thread;
        }
    }
}
