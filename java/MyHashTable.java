public class MyHashTable <K, V>{
    private class HashNode <K, V> {
        private K key;
        private V value;
        private HashNode <K, V> next;

        public HashNode(K key, V value){
            this.key = key;
            this.value = value;
        }

        public String toString(){
            return "{" + key + " : " + value + "}";
        }
    }
    private HashNode<K, V>[] buckets;
    private int M = 11;
    private Double loadFactor = 0.75;
    private int size = 0;
    public MyHashTable(){
        this.buckets = new HashNode[M];
    }
    public MyHashTable(int M) {
        this.M = (int)(M * loadFactor);
        this.buckets = new HashNode[M];
    }
    private int hash(K key){
        return (key.hashCode() & 0x7fffffff) % M;
    }

    private void rehashTable(){
        M = M * 2;
        HashNode<K, V>[] temp = buckets;
        buckets = new HashNode[M];
        for(HashNode<K,V> node : temp){
            while(node != null){
                int index = hash(node.key);
                HashNode<K , V> newNode = new HashNode<>(node.key , node.value);
                newNode.next = buckets[index];
                buckets[index] = newNode;
                node = node.next;
            }
        }
    }

    public void put(K key, V value){
        if((double) size / M > loadFactor){
            rehashTable();
        }

        int index = hash(key);
        HashNode<K, V> newNode = new HashNode<>(key, value);
        newNode.next = buckets[index];
        buckets[index] = newNode;
        size++;
    }
    public V get(K key){
        int index = hash(key);
        HashNode<K, V> current = buckets[index];
        while(current != null){
            if(current.key.equals(key)){
                size ++;
                return current.value;
            }
            current = current.next;
        }
        return null;
    }
    public V remove(K key){
        int index = hash(key);
        HashNode<K, V> current = buckets[index];
        HashNode<K, V> previous = null;

        while(current != null){
            if(current.key.equals(key)){
                if(previous == null){
                    buckets[index] = current.next;
                }else{
                    previous.next = current.next;
                }
                size--;
                return current.value;
            }
            previous = current;
            current = current.next;
        }
        return null;
    }
    public boolean contains(V value){
        for (HashNode<K, V> bucket : buckets) {
            HashNode<K, V> current = bucket;
            while (current != null) {
                if (current.value.equals(value)) {
                    return true;
                }
                current = current.next;
            }
        }
        return false;
    }
    public K getKey(V value){
        for (HashNode<K, V> bucket : buckets) {
            HashNode<K, V> current = bucket;
            while (current != null) {
                if (current.value.equals(value)) {
                    return current.key;
                }
                current = current.next;
            }
        }
        return null;
    }

    public int getM() {
        return M;
    }

    public int countElements(int index){
        int count = 0;
        HashNode<K, V> temp = buckets[index];
        while(temp != null){
            count++;
            temp = temp.next;
        }
        return count;
    }
}
