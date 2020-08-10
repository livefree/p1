package datastructures.dictionaries;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import cse332.interfaces.misc.BString;
import cse332.interfaces.trie.TrieMap;

/**
 * See cse332/interfaces/trie/TrieMap.java
 * and cse332/interfaces/misc/Dictionary.java
 * for method specifications.
 */
public class HashTrieMap<A extends Comparable<A>, K extends BString<A>, V> extends TrieMap<A, K, V> {

    public class HashTrieNode extends TrieNode<Map<A, HashTrieNode>, HashTrieNode> {
        public HashTrieNode() {
            this(null);
        }

        public HashTrieNode(V value) {
            this.pointers = new HashMap<A, HashTrieNode>();
            this.value = value;
        }

        @Override
        public Iterator<Entry<A, HashTrieMap<A, K, V>.HashTrieNode>> iterator() {
            return pointers.entrySet().iterator();
        }
    }

    public HashTrieMap(Class<K> KClass) {
        super(KClass);
        this.root = new HashTrieNode();
    }

    /**
     *
     * @param key
     *            key with which the specified value is to be associated
     * @param value
     *            value to be associated with the specified key
     * @return
     *            the previous value associated with key, or null if there was no mapping for key.
     *
     * Throws:  IllegalArgumentException if key or value is null
     */
    @Override
    public V insert(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException();
        }
        HashTrieNode curNode = (HashTrieMap<A, K, V>.HashTrieNode) root;
        V oldValue = null;
        if(key.size()==0){
            curNode.value = value;
            this.size++;
            return curNode.value;
        }
        Iterator<A> keyIterator = key.iterator();
        // travese key from root
        while(keyIterator.hasNext()) {
            A keyElement = keyIterator.next();
            if (curNode.pointers.get(keyElement) == null) {
                // put new key element to map
                curNode.pointers.put(keyElement, new HashTrieNode());
            }
            curNode = curNode.pointers.get(keyElement);
            //if hasNext == false
            //  put value in node
            if(!keyIterator.hasNext()) {
                oldValue = curNode.value;
                curNode.value = value;
            }
        }
        this.size++;
        return oldValue;
    }

    /**
     * Returns the value to which the key is mapped in this dictionary. if contains an entry for the specified key,
     * the associated value is returned; otherwise, null is returned.
     * @param key
     *            the key whose associated value is to be returned
     * @return
     *            the value to which the key is mapped in this dictionary
     */
    @Override
    public V find(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        HashTrieNode curNode = (HashTrieMap<A, K, V>.HashTrieNode) root;
        if(key.size()==0){
            return curNode.value;
        }
        Iterator<A> keyIterator = key.iterator();
        while(keyIterator.hasNext()) {
            A subKey = keyIterator.next();
            if (curNode.pointers.get(subKey) != null) {
                curNode = curNode.pointers.get(subKey);
            }else {
                return null;
            }
        }

        return curNode.value;
    }

    /**
     * Returns true if this muapr contains a mapping for which the key
     * starts with the specified key prefix.
     *
     * @param key
     *            The prefix of a key whose presence in this map is to be tested
     * @return true if this map contains a mapping whose key starts
     *         with the specified key prefix.
     * @throws IllegalArgumentException
     *             if the key is null.
     */
    @Override
    public boolean findPrefix(K key) {
        if(key == null) {
            throw new IllegalArgumentException();
        }
        boolean result = true;
        HashTrieNode curNode = (HashTrieMap<A, K, V>.HashTrieNode) this.root;
        Iterator<A> keyIterator = key.iterator();
        while(keyIterator.hasNext()) {
            A subKey = keyIterator.next();
            if (curNode.pointers.get(subKey) != null) {
                curNode = curNode.pointers.get(subKey);
            } else {
                result = false;
            }
        }
        return result;
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     *
     * @param key
     *            key whose mapping is to be removed from the map
     * @throws IllegalArgumentException
     *             if either key is null.
     */
    @Override
    public void delete(K key) {
        //exception
        if (key == null) {
            throw new IllegalArgumentException();
        }
        // test if key in map
        if(this.find(key) == null) {
            return;
        }
        HashTrieNode curNode = (HashTrieMap<A, K, V>.HashTrieNode)this.root;
        A lstKey = null;
        if(key.isEmpty()) { //empty string key
            curNode.value = null;
            this.size--;
            return;
        }
        HashTrieNode lstNode = (HashTrieMap<A, K, V>.HashTrieNode)this.root;
        Iterator<A> keyIterator = key.iterator();
        if (keyIterator.hasNext() ) { // find last node
            lstKey = keyIterator.next();
        }
        keyIterator = key.iterator();
        while (keyIterator.hasNext()) {
            A subKey = keyIterator.next();
            if (curNode == null) {
                return;
            }
            // mark multi pointer node
            if (curNode.value != null || curNode.pointers.size() > 1) {
                lstNode = curNode;
                lstKey = subKey;
            }
            if (!curNode.pointers.isEmpty()) {
                curNode = curNode.pointers.get(subKey);
            } else {
                return;
            }
        }
        if (curNode != null && curNode.value != null) {
            if (!curNode.pointers.isEmpty()) {
                curNode.value = null;
            } else if (lstKey != null) {
                lstNode.pointers.remove(lstKey);
            } else {
                this.root.value = null;
            }
            this.size--;
        }
//        // null key exception
//        if(key == null) {
//            throw new IllegalArgumentException();
//        }
//        HashTrieNode curNode = (HashTrieMap<A, K, V>.HashTrieNode)this.root;
//
//        // only root node
//        if(curNode.pointers.size() == 0){
//            if(this.find(key) != null){
//                this.root = null;
//            }
//        } else if(this.find(key) != null) {
//            deleteChildren((HashTrieNode) this.root, key);
//        }
    }



    @Override
    public void clear() {
//        throw new NotYetImplementedException();
        HashTrieNode hashTrieNode = (HashTrieMap<A, K, V>.HashTrieNode) this.root;
        hashTrieNode.pointers.clear();
        this.size = 0;
    }
}
