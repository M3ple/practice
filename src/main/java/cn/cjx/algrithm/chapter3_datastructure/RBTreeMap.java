package cn.cjx.algrithm.chapter3_datastructure;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

//    private Node<K,V> rotateRight(Node<K,V> node){
//        Node<K,V> x = node.left;
//        x.left = node;
//        node.left=x.right;
//        node.color =RED;
//        x.right = node;
//        x.color = BLACK;
//        return x;
//    }
//
//    private Node<K,V> rotateleft(Node<K,V> node){
//        Node<K, V> x = node.left.right;
//        node.left.right = x.left;
//        x.left=node.left;
//        node.left =x;
//        return node;
//    }

    private Node<K, V> rotateRight(Node<K, V> node) {
        Node<K, V> x = node.left;
        node.left = x.right;
        x.right = node;
        x.color = node.color;
        node.color = RED;
        x.n = node.n;
        node.n = size(node.left)+size(node.right)+1;
        return x;
    }

    private Node<K, V> rotateleft(Node<K, V> node) {
        Node<K, V> x = node.right;
        node.right = x.left;
        x.left = node;
        x.color = node.color;
        node.color = RED;
        x.n = node.n;
        node.n = size(node.left)+size(node.right)+1;
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
        if (node==null)
            return new Node<K,V>(k,v,RED,1);
        int i = node.k.compareTo(k);
        if (i>0) {
            node.left = put(k, v, node.left);
        }else if (i<0){
            node.right = put(k, v, node.right);
        }else {
            node.v = v;
        }
        if (isRed(node.right) && !isRed(node.left))
            node=rotateleft(node);
        if(isRed(node.left) && isRed(node.left.left))
            node=rotateRight(node);
        if (isRed(node.left) && isRed(node.right))
            flipColor(node);
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
        if (node==null)
            return null;
        int i = node.k.compareTo(k);
        if (i>0)
            return get(k,node.left);
        else if (i<0)
            return get(k,node.right);
        else
            return node;
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
        return null;
    }

    @Override
    public K min() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public int rank(K k) {
        return 0;
    }

    @Override
    public K select(int n) {
        return null;
    }


    private Queue<K> getKeys(Queue<K> queue,Node<K,V> node,int lo, int hi){
        if (node==null)
            return queue;
        int rank = rank(node.k);
        if (rank<lo)
            return getKeys(queue,node.right,lo,hi);
        else if (rank>hi)
            return getKeys(queue,node.left,lo,hi);
        getKeys(queue,node.left,lo,hi);
        queue.offer(node.k);
        getKeys(queue,node.right,lo,hi);
        return queue;
    }

    private Queue<K> getKeys(Queue<K> queue,Node<K,V> node,K lo, K hi){
        if (node==null || lo.compareTo(hi)>0)
            return queue;
        int i = node.k.compareTo(lo);
        int j = node.k.compareTo(hi);
        if (i<0)
            return getKeys(queue,node.right,lo,hi);
        else if (j>0)
            return getKeys(queue,node.left,lo,hi);
        getKeys(queue,node.left,lo,hi);
        queue.offer(node.k);
        getKeys(queue,node.right,lo,hi);
        return queue;
    }

    @Override
    public Iterable<K> keys() {
        return new Iterable<K>() {
            @Override
            public Iterator<K> iterator() {
                keys.clear();
                return getKeys(keys,root,rank(min()),rank(max())).iterator();
            }
        };
    }

    @Override
    public Iterable<K> keys(int lo, int hi) {
        return new Iterable<K>() {
            @Override
            public Iterator<K> iterator() {
                keys.clear();
                return getKeys(keys,root,lo,hi).iterator();
            }
        };
    }

    @Override
    public Iterable<K> keys(K lo, K hi) {
        return new Iterable<K>() {
            @Override
            public Iterator<K> iterator() {
                keys.clear();
                return getKeys(keys,root,lo,hi).iterator();
            }
        };
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
        return null;
    }
    public void paintTree() throws CloneNotSupportedException {
        printNode(root);
    }

    //-------------------打印树形状--------------------
    private void printNode(Node<K, V> node) throws CloneNotSupportedException {
        if (node == null)
            return;
        ;
        Queue<Node<K, V>> layer = new Queue<>();
        layer.offer(node);
        List<Queue<Node<K, V>>> queues = new ArrayList<>();
        queues.add(layer);
        getLayer((Queue<Node<K, V>>) layer.clone(), queues);
        printTree(queues);
    }

    private void printTree(List<Queue<Node<K, V>>> queues) {
        int height = height();
        int layer = 1;
        if (queues.size()!= height)
            throw new IllegalStateException("深度不一致");
        h = height;
        for (Queue<Node<K, V>> queue : queues) {
            //某一层的起始偏移量
            String headBlanksOffset= getSpaces(getOffset(layer));
            String intervalSpaces = getSpaces(interval(layer));
            StringBuilder sb = new StringBuilder();
            int count = 1;
            //某一层
            sb.append(headBlanksOffset);
            for (Iterator<Node<K, V>> iterator = queue.iterator(); iterator.hasNext(); ) {
                Node<K, V> n = iterator.next();
                if (layer==height){
                    if (count%2==0){
                        sb.append(n != null ? n.k : "--").append("  ");
                    }else {
                        sb.append(n != null ? n.k : "--").append(intervalSpaces);
                    }
                    count++;
                }else {
                    sb.append(n != null ? (n.color?("<"+n.k+">"):n.k.toString()): "--").append(intervalSpaces);
                }
            }
            System.out.println(sb.toString());
            layer++;
        }
    }

    private int height() {
        return height(root);
    }

    private int height(Node<K, V> node) {
        if (node==null)
            return 0;
        int left = height(node.left);
        int right = height(node.right);
        return Math.max(left,right)+1;
    }

    private int getOffset(int layer) {
        if(layer>h)
            return 0;
        int layerN = ++layer;
        int interval = interval(layerN);
        int i = (interval + 1) / 2;
        int offset = getOffset(layerN);
        return offset+i;
    }

    //获取指定层数的左右分支间隔数
    private int interval(int layer) {
        return layer>h?0:(2*interval(++layer)+1);
    }

    //获取指定个数的"  "
    public String getSpaces(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append("  ");
        }
        return sb.toString();
    }

    private void getLayer(Queue<Node<K, V>> layer, List<Queue<Node<K, V>>> queues) throws CloneNotSupportedException {
        boolean flag = true;
        for (Iterator<Node<K, V>> iterator = layer.iterator(); iterator.hasNext(); ) {
            Node<K, V> next = iterator.next();
            if (next != null)
                flag = false;
        }
        if (flag) {
            queues.remove(queues.size() - 1);
            return;
        }
        Queue<Node<K, V>> nextLayer = new Queue<>();
        while (!layer.isEmpty()) {
            Node<K, V> head = layer.poll();
            if (head != null && head.left != null)
                nextLayer.offer(head.left);
            else
                nextLayer.offer(null);
            if (head != null && head.right != null)
                nextLayer.offer(head.right);
            else
                nextLayer.offer(null);
        }
        queues.add(nextLayer);
        getLayer((Queue<Node<K, V>>) nextLayer.clone(), queues);
    }
    //-------------------seperator--------------------
}
