package cn.cjx.think_in_java.chapter15_generic;

import java.util.ArrayList;
import java.util.List;

/**
 * @功能描述:
 * @使用对象:xx系统
 * @接口版本:
 * @创建日期: 2019/5/19 13:48
 * @创建人:陈俊旋
 */
public class GenericDemo {
    public static void main(String[] args) {
        A[] arr = new B[10];
        arr[0] = new B();//一旦设定了值，他就会自动转换为该类型的数组，不能再对其进行赋值其它类型
        //        arr[1] = new A();//B[] can't store A type due to array;characteristic
        B[] brr = (B[]) arr;
        System.out.println(arr.getClass());
        A[] arr2 = new A[10];
        arr2[0] = new B();
        arr2[1] = new C();
        System.out.println(arr2);
        //A的子类
        List<? extends A> list = new ArrayList<>();
        //        list.add(new B()); // 编译失败
        //A的父类
        List<? super A> list2 = new ArrayList<>();
        list2.add(new B());
        List<A> list3 = new ArrayList<A>();
        list3.add(new B());
    }
}

class A {
}

class D {
}

class B extends A {
}

class C extends A {
}

class TestDemo {
    public static void run(List list) {
        list.add(new A());
    }

    public static void main(String[] args) {
        //思考点一
        List<D> list = new ArrayList<>();
        run(list);//可以存入，取出来的时候会cast to D发生异常ClassCastException
        for (int i = 0; i < list.size(); i++) {
//            D d =  list.get(i); //open annotation will cause error
//            System.out.println(d);
        }
    }
}
