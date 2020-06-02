
package cn.cjx.algrithm.chapter3_datastructure;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @功能描述:
 * @使用对象:xx系统
 * @接口版本:
 * @创建日期: 2019/6/3 16:24
 * @创建人:陈俊旋
 */
public class TreeMap<K extends Comparable<K>, V> implements ST<K, V> {
    private Node<K, V> root;
    private Queue<K> keys = new Queue<>();
    private int h = height();

    @Override
    public void put(K k, V v) {
        root = put(root, k, v);
    }

    private Node<K, V> put(Node<K, V> node, K k, V v) {
        if (node == null) {
            return new Node<>(k, v, 1);
        }
        int i = node.k.compareTo(k);
        if (i == 0) {
            node.v = v;
        } else if (i > 0) {
            node.left = put(node.left, k, v);
        } else {
            node.right = put(node.right, k, v);
        }
        node.n = size(node.left) + size(node.right) + 1;
        return node;
    }

    @Override
    public V get(K k) {
        return get(root, k);
    }

    private V get(Node<K, V> node, K k) {
        if (node == null) {
            return null;
        }
        int i = node.k.compareTo(k);
        if (i == 0) {
            return node.v;
        } else if (i > 0) {
            return get(node.left, k);
        } else {
            return get(node.right, k);
        }
    }

    @Override
    public void delete(K k) throws NoSuchMethodException {
        delete(root, k);
    }

    private void delete(Node<K, V> node, K k) throws NoSuchMethodException {
        Node<K, V> node1 = findNode(node, k);
        if (node1 == null)
            throw new NoSuchMethodException();
        Node<K, V> min = min(node.right);
        min.left = node1.left;
        int rank = rank(k);
        Node<K, V> father = findNode(node, select(rank - 1));// TODO: 2019/6/4
        father.right = node1.right;
    }

    private Node<K, V> findNode(Node<K, V> node, K k) {
        if (node == null)
            return null;
        int i = node.k.compareTo(k);
        if (i > 0) {
            return findNode(node.left, k);
        } else if (i < 0) {
            return findNode(node.right, k);
        } else {
            return node;
        }
    }

    public void deleteMax() {
        deleteMax(root);
    }

    private Node<K, V> deleteMax(Node<K, V> node) {
        if (node == null)
            return null;
        if (node.right == null)
            return node.left;
        else {
            node.right = deleteMax(node.right);
            return node;
        }
    }

    public void deleteMin() {
        deleteMin(root);
    }

    private Node<K, V> deleteMin(Node<K, V> node) {
        if (node == null)
            return null;
        if (node.left == null)
            return node.right;
        else {
            node.left = deleteMin(node.left);
            return node;
        }
    }

    @Override
    public K floor(K k) {
        return floor(root, k) != null ? floor(root, k).k : null;
    }

    private Node<K, V> floor(Node<K, V> node, K k) {
        if (node == null)
            return null;
        int i = node.k.compareTo(k);
        if (i == 0)
            return node;
        else if (i > 0)
            return floor(node.left, k);
        Node<K, V> floor = floor(node.right, k);
        if (floor == null)
            return node;
        else
            return floor;
    }

    @Override
    public K ceil(K k) {
        return ceil(root, k) != null ? ceil(root, k).k : max();
    }

    private Node<K, V> ceil(Node<K, V> node, K k) {
        if (node == null)
            return null;
        int i = node.k.compareTo(k);
        if (i == 0)
            return node;
        else if (i < 0)
            return ceil(node.right, k);
        Node<K, V> ceil = ceil(node.left, k);
        if (ceil == null)
            return node;
        else
            return ceil;
    }

    @Override
    public boolean contains(K k) {
        return contains(root, k) != null;
    }

    private Node<K, V> contains(Node<K, V> node, K k) {
        if (node == null)
            return null;
        int i = node.k.compareTo(k);
        if (i == 0)
            return node;
        else if (i > 0)
            return contains(node.left, k);
        else
            return contains(node.right, k);
    }

    @Override
    public boolean isEmpty() {
        return size(root) == 0;
    }

    @Override
    public K max() {
        if (root == null)
            return null;
        Node<K, V> node = root;
        while (node.right != null) {
            node = node.right;
        }
        return node.k;
    }

    private Node<K, V> min(Node<K, V> node) {
        if (node == null)
            return null;
        return null;
    }

    @Override
    public K min() {
        if (root == null)
            return null;
        Node<K, V> node = root;
        while (node.left != null) {
            node = node.left;
        }
        return node.k;
    }

    @Override
    public int size() {
        return size(root);
    }

    public int size(Node<K, V> node) {
        return node == null ? 0 : node.n;
    }

    @Override
    public int rank(K k) {
        if (contains(k))
            return rank(root, k);
        else
            return -1;
    }

    private int rank(Node<K, V> node, K k) {
        if (node == null)
            return 0;
        int i = node.k.compareTo(k);
        if (i == 0)
            return size(node.left) + 1;
        else if (i > 0) {
            return rank(node.left, k);
        } else {
            return size(node.left) + 1 + rank(node.right, k);
        }
    }

    @Override
    public K select(int n) {
        return select(root, n) != null ? select(root, n).k : null;
    }

    private Node<K, V> select(Node<K, V> node, int n) {
        if (node == null)
            return null;
        int size = size(node.left);
        if (size > n)
            return select(node.left, n);
        else if (size < n) {
            if ((size + 1) == n)
                return node;
            return select(node.right, n - size - 1);
        } else {
            return node.left;
        }
    }

    private Queue<K> getKeys(Queue<K> queue, Node<K, V> node, int lo, int hi) {
        if (node == null)
            return queue;
        int rank = rank(node.k);
        if (rank < lo)
            return getKeys(queue, node.right, lo, hi);
        else if (rank > hi)
            return getKeys(queue, node.left, lo, hi);
        getKeys(queue, node.left, lo, hi);
        queue.offer(node.k);
        getKeys(queue, node.right, lo, hi);
        return queue;
    }

    private Queue<K> getKeys(Queue<K> queue, Node<K, V> node, K lo, K hi) {
        if (node == null || lo.compareTo(hi) > 0)
            return queue;
        int i = node.k.compareTo(lo);
        int j = node.k.compareTo(hi);
        if (i < 0)
            return getKeys(queue, node.right, lo, hi);
        else if (j > 0)
            return getKeys(queue, node.left, lo, hi);
        getKeys(queue, node.left, lo, hi);
        queue.offer(node.k);
        getKeys(queue, node.right, lo, hi);
        return queue;
    }

    @Override
    public Iterable<K> keys() {
        return new Iterable<K>() {
            @Override
            public Iterator<K> iterator() {
                keys.clear();
                return getKeys(keys, root, rank(min()), rank(max())).iterator();
            }
        };
    }

    @Override
    public Iterable<K> keys(int lo, int hi) {
        return new Iterable<K>() {
            @Override
            public Iterator<K> iterator() {
                keys.clear();
                return getKeys(keys, root, lo, hi).iterator();
            }
        };
    }

    @Override
    public Iterable<K> keys(K lo, K hi) {
        return new Iterable<K>() {
            @Override
            public Iterator<K> iterator() {
                keys.clear();
                return getKeys(keys, root, lo, hi).iterator();
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName() + "{");
        for (K k : keys()) {
            sb.append(k == this ? "(this Collection)" : k).append(":").append(get(k)).append(",");
        }
        return sb.subSequence(0, sb.length() - 1) + "}";
    }

    @Override
    public Iterator<K> iterator() {
        return null;
    }

    private class Node<K, V> {
        Node<K, V> left;
        Node<K, V> right;
        K k;
        V v;
        int n;

        public Node(K k, V v, int n) {
            this.k = k;
            this.v = v;
            this.n = n;
        }
    }

    public int height() {
        return heigthtByRecursion(root);
//        return heigthtByQueue(root);
    }


    public int height(K k) {
        Node<K, V> node = findNode(root, k);
        return heigthtByRecursion(node);
    }

    //递归算法
    private int heigthtByRecursion(Node<K, V> node) {
        if (node == null)
            return 0;
        int left = heigthtByRecursion(node.left);
        int right = heigthtByRecursion(node.right);
        return Math.max(left, right) + 1;
    }

    //非递归
    private int heigthtByQueue(Node<K, V> node) {
        if (node == null)
            return 0;
        int height = 0;
        Queue<Node<K, V>> queue = new Queue<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            Node<K, V> head = queue.poll();
            System.out.println(head.k);
            height++;
            if (head.left != null)
                queue.offer(head.left);
            if (head.right != null)
                queue.offer(head.right);
        }
        return height;
    }

    public void printTree() throws CloneNotSupportedException {
        Queue<Node<K, V>> queue = new Queue<>();
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
                    sb.append(n != null ? n.k : "--").append(intervalSpaces);
                }
            }
            System.out.println(sb.toString());
            layer++;
        }
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
    public static void main(String[] args) {
        Queue<Object> nextLayer = new Queue<>();
        nextLayer.offer(null);
        System.out.println(nextLayer);
        System.out.println(nextLayer.poll() == null);
        System.out.println(nextLayer.isEmpty());
    }
}
