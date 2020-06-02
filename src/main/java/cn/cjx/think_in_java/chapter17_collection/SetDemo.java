package cn.cjx.think_in_java.chapter17_collection;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * @功能描述:
 * @使用对象:xx系统
 * @接口版本:
 * @创建日期: 2019/5/24 13:55
 * @创建人:陈俊旋
 */
public class SetDemo {
    public static <T> Set<T> fill(Set<T> set, Class<T> type) {
        for (int i = 0; i < 10; i++) {
            try {
                T t = type.getConstructor(int.class).newInstance(i);
                set.add(t);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return set;
    }

    public static void main(String[] args) {
//        Set<SortedElement> set = new TreeSet<>();
        Set<SortedElement> set = new HashSet<>();
        fill(set, SortedElement.class);
        fill(set, SortedElement.class);
        System.out.println(set);

        //---------sortedSet--------
        SortedSet<String> sortedSet = new TreeSet<>();
        Collections.addAll(sortedSet, "ONE TWO THREEE FOUR FIVE SIX SEVEN EIGHT".split(" "));
        System.out.println(sortedSet);
        String first = sortedSet.first();
        String last = sortedSet.last();
        System.out.println(first);
        System.out.println(last);
        Iterator<String> it = sortedSet.iterator();
        for (int i = 0; i < 7; i++) {
            if (i == 3)
                first = it.next();
            else if (i == 6)
                last = it.next();
            else
                it.next();
        }
        System.out.println(first);
        System.out.println(last);
    }
}

class SortedElement implements Comparable<SortedElement> {

    int i;

    public SortedElement(int i) {
        this.i = i;
    }

    @Override
    public int compareTo(SortedElement e) {
//        return e.i-i;//如果e的i是一个非常大的正数，自身的i是一个非常小的数结果会溢出，而导致结果变成负数
        return e.i > i ? -1 : ((e.i == i) ? 0 : 1);
    }

    @Override
    public String toString() {
        return Integer.valueOf(i).toString()/*+hashCode()*/;
    }
}

class LinkedSortedSet<T>{
    LinkedList<T> list = new LinkedList<>();

    public void add(T t) {
        Comparable e = (Comparable) t;
        int position =0;
        for (Iterator<T> it = list.iterator(); it.hasNext(); ) {
            T next =  it.next();
            if (e.compareTo(next)<0) {
                break;
            }
            position++;
        }
        list.add(position,t);
    }

    public T get(int index) {
        return list.get(index);
    }

    @Override
    public String toString() {
        return list.toString();
    }

    public static void main(String[] args) {
        //LinkedList的add(int index,T t)是在该位置之前添加元素
//        LinkedList<String> list = new LinkedList<>();
//        list.add("a");
//        list.add("b");
//        System.out.println(list);
//        list.add(0,"q");
//        System.out.println(list);

        //-------大的在前------
        LinkedSortedSet<String> sortedSet = new LinkedSortedSet<>();
        sortedSet.add("a");
        sortedSet.add("b");
        sortedSet.add("d");
        System.out.println(sortedSet);
        sortedSet.add("c");
        System.out.println(sortedSet);

        LinkedSortedSet<SortedElement> elementsSet = new LinkedSortedSet<>();
        elementsSet.add(new SortedElement(1));
        elementsSet.add(new SortedElement(2));
        elementsSet.add(new SortedElement(4));
        System.out.println(elementsSet);
        elementsSet.add(new SortedElement(3));
        System.out.println(elementsSet);
    }
}