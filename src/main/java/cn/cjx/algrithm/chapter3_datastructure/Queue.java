package cn.cjx.algrithm.chapter3_datastructure;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @功能描述:
 * @使用对象:xx系统
 * @接口版本:
 * @创建日期: 2019/6/4 11:15
 * @创建人:陈俊旋
 */
public class Queue<T> implements Iterable<T>,Cloneable{
    private LinkedList<T> list = new LinkedList<>();

    public boolean offer(T t) {
//        return list.offerFirst(t);//stack
        return list.offerLast(t);//queue
    }

    public boolean offerAll(Queue<T> queue) {
        try {
            if (this == queue)
                queue = (Queue)queue.clone();
            for (Iterator<T> it = queue.iterator(); it.hasNext(); ) {
                T t = it.next();
                offer(t);
            }
            return true;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Queue<T> clone = new Queue<>();
        for (Iterator<T> it = this.iterator(); it.hasNext(); ) {
            T t = it.next();
            clone.offer(t);
        }
        return clone;
    }

    public T remove() {
        return list.removeLast();
    }

    public T poll() {
        return list.poll();
    }

    public T peek() {
        return list.peek();
    }

    public void clear() {
        list.clear();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }


    @Override
    public String toString() {
        return list.toString();
    }

    public static void main(String[] args) {
        Queue<String> queue = new Queue<>();
//        BlockingQueue<String> queue = new ArrayBlockingQueue<>(20);
        for (int i = 0; i < 10; i++) {
            queue.offer("a" + i);
        }
        System.out.println(queue);
        for (int i = 0; i < 10; i++) {
            System.out.println(queue.poll());
        }
    }
}
