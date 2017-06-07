package com.libo.reentrantLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
/**
 * 请查看JDK比发包中的ArrayBlockingQueue
 */
public class ReenterLockCondition implements Runnable {
	public static ReentrantLock lock = new ReentrantLock();
	public static Condition condition = lock.newCondition();

	@Override
	public void run() {
		try {
			lock.lock();
			condition.await(); //释放锁资源
			System.out.println("线程继续执行....");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		ReenterLockCondition s1 = new ReenterLockCondition();
		Thread thread = new Thread(s1);
		thread.start();
		Thread.sleep(2000);
		/*
		 * 要求线程必须持有锁，再通知其他线程后必须调用unlock方法，否则即使其他线程获得通知，也无法获得锁
		 */
		lock.lock();
		condition.signal(); 
		Thread.sleep(5000);
		lock.unlock();
	}

}
