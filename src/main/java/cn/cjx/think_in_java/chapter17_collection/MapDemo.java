package cn.cjx.think_in_java.chapter17_collection;

import java.util.*;

/**
 * @功能描述:
 * @使用对象:xx系统
 * @接口版本:
 * @创建日期: 2019/5/23 13:32
 * @创建人:陈俊旋
 */
//享元模式
public class MapDemo {
    private final static String[][] DATA = {
            {"china", "beijing"},
            {"America", "washington"}
    };

    private static class MyMap extends AbstractMap<String, String> {
        private static class MyEntry implements Entry<String, String> {

            int index;

            public MyEntry(int index) {
                this.index = index;
            }

            @Override
            public String getKey() {
                return DATA[index][0];
            }

            @Override
            public String getValue() {
                return DATA[index][1];
            }

            @Override
            public String setValue(String value) {
                return DATA[index][1] = value;
            }

            @Override
            public boolean equals(Object o) {
                return DATA[index][0].equals(o);
            }

            @Override
            public int hashCode() {
                return DATA[index][0].hashCode();
            }
        }

        static class EntrySet extends AbstractSet<Entry<String, String>> {
            int size;

            public EntrySet(int size) {
                if (size < 0) {
                    this.size = 0;
                } else if (size > DATA.length) {
                    this.size = DATA.length;
                } else {
                    this.size = size;
                }
            }

            @Override
            public Iterator<Entry<String, String>> iterator() {
                return new Iter();
            }

            private class Iter implements Iterator {
                MyEntry entry = new MyEntry(-1);//只记录一个Entry通过索引的位置变化来读取数据

                @Override
                public boolean hasNext() {
                    return entry.index < size - 1;
                }

                @Override
                public Object next() {
                    entry.index++;
                    return entry;
                }

                @Override
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            }

            @Override
            public int size() {
                return size;
            }
        }

        private Set<Entry<String, String>> entries = new EntrySet(DATA.length);//存储所有元数据的索引

        @Override
        public Set<Entry<String, String>> entrySet() {
            return entries;
        }
    }

    public static Map<String, String> select(final int size) {
        return new MyMap() {
            public Set<Entry<String, String>> entrySet() {
                return new EntrySet(size);
            }
        };
    }

    static Map<String, String> map = new MyMap();

    public static Map<String, String> capitals() {
        return map; // The entire map
    }

    public static Map<String, String> capitals(int size) {
        return select(size); // A partial map
    }

    static List<String> names =
            new ArrayList<String>(map.keySet());

    // All the names:
    public static List<String> names() {
        return names;
    }

    // A partial list:
    public static List<String> names(int size) {
        return new ArrayList<String>(select(size).keySet());
    }

}

class Countries {
    public static final String[][] DATA = {
            // Africa
            {"ALGERIA", "Algiers"}, {"ANGOLA", "Luanda"}
    };

    // Use AbstractMap by implementing entrySet()
    private static class FlyweightMap
            extends AbstractMap<String, String> {
        private static class Entry
                implements Map.Entry<String, String> {
            int index;

            Entry(int index) {
                this.index = index;
            }

            public boolean equals(Object o) {
                return DATA[index][0].equals(o);
            }

            public String getKey() {
                return DATA[index][0];
            }

            public String getValue() {
                return DATA[index][1];
            }

            public String setValue(String value) {
                throw new UnsupportedOperationException();
            }

            public int hashCode() {
                return DATA[index][0].hashCode();
            }
        }

        // Use AbstractSet by implementing size() & iterator()
        static class EntrySet
                extends AbstractSet<Map.Entry<String, String>> {
            private int size;

            EntrySet(int size) {
                if (size < 0)
                    this.size = 0;
                    // Can't be any bigger than the array:
                else if (size > DATA.length)
                    this.size = DATA.length;
                else
                    this.size = size;
            }

            public int size() {
                return size;
            }

            private class Iter
                    implements Iterator<Map.Entry<String, String>> {
                // Only one Entry object per Iterator:
                private Entry entry = new Entry(-1);

                public boolean hasNext() {
                    return entry.index < size - 1;
                }

                public Map.Entry<String, String> next() {
                    entry.index++;
                    return entry;
                }

                public void remove() {
                    throw new UnsupportedOperationException();
                }
            }

            public Iterator<Map.Entry<String, String>> iterator() {
                return new Iter();
            }
        }

        private static Set<Map.Entry<String, String>> entries =
                new EntrySet(DATA.length);

        public Set<Map.Entry<String, String>> entrySet() {
            return entries;
        }
    }

    // Create a partial map of 'size' countries:
    static Map<String, String> select(final int size) {
        return new FlyweightMap() {
            public Set<Map.Entry<String, String>> entrySet() {
                return new EntrySet(size);
            }
        };
    }

    static Map<String, String> map = new FlyweightMap();

    public static Map<String, String> capitals() {
        return map; // The entire map
    }

    public static Map<String, String> capitals(int size) {
        return select(size); // A partial map
    }

    static List<String> names =
            new ArrayList<String>(map.keySet());

    // All the names:
    public static List<String> names() {
        return names;
    }

    // A partial list:
    public static List<String> names(int size) {
        return new ArrayList<String>(select(size).keySet());
    }

    public static void main(String[] args) {
        System.out.println(capitals(10));
        System.out.println(names(10));
        System.out.println(new HashMap<String, String>(capitals(3)));
        System.out.println(new LinkedHashMap<String, String>(capitals(3)));
        System.out.println(new TreeMap<String, String>(capitals(3)));
        System.out.println(new Hashtable<String, String>(capitals(3)));
        System.out.println(new HashSet<String>(names(6)));
        System.out.println(new LinkedHashSet<String>(names(6)));
        System.out.println(new TreeSet<String>(names(6)));
        System.out.println(new ArrayList<String>(names(6)));
        System.out.println(new LinkedList<String>(names(6)));
        System.out.println(capitals().get("BRAZIL"));
    }
}

class Demo {
    public static void run() {
        List empNumberList = null;
        //运算优先级
        System.out.println("a" + empNumberList != null ? empNumberList.size() : "牛逼轰轰ALL");
    }

    public static void go() {
        LinkedHashMap<Integer, String> map = new LinkedHashMap<>(16, 0.75f, true);
        for (int i = 0; i < 10; i++) {
            map.put(i, "A" + i + "");
        }
        System.out.println(map);
        //使用LRU算法，取出来的元素会被放置在前面
        for (int i = 0; i < 5; i++) {
            map.get(i);
        }
        System.out.println(map);
    }

    public static void main(String[] args) {
        go();
//        String a = new String("a");
//        String b = "a";
//        String c = "a";
//        System.out.println(a==b);
//        System.out.println(c==b);
    }
}
