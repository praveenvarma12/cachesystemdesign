package com.uditagarwal;

import com.uditagarwal.cache.Cache;
import com.uditagarwal.cache.factories.CacheFactory;

public class Main {

    public static void main(String[] args) {
        // Create a cache with a capacity of 3 using the MRU eviction policy
        Cache<Integer, String> cache = new CacheFactory<Integer, String>().mruCache(3);

        // Add some items to the cache
        cache.put(1, "One");
        cache.put(2, "Two");
        cache.put(3, "Three");

        // Access some items
        System.out.println("Get 1: " + cache.get(1)); // Should return "One"
        System.out.println("Get 2: " + cache.get(2)); // Should return "Two"

        // Add another item, which should evict the most recently used item (which is 3)
        cache.put(4, "Four");

        // Now, let's see the state of the cache
        try {
            System.out.println("Get 3: " + cache.get(3)); // Should throw an exception since 3 is evicted
        } catch (Exception e) {
            System.out.println(e.getMessage()); // Handle the exception
        }

        // Accessing the remaining items
        System.out.println("Get 1: " + cache.get(1)); // Should still return "One"
        System.out.println("Get 2: " + cache.get(2)); // Should still return "Two"
        System.out.println("Get 4: " + cache.get(4)); // Should return "Four"
    }
}