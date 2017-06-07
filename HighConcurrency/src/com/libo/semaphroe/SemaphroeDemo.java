package com.libo.semaphroe;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * java的信号量，可以控制多个线程同时进入临街资源
 */
public class SemaphroeDemo implements Runnable {

	private final Semaphore semaphore = new Semaphore(5);

	@Override
	public void run() {
		try {
			semaphore.acquire();
			System.out.println(Thread.currentThread().getName());
			Thread.sleep(1000);
		} catch (Exception e) {
		} finally {
			semaphore.release();
		}
	}
	
	public static void main(String[] args) {
//		Executor executor = Executors.newFixedThreadPool(20);
//		SemaphroeDemo semaphore = new SemaphroeDemo();
//		for (int i = 0; i < 20; i++) {
//			executor.execute(semaphore);
//		}
		System.out.println((5798/10)%32);
	}
}
