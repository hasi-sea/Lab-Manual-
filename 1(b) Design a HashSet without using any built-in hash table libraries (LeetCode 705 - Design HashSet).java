import java.util.LinkedList;

class MyHashSet {
    private final int BUCKETS = 1000;
    private LinkedList<Integer>[] table;

    public MyHashSet() {
        table = new LinkedList[BUCKETS];
    }

    private int hash(int key) {
        return key % BUCKETS;
    }
    
    public void add(int key) {
        int index = hash(key);
        if (table[index] == null) {
            table[index] = new LinkedList<>();
        }
        if (!table[index].contains(key)) {
            table[index].add(key);
        }
    }
    
    public void remove(int key) {
        int index = hash(key);
        if (table[index] != null) {
            table[index].remove((Integer) key);
        }
    }
    
    public boolean contains(int key) {
        int index = hash(key);
        return table[index] != null && table[index].contains(key);
    }
}
