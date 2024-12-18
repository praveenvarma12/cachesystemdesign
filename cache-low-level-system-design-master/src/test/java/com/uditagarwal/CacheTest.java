package com.uditagarwal;

import com.uditagarwal.cache.Cache;
import com.uditagarwal.cache.factories.CacheFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author neeraj on 26/07/20
 * Copyright (c) 2019, lld-cache.
 * All rights reserved.
 */
public class CacheTest {

    Cache<Integer, Integer> cache;

    @BeforeEach
    public void setup() {
        // Use the MRU cache instead of the LRU cache
        cache = new CacheFactory<Integer, Integer>().mruCache(3);
    }

    @Test
    public void itShouldBeAbleToGetAndAddItemsInTheCache() {
        cache.put(1, 1);
        cache.put(2, 2);

        assertEquals(1, cache.get(1)); // Accessing 1 after 2 got inserted

        cache.put(3, 3);
        assertEquals(3, cache.get(3));

        // Now if I try to add any element, the eviction should happen
        // Eviction should happen based on Most Recently Used item
        // which is 3 in this case since it was just accessed.
        cache.put(4, 4);

        // After adding 4, the most recently used item (3) should be evicted
        // So accessing 3 should throw an exception
        assertThrows(Exception.class, () -> cache.get(3), "Tried to access non-existing key.");
    }
}