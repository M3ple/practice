package cn.cjx.think_in_java.chapter21_concurrency;

import java.util.concurrent.TimeUnit;

/**
 * @功能描述:
 * @使用对象:xx系统
 * @创建日期: 2020/7/17 15:45
 * @创建人:陈俊旋
 */
public class JoinDemo {
    public static void main(String[] args) {
        Thread t = new Thread(new CountDown());
        Joinner joinner = new Joinner(t);
        Thread joinnerThread = new Thread(joinner);
        joinnerThread.start();
    }

    public static class Joinner implements Runnable {

        Thread t;

        public Joinner(Thread t) {
            this.t = t;
        }

        @Override
        public void run() {
            try {
                System.out.println("join "+t.getName());
                t.start();
                t.join();
//                throw new RuntimeException("exception occurred");
                System.out.println(t.getName()+" end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class CountDown implements Runnable {

        public int count = 5;

        @Override
        public void run() {
            while (count-->0){
                try {
                    System.out.println("count down"+count);
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("count down end!");
        }
    }
}
