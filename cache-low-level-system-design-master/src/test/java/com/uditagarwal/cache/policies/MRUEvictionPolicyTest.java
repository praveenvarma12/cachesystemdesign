package com.uditagarwal.cache.policies;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MRUEvictionPolicyTest {

    private MRUEvictionPolicy<Integer> mruEvictionPolicy;

    @BeforeEach
    void setUp() {
        mruEvictionPolicy = new MRUEvictionPolicy<>();
    }

    @Test
    void testEvictionPolicy() {
        mruEvictionPolicy.keyAccessed(1);
        mruEvictionPolicy.keyAccessed(2);
        mruEvictionPolicy.keyAccessed(3);
        
        // Access key 1, making it the most recently used
        mruEvictionPolicy.keyAccessed(1);
        
        // Evict the most recently used key (which should be 3)
        assertEquals(3, mruEvictionPolicy.evictKey());
        
        // Now, the next eviction should be 2
        assertEquals(2, mruEvictionPolicy.evictKey());
        
        // Finally, evict key 1
        assertEquals(1, mruEvictionPolicy.evictKey());
        
        // No keys left to evict
        assertNull(mruEvictionPolicy.evictKey());
    }
}