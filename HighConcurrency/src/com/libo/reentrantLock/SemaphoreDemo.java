package com.libo.reentrantLock;

import java.util.concurrent.Semaphore;

/**
 * JDK提供的一个demo
 * 注意一点Semaphore提供了一种可以控制多个线程进入临界资源的方法，
 * 但是针对临界资源的访问还是需要有同步机制，譬如实例代码中的getNextAvailableItem()方法依然需要保持通知
 */
public class SemaphoreDemo {

	private static final int MAX_AVAILABLE = 100;
	private final Semaphore available = new Semaphore(MAX_AVAILABLE, true);

	public Object getItem() throws InterruptedException {
		available.acquire();
		return getNextAvailableItem();
	}

	public void putItem(Object x) {
		if (markAsUnused(x))
			available.release();
	}

	// Not a particularly efficient data structure; just for demo

	protected Object[] items = new Object[10];
	protected boolean[] used = new boolean[MAX_AVAILABLE];

	protected synchronized Object getNextAvailableItem() {
		for (int i = 0; i < MAX_AVAILABLE; ++i) {
			if (!used[i]) {
				used[i] = true;
				return items[i];
			}
		}
		return null; // not reached
	}

	protected synchronized boolean markAsUnused(Object item) {
		for (int i = 0; i < MAX_AVAILABLE; ++i) {
			if (item == items[i]) {
				if (used[i]) {
					used[i] = false;
					return true;
				} else
					return false;
			}
		}
		return false;
	}
}
