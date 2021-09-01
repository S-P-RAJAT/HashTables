package com.bridgelabz.hashtables;

import java.util.ArrayList;

import com.bridgelabz.linkedlist.MyLinkedList;

public class MyLinkedHashMap<K, V> {

	private final int numBuckets;
	ArrayList<MyLinkedList<K>> myBucketArray;

	public MyLinkedHashMap() {

		this.numBuckets = 10;
		this.myBucketArray = new ArrayList<>(numBuckets);

		for (int index = 0; index < numBuckets; index++) {

			this.myBucketArray.add(null);
		}
	}

	public V get(K key) {

		int index = this.getBucketIndex(key);
		MyLinkedList<K> myLinkedList = this.myBucketArray.get(index);
		if (myLinkedList == null)
			return null;
		MyMapNode<K, V> myMapNode = (MyMapNode<K, V>) myLinkedList.search(key);
		return (myMapNode == null) ? null : myMapNode.getValue();

	}

	public void add(K key, V value) {

		int index = this.getBucketIndex(key);
		MyLinkedList<K> myLinkedList = this.myBucketArray.get(index);
		if (myLinkedList == null) {

			myLinkedList = new MyLinkedList<>();
			this.myBucketArray.set(index, myLinkedList);
		}

		MyMapNode<K, V> myMapNode = (MyMapNode<K, V>) myLinkedList.search(key);
		if (myMapNode == null) {

			myMapNode = new MyMapNode<>(key, value);
			myLinkedList.appendNode(myMapNode);

		} else {

			myMapNode.setValue(value);
		}

	}

	public String toString() {

		return "MyLinkedHashMap List{" + myBucketArray + '}';
	}

	private int getBucketIndex(K key) {

		int hashCode = Math.abs(key.hashCode());
		int index = hashCode % numBuckets;
		return index;
	}

	public void remove(K key) {

		int index = this.getBucketIndex(key);
		MyLinkedList<K> myLinkedList = this.myBucketArray.get(index);
		myLinkedList.deleteGivenKeyNode(key);

	}
}