package cn.cjx.think_in_java.chapter17_collection;

import java.util.*;

/**
 * @功能描述:
 * @使用对象:xx系统
 * @接口版本:
 * @创建日期: 2019/5/23 16:35
 * @创建人:陈俊旋
 */
public class CountingIntegerList extends AbstractList<Integer> {
    private int size;
    @Override
    public Integer get(int index) {
//        return 0;
        return index;
    }

    public CountingIntegerList(int size) {
        this.size = size>0?size:0;
    }

    @Override
    public int size() {
        return size;
    }

    public static void main(String[] args) {
        CountingIntegerList c = new CountingIntegerList(30);
        System.out.println(c.toString());//iterator的next()方法调用的是get();
    }
}
//关于collection的toString()方法！！！！！
class StackOverFlowList<E> extends ArrayList<E>{

//    public E get(E e) {
////        return 0;
//        return e;
//    }
    public String toString() {
        Iterator<E> it = iterator();
        if (! it.hasNext())
            return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (;;) {
            E e = it.next();
            sb.append(e);
//            sb.append(e == this ? "(this Collection)" : e);//防止存入collection类型导致循环调用stackOverFlow
            if (! it.hasNext())
                return sb.append(']').toString();
            sb.append(',').append(' ');
        }
    }

    public static void main(String[] args) {
        StackOverFlowList<Collection> list = new StackOverFlowList<>();
        HashSet<Object> set = new HashSet<>();
        list.add(set);
        list.add(list);
        System.out.println(list);
    }
}
