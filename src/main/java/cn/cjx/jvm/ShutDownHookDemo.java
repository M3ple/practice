package cn.cjx.jvm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @功能描述:
 * @使用对象:xx系统
 * @接口版本:
 * @创建日期: 2019/9/11 15:21
 * @创建人:陈俊旋
 */
public class ShutDownHookDemo {
    public static void main(String[] args)
    {
        // register shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("Do something in Shutdown Hook")));

        // sleep for some time
        try {
            for (int i=0; i<3; i++) {
                System.out.println("Count: " + i + "...");
                TimeUnit.MILLISECONDS.sleep(1000);
            }
            List nullList = new ArrayList<>();
            System.out.println("Trying to print null list's first element: " + nullList.get(0).toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Ready to exit.");
        System.exit(0);
    }
}
