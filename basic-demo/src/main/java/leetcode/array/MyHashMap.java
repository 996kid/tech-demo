package leetcode.array;

import lombok.Getter;

import java.util.LinkedList;

/**
 * 不使用任何内建的哈希表库设计一个哈希映射（HashMap）。
 *
 * 实现 MyHashMap 类：
 *
 * MyHashMap() 用空映射初始化对象
 * void put(int key, int value) 向 HashMap 插入一个键值对 (key, value) 。如果 key 已经存在于映射中，则更新其对应的值 value 。
 * int get(int key) 返回特定的 key 所映射的 value ；如果映射中不包含 key 的映射，返回 -1 。
 * void remove(key) 如果映射中存在 key 的映射，则移除 key 和它所对应的 value 。
 */

//<key, value>
public class MyHashMap {

    private LinkedList<Node>[] arr;

    public MyHashMap() {
        arr = new LinkedList[679];
    }
    
    public void put(int key, int value) {
        int index = hash(key);
        Node node = new Node(key, value);
        if (arr[index] != null) {
            LinkedList<Node> linkedList = arr[index];
            linkedList.addLast(node);
        } else {
            LinkedList<Node> linkedList = new LinkedList<>();
            linkedList.addLast(node);
            arr[key] = linkedList;
        }
    }
    
    public int get(int key) {
        int index = hash(key);
        // 遍历联表
        return 0;
    }
    
    public void remove(int key) {
        //
    }

    private int hash(int key) {
        return key % arr.length;
    }

    @Getter
    class Node {
        private int key;

        private int value;

        public Node(int key, int value) {
            this.key = key;

            this.value = value;
        }
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */