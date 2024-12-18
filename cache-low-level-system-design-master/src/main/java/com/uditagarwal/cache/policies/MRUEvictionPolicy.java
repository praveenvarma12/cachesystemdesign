package com.uditagarwal.cache.policies;

import com.uditagarwal.algoritms.DoublyLinkedList;
import com.uditagarwal.algoritms.DoublyLinkedListNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Eviction policy based on MRU algorithm.
 *
 * @param <Key> Key type.
 */
public class MRUEvictionPolicy<Key> implements EvictionPolicy<Key> {

    private DoublyLinkedList<Key> dll;
    private Map<Key, DoublyLinkedListNode<Key>> mapper;

    public MRUEvictionPolicy() {
        this.dll = new DoublyLinkedList<>();
        this.mapper = new HashMap<>();
    }

    @Override
    public void keyAccessed(Key key) {
        if (mapper.containsKey(key)) {
            dll.detachNode(mapper.get(key));
            dll.addNodeAtLast(mapper.get(key)); // Move to the end to mark as most recently used
        } else {
            DoublyLinkedListNode<Key> newNode = dll.addElementAtLast(key);
            mapper.put(key, newNode);
        }
    }

    @Override
    public Key evictKey() {
        DoublyLinkedListNode<Key> last = dll.getLastNode(); // Get the most recently used node
        if (last == null) {
            return null;
        }
        dll.detachNode(last);
        return last.getElement();
    }
}