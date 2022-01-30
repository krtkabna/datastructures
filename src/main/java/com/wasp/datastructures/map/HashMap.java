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
    private List<Entry<K, V>>[] array;
    private Set<Entry<K, V>> entrySet = new HashSet<>();
    private int size;

    public HashMap() {
        this(DEFAULT_CAPACITY);
    }

    public HashMap(int initialCapacity) {
        array = (List<Entry<K, V>>[]) new ArrayList[initialCapacity];
        size = 0;
        for (int i = 0; i < array.length; i++) {
            array[i] = new ArrayList<>();
        }
    }


    @Override
    public V put(K key, V value) {
        V result = null;

        List<Entry<K, V>> bucket = getBucket(key);
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
        List<Entry<K, V>> bucket = getBucket(key);
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
        Iterator<Entry<K, V>> bucketIterator = getBucket(key).iterator();
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
        for (Entry<K, V> entry : getBucket(key)) {
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
        for (List<Entry<K, V>> bucket : array) {
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
            nextCalled = true;
            return bucketIterator.next();
        }

        @Override
        public void remove() {
            if (!nextCalled) {
                throw new DataStructureIteratorException("Calling remove() before calling next()");
            }
            bucketIterator.remove();
            size--;
            nextCalled = false;
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

    private List<Entry<K, V>> getBucket(K key) {
        if (key != null) {
            return array[Math.abs(key.hashCode()) % array.length];
        } else return array[0];
    }
}
