package cn.cjx.think_in_java.chapter17_collection;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * @功能描述:
 * @使用对象:xx系统
 * @接口版本:
 * @创建日期: 2019/5/24 16:20
 * @创建人:陈俊旋
 */
public class QueueDemo {
    public static void main(String[] args) {
        PriorityQueue<IntegerElement> queue = new PriorityQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.add(new IntegerElement());
        }
        System.out.println(queue);//打印和取出来的位置不一样
        while (!queue.isEmpty())
            System.out.println(queue.remove());//打印和取出来的位置不一样
    }
}

class IntegerElement implements Comparable<IntegerElement>{
    static Random r = new Random();
    public int id =r.nextInt(100);

    @Override
    public int compareTo(IntegerElement e) {
        return this.id<e.id?-1:(this.id==e.id?0:1);
    }

    @Override
    public String toString() {
        return id+"";
    }
}

class WordsCounter{
    private String[] words;
    private Map<String,Integer> map = new HashMap<>();

    public WordsCounter(String words) {
        this.words = words.split(" ");
    }

    public int count(String key){
        if (key==null)
            return 0;
        else if (map.containsKey(key))
            return map.get(key);
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(key))
                if (!map.containsKey(key))
                    map.put(key,1);
                else
                    map.put(key,map.get(key)+1);
        }
        return map.containsKey(key)?map.get(key):0;
    }

    public void countAll(){
        for (int i = 0; i < words.length; i++) {
            if (!map.containsKey(words[i]))
                map.put(words[i],1);
            else if (words[i].equals(words[i]))
                map.put(words[i],map.get(words[i])+1);
        }
    }

    @Override
    public String toString() {
        return map.toString();
    }

    public static void main(String[] args) {
        System.out.println("C".equals("c"));
        WordsCounter wordsCounter = new WordsCounter("a a b b b b b b c c c c c cc c");
//        wordsCounter.countAll();
//        System.out.println(wordsCounter);
        System.out.println(wordsCounter.count("a"));
        System.out.println(wordsCounter.count("b"));
        System.out.println(wordsCounter.count("c"));
        System.out.println(wordsCounter.count("C"));
    }
}
