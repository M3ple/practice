package cn.cjx.think_in_java.chapter21_concurrency;

import java.util.concurrent.CompletableFuture;

/**
 * @功能描述:
 * @使用对象:xx系统
 * @创建日期: 2020/8/1 9:28
 * @创建人:陈俊旋
 */
public class CompletableFutureDemo {

    /**
     * CompletableFuture的优点是：
     * 异步任务结束时，会自动回调某个对象的方法；
     * 异步任务出错时，会自动回调某个对象的方法；
     * 主线程设置好回调后，不再关心异步任务的执行。
     */
    public static void main(String[] args) throws Exception {
//        asyncTask();
        asyncTaskWithException();
    }

    public static void asyncTaskWithException() throws InterruptedException {
        CompletableFuture.supplyAsync(()->1/0).exceptionally(throwable -> {
            throwable.printStackTrace();
            return Integer.valueOf(-1);
        });
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        Thread.sleep(2000);
    }

    /**
     * 异步执行任务
     * @throws InterruptedException
     */
    private static void asyncTask() throws InterruptedException {
        // 两个CompletableFuture执行异步查询:
        CompletableFuture<String> cfQueryFromSina = CompletableFuture.supplyAsync(() -> {
            return queryCode("中国石油", "https://finance.sina.com.cn/code/");
        });
        CompletableFuture<String> cfQueryFrom163 = CompletableFuture.supplyAsync(() -> {
            return queryCode("中国石油", "https://money.163.com/code/");
        });

        // 用anyOf合并为一个新的CompletableFuture:
        CompletableFuture<Object> cfQuery = CompletableFuture.anyOf(cfQueryFromSina, cfQueryFrom163);

        // 两个CompletableFuture执行异步查询:
        CompletableFuture<Double> cfFetchFromSina = cfQuery.thenApplyAsync((code) -> {
            return fetchPrice((String) code, "https://finance.sina.com.cn/price/");
        });
        CompletableFuture<Double> cfFetchFrom163 = cfQuery.thenApplyAsync((code) -> {
            return fetchPrice((String) code, "https://money.163.com/price/");
        });

        // 用anyOf合并为一个新的CompletableFuture:
        //anyOf()可以实现“任意个CompletableFuture只要一个成功”，allOf()可以实现“所有CompletableFuture都必须成功”
        CompletableFuture<Object> cfFetch = CompletableFuture.anyOf(cfFetchFromSina, cfFetchFrom163);

        // 最终结果:
        cfFetch.thenAccept((result) -> {
            System.out.println("price: " + result);
        });
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        Thread.sleep(2000);
    }

    static String queryCode(String name, String url) {
        System.out.println("query code from " + url + "...");
        try {
            Thread.sleep((long) (Math.random() * 100));
        } catch (InterruptedException e) {
        }
        return "601857";
    }

    static Double fetchPrice(String code, String url) {
        try {
            Thread.sleep((long) (Math.random() * 100));
        } catch (InterruptedException e) {
        }
        double v = 5 + Math.random() * 20;
        System.out.println("query price from " + "v-"+v +" "+ url +"...");
        return v;
    }
}
