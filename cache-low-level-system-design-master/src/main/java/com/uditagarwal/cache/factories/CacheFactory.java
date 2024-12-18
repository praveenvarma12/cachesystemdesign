package com.uditagarwal.cache.factories;

import com.uditagarwal.cache.Cache;
import com.uditagarwal.cache.policies.LRUEvictionPolicy;
import com.uditagarwal.cache.policies.MRUEvictionPolicy; // Import MRU policy
import com.uditagarwal.cache.storage.HashMapBasedStorage;

public class CacheFactory<Key, Value> {

    public Cache<Key, Value> defaultCache(final int capacity) {
        return new Cache<Key, Value>(new LRUEvictionPolicy<Key>(),
                new HashMapBasedStorage<Key, Value>(capacity));
    }

    // New method to create a cache with MRU eviction policy
    public Cache<Key, Value> mruCache(final int capacity) {
        return new Cache<Key, Value>(new MRUEvictionPolicy<Key>(),
                new HashMapBasedStorage<Key, Value>(capacity));
    }
}