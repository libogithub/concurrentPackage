package com.libo.countDownLatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 控制多个线程工作完成后，主线程在继续向下执行
 */
public class CountDownLatchDemo implements Runnable {
	
	static CountDownLatch latch = new CountDownLatch(10);

	@Override
	public void run() {
		try {
			Thread.sleep(new Random().nextInt(10)* 1000);
			System.out.println("check complete...");
			latch.countDown();
		} catch (Exception e) {
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		ExecutorService executor = Executors.newFixedThreadPool(20);
		CountDownLatchDemo demo = new CountDownLatchDemo();
		for (int i = 0; i < 10; i++) {
			executor.execute(demo);
		}
		
		latch.await();
		System.out.println("fire......");
		executor.shutdown();
	}
}
