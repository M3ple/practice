package cn.cjx.think_in_java.chapter17_collection;

import java.util.TreeSet;

/**
 * @功能描述:
 * @使用对象:xx系统
 * @接口版本:
 * @创建日期: 2019/5/25 15:55
 * @创建人:陈俊旋
 */
public class HashCodeDemo {

    public static void main(String[] args) {
        TreeSet<Pet> set = new TreeSet<>();
        set.add(new Pet("Jack"));
        set.add(new Pet("Rose"));
        set.add(new Pet("Harry"));
//        set.add(new Pet("H"));
//        set.add(new Pet("R"));
//        set.add(new Pet("J"));
        System.out.println(set);
//        Arrays.binarySearch()//二分查找法
    }
}
class Pet implements Comparable<Pet>{
    private static long count = 0;
    private static final long id = count++;
    private String name;

    public Pet(String name) {
        this.name = name;
    }

    public Pet() {
    }


    @Override
    public String toString() {
        return "Pet{" +
                "id='" + id + '\'' +
                ",name='" + name + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int compareTo(Pet p) {
        if (p==null) {
            return 1;
        }
        String i = this.getClass().getSimpleName();
        String j = p.getClass().getSimpleName();
        if (!i.equals(j)){
            return i.compareTo(j);
        }else {
            if (this.id!=p.id){
                return this.id<p.id?-1:(this.id==p.id?0:1);
            }else {
                return this.name.compareTo(p.name);
            }
        }
    }
}
