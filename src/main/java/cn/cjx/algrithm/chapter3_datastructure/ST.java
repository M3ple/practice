package cn.cjx.algrithm.chapter3_datastructure;

/**
 * @功能描述:SequentialTable-有序符号表API
 * @使用对象:xx系统
 * @接口版本:
 * @创建日期: 2019/6/3 14:47
 * @创建人:陈俊旋
 */
public interface ST<K,V> extends Iterable<K>{
    void put(K k, V v);
    V get(K k);
    void delete(K k) throws NoSuchMethodException;
    K floor(K k);
    K ceil(K k);
    boolean contains(K k);
    boolean isEmpty();
    K max();
    K min();
    int size();
    int rank(K k);
    K select(int n);
    Iterable<K> keys();
    Iterable<K> keys(int lo, int hi);
    Iterable<K> keys(K lo, K hi);
}
