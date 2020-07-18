package cn.cjx.think_in_java.chapter21_concurrency;

/**
 * @功能描述:
 * @使用对象:xx系统
 * @创建日期: 2020/7/17 11:59
 * @创建人:陈俊旋
 */
public class YieldDemo {

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(new CountDown()).start();
        }
    }

    public static class CountDown implements Runnable {

        public int count = 5;
        public static int num = 0;
        public final int id = num++;

        @Override
        public void run() {
            while (count-->0){
                System.out.println("thread#"+id+"count:"+count);
                Thread.yield();
            }
        }
    }
}
