package com.wasp.datastructures.map;

import com.wasp.datastructures.exception.DataStructureIteratorException;
import com.wasp.datastructures.list.ArrayList;
import com.wasp.datastructures.list.List;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

public class HashMap<K, V> implements Map<K, V> {
    private static final int DEFAULT_CAPACITY = 10;
    private ArrayList<Entry<K, V>>[] array;
    private Set<Entry<K, V>> entrySet = new HashSet<>();
    private int size;

    public HashMap() {
        this(DEFAULT_CAPACITY);
    }

    public HashMap(int initialCapacity) {
        array = (ArrayList<Entry<K, V>>[]) new ArrayList[initialCapacity];
        size = 0;
        for (int i = 0; i < array.length; i++) {
            array[i] = new ArrayList<>();
        }
    }

    @Override
    public V put(K key, V value) {
        int i = getBucketIndex(key);
        V result = null;

        List<Entry<K, V>> bucket = array[i];
        if (bucket.isEmpty()) {
            bucket.add(new Entry<>(key, value));
            size++;
        } else {
            for (Entry<K, V> entry : bucket) {
                if (key.equals(entry.key)) {
                    result = entry.value;
                    entry.value = value;
                }
            }
            if (result == null) {
                Entry<K, V> newEntry = new Entry<>(key, value);
                bucket.add(newEntry);
                size++;
            }
        }
        return result;
    }

    @Override
    public V get(K key) {
        int i = getBucketIndex(key);
        List<Entry<K, V>> bucket = array[i];
        V result = null;

        if (!bucket.isEmpty()) {
            for (Entry<K, V> entry : bucket) {
                if (key.equals(entry.key)) {
                    result = entry.value;
                }
            }
        }
        return result;
    }

    @Override
    public V remove(K key) {
        int i = getBucketIndex(key);
        Iterator<Entry<K, V>> bucketIterator = array[i].iterator();
        V result = null;
        while (bucketIterator.hasNext()) {
            Entry<K, V> entry = bucketIterator.next();
            if (key.equals(entry.key)) {
                result = entry.value;
                bucketIterator.remove();
                size--;
            }
        }

        return result;
    }

    @Override
    public boolean containsKey(K key) {
        int i = getBucketIndex(key);
        for (Entry<K, V> entry : array[i]) {
            if (Objects.equals(entry.key, key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        for (ArrayList<Entry<K, V>> bucket : array) {
            for (Entry<K, V> entry : bucket) {
                entrySet.add(entry);
            }
        }
        return entrySet;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new HashMapIterator();
    }

    private int getBucketIndex(K key) {
        if (key != null) {
            return Math.abs(key.hashCode()) % array.length;
        } else return 0;
    }

    private class HashMapIterator implements Iterator<Entry<K, V>> {
        private boolean nextCalled = false;
        private int bucketIndex = -1;
        Iterator<Entry<K, V>> bucketIterator = null;

        @Override
        public boolean hasNext() {
            if (bucketIterator == null || !bucketIterator.hasNext()) {
                return nextBucket();
            } else {
                return true;
            }
        }

        @Override
        public Entry<K, V> next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No buckets left in map");
            }
            return bucketIterator.next();
        }

        @Override
        public void remove() {
            if (!nextCalled) {
                throw new DataStructureIteratorException("Calling remove() before calling next()");
            }
            bucketIterator.remove();
            size--;
        }

        private boolean nextBucket() {
            do {
                if (++bucketIndex >= array.length) {
                    return false;
                }
                bucketIterator = array[bucketIndex].iterator();
            } while (!bucketIterator.hasNext());
            return true;
        }
    }
}
