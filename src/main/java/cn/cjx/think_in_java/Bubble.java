package cn.cjx.think_in_java;

import java.util.Arrays;

/**
 * @author: 陈俊旋
 * @description:
 * @date: 2019/5/5 10:22
 */
public class Bubble {
    public static int[] bubble(int... arr) {
        boolean flag = true;
        int count = 0;
        for (int i = 0; i < arr.length-1; i++) {
            flag = true;
            for (int j = 0; j < arr.length-1-i; j++) {
                int tmp = arr[j+1];
                if (arr[j]>arr[j+1]){
                    arr[j+1] = arr[j];
                    arr[j] = tmp;
                    flag = false;
                }
            }
            count++;
            //优化过后的算法只用了6次就完成了
//            if (flag) {
//                System.out.println(count);
//                return arr;
//            }
        }
        //未优化之前的算法用了13次
        System.out.println(count);
        return arr;
    }

    public static void main(String[] args) {
        int[] ints = bubble(new int[]{3, 1, 6, 9,5,8,7,4,99,54,32,66,99,74});
        System.out.println(Arrays.toString(ints));
    }
}
