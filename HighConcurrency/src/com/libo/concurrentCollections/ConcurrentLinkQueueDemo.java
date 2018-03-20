package com.libo.concurrentCollections;

import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CopyOnWriteArrayList;


public class ConcurrentLinkQueueDemo {
	
	public static void main(String[] args) {
		ConcurrentLinkedQueue<String> queues = new ConcurrentLinkedQueue<String>();
		queues.add("1");
		queues.offer("2");
		
		ConcurrentSkipListMap<String, Integer> map = new ConcurrentSkipListMap<>();
		map.put("3", 3);
		map.put("1", 1);
		map.put("2", 2);
		map.put("4", 4);
		ThreadLocal<String> aa = new ThreadLocal<String>();
		aa.set("1");
		aa.get();
		aa.remove();
		
		for (String key : map.keySet()) {
			System.out.println(map.get(key));
		}
		
//		for (int t = 0, p = t;;) {
//			System.out.println(p);
//		}
		
//		CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
//		list.add("1");
//		list.get(0);
		
//		BlockingQueue<String>
		
	}

}
