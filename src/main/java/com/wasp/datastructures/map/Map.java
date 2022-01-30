package com.wasp.datastructures.map;

import java.util.Iterator;
import java.util.Set;

public interface Map<K, V> extends Iterable<Map.Entry<K, V>> {

    V put(K key, V value);

    V get(K key);

    V remove(K key);

    boolean containsKey(K key);

    int size();

    Set<Entry<K, V>> entrySet();

    Iterator<Entry<K, V>> iterator();

    class Entry<K, V> {
        K key;
        V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Entry{" +
                "key=" + key +
                ", value=" + value +
                '}';
        }
    }

}
