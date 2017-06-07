package com.libo.countDownLatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ���ƶ���̹߳�����ɺ����߳��ڼ�������ִ��
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
