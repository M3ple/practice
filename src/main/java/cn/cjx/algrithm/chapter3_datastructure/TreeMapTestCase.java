package cn.cjx.algrithm.chapter3_datastructure;

import java.util.Random;

/**
 * @功能描述:
 * @使用对象:xx系统
 * @接口版本:
 * @创建日期: 2019/6/3 16:44
 * @创建人:陈俊旋
 */
public class TreeMapTestCase {
    public static void main(String[] args) {
        TreeMap<String,Integer> st = new TreeMap<>();
        Random r = new Random(10);
        for (int i = 0; i < 50; i++) {
            String k = "a" + r.nextInt(10);
            st.put(k,st.get(k)!=null?st.get(k)+1:1);
        }/*
        //-------------------seperator--------------------
        System.out.println("max:"+st.max());
        ((TreeMap<String, Integer>) st).deleteMax();
        System.out.println("max:"+st.max());
        System.out.println("min:"+st.min());
        ((TreeMap<String, Integer>) st).deleteMin();
        System.out.println("min:"+st.min());
        //-------------------seperator--------------------
        System.out.println("select:"+st.select(9));
        System.out.println("select:"+st.select(10));
        System.out.println("rank:"+st.rank("a6"));
        System.out.println("rank:"+st.rank("a0"));
        System.out.println("rank:"+st.rank("a10"));
        System.out.println("rank:"+st.rank("a."));
        //-------------------seperator--------------------
        System.out.println("floor:"+st.floor("b"));
        System.out.println("floor:"+st.floor("a10"));//a2大于a10的
        System.out.println("ceil:"+st.ceil("a-"));
        System.out.println("ceil:"+st.ceil("a5"));
        System.out.println("contains:"+st.contains("a1"));
        System.out.println("contains:"+st.contains("a."));
        //-------------------seperator--------------------
        for (String s : st.keys()) {
            System.out.println(s);
        }
        System.out.println("-------------------");
        for (String s : st.keys(1,100)) {
            System.out.println(s);
        }
        System.out.println("-------------------");
        for (String s : st.keys("a0","a3")) {
            System.out.println(s);
        }
        System.out.println("-------------------");
        System.out.println(st);*/
        //-----------------seperator----------------------
//        System.out.println("heght:"+st.height());
//        System.out.println("heght:"+st.height("a9"));
        //-----------------seperator----------------------
        try {
            st.printTree();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
class RBTreeMapTestCase{
    public static void main(String[] args) {
        RBTreeMap<String, Integer> st = new RBTreeMap<>();
        Random r = new Random(10);
        for (int i = 0; i < 50; i++) {
            String k = "a" + r.nextInt(10);
            st.put(k,st.get(k)!=null?st.get(k)+1:1);
        }
//        for (int i = 0; i < 10; i++) {
//            System.out.println(st.get("a" + i));
//        }
        try {
            st.paintTree();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        System.out.println(st);
    }
}