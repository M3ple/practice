package cn.cjx.algrithm.chapter3_datastructure;


import java.util.Iterator;
import java.util.Random;

/**
 * @功能描述:
 * @使用对象:xx系统
 * @接口版本:
 * @创建日期: 2019/6/5 16:28
 * @创建人:陈俊旋
 */
public class RBTreeMap<K extends Comparable<K>, V> implements ST<K, V> {
    private static final boolean BLACK = false;
    private static final boolean RED = true;
    private Node<K, V> root;
    private Queue<K> keys = new Queue<>();
    private int h;

    private static class Node<K, V> {
        Node<K, V> left;
        Node<K, V> right;
        boolean color;
        K k;
        V v;
        int n;

        public Node( K k, V v, boolean color,int n) {
            this.color = color;
            this.k = k;
            this.v = v;
            this.n = n;
        }
    }

    private Node<K, V> rotateRight(Node<K, V> node) {
        Node<K, V> x = node.left;
        node.left = x.right;
        x.right = node;
        // color变换
        x.color = node.color;
        node.color = RED;
        x.n = node.n;
        node.n = recaculateNum(node);
        return x;
    }

    private int recaculateNum(Node<K, V> node) {
        return size(node.left) + size(node.right) + 1;
    }

    private Node<K, V> rotateleft(Node<K, V> node) {
        Node<K, V> x = node.right;
        node.right = x.left;
        x.left = node;
        // color变换
        x.color = node.color;
        node.color = RED;
        x.n = node.n;
        node.n = recaculateNum(node);
        return x;
    }

    private int size(Node<K,V> node) {
        return node == null ? 0 : node.n;
    }

    private void flipColor(Node<K, V> node) {
        node.left.color = BLACK;
        node.right.color = BLACK;
        node.color = RED;
    }

    @Override
    public void put(K k, V v) {
        root = put(k,v,root);
    }

    private Node<K,V> put(K k, V v, Node<K,V> node) {
        if (node==null) {
            return new Node<>(k,v,RED,1);
        }
        int i = node.k.compareTo(k);
        if (i>0) {
            node.left = put(k, v, node.left);
        }else if (i<0){
            node.right = put(k, v, node.right);
        }else {
            node.v = v;
        }
        if (isRed(node.right) && !isRed(node.left)) {
            node=rotateleft(node);
        }
        if(isRed(node.left) && isRed(node.left.left)){
            node=rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)){
            flipColor(node);
        }
        node.n = recaculateNum(node);
        return node;
    }

    private boolean isRed(Node<K,V> node) {
        return node==null?false:node.color;
    }

    @Override
    public V get(K k) {
        Node<K, V> node = get(k, root);
        return node !=null?node.v:null;
    }

    private Node<K,V> get(K k, Node<K,V> node) {
        if (node==null) {
            return null;
        }
        int i = node.k.compareTo(k);
        if (i>0) {
            return get(k,node.left);
        } else if (i<0) {
            return get(k,node.right);
        } else {
            return node;
        }
    }

    @Override
    public void delete(K k) throws NoSuchMethodException {

    }

    @Override
    public K floor(K k) {
        return null;
    }

    @Override
    public K ceil(K k) {
        return null;
    }

    @Override
    public boolean contains(K k) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public K max() {
        Node<K,V> max = max(root);
        if (max==null){
            return null;
        }
        return max.k;
    }

    @Override
    public K min() {
        Node<K,V> min = min(root);
        if (min==null){
            return null;
        }
        return min.k;
    }

    public Node<K,V> max(Node<K,V> node) {
        if (node==null){
            return null;
        }
        if (node.right==null){
            return node;
        }
        return max(node.right);
    }

    public Node<K,V> min(Node<K,V> node) {
        if (node==null){
            return null;
        }
        if (node.left==null){
            return node;
        }
        return min(node.left);
    }

    @Override
    public int size() {
        return root!=null?root.n:0;
    }

    @Override
    public int rank(K k) {
        // TODO: 2020/6/5 rank方法有问题
        if (k==null){
            return -1;
        }
        Node<K, V> node = get(k, root);
        return node.left.n;
    }

    @Override
    public K select(int n) {
        return null;
    }

    private Queue<K> getKeys(Queue<K> queue,Node<K,V> node,int lo, int hi){
        if (node==null || lo==-1 || hi==-1) {
            return queue;
        }
        int rank = rank(node.k);
        if (rank<lo) {
            return getKeys(queue,node.right,lo,hi);
        } else if (rank>hi) {
            return getKeys(queue,node.left,lo,hi);
        }else {
            queue.offer(node.k);
            getKeys(queue,node.left,lo,hi);
            getKeys(queue,node.right,lo,hi);
        }
        return queue;
    }

    @Override
    public Iterable<K> keys() {
        return () -> {
            keys.clear();
            return getKeys(keys, root, rank(min()), rank(max())).iterator();
        };
    }

    @Override
    public Iterable<K> keys(int lo, int hi) {
        return () ->{
            keys.clear();
            return getKeys(keys,root,lo,hi).iterator();
        };
    }

    @Override
    public Iterable<K> keys(K lo, K hi) {
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName()+"{");
        for (K k : keys()) {
            sb.append(k == this ? "(this Collection)" : k).append(":").append(get(k)).append(",");
        }
        return sb.subSequence(0,sb.length()-1)+"}";
    }

    @Override
    public Iterator<K> iterator() {
        return keys().iterator();
    }

    public int height() {
        return height(root);
    }

    private int height(Node<K, V> node) {
        if (node==null) {
            return 0;
        }
        int left = height(node.left);
        int right = height(node.right);
        return Math.max(left,right)+1;
    }

    //-------------------seperator--------------------
    public static void main(String[] args) {
        RBTreeMap<Integer, String> st = new RBTreeMap<>();
        Random r = new Random(10);
        int limit = 50;
        for (int i = 0; i < limit; i++) {
//            st.put(k,st.get(k)!=null?st.get(k)+1:1);
            String k = "a" + i;
            st.put(r.nextInt(limit),k);
        }
//        for (int i = 0; i < st.size(); i++) {
//            System.out.println(st.get(i));
//        }
        for (Integer integer : st.keys()) {
            System.out.println(integer);
        }
        System.out.println(st.size());
        System.out.println(st);
        System.out.println(st.height());
    }
}
