package com.wasp.datastructures.map;

import java.util.Iterator;
import java.util.Objects;
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
        private K key;
        private V value;

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Entry)) return false;
            Entry<?, ?> entry = (Entry<?, ?>) o;
            return Objects.equals(getKey(), entry.getKey()) && Objects.equals(getValue(), entry.getValue());
        }
    }

}
