package com.libo.CyclicBarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Soldier implements Runnable {
	
	private String solider ;
	private CyclicBarrier cyclicBarrier ;
	
	public Soldier(String solider, CyclicBarrier cyclicBarrier) {
		this.solider = solider;
		this.cyclicBarrier = cyclicBarrier;
	}
	@Override
	public void run() {
		try {
			cyclicBarrier.await();//等待所有士兵到齐
//			if("士兵5".equals(solider)){
//				int a = 5/0;
//			}
			//等待所有士兵到齐
			cyclicBarrier.await();
			dowork();
			cyclicBarrier.await();//等待所有士兵完成任务
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
	}

	private void dowork() {
		try {
			Thread.sleep(new Random().nextInt(10) * 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(solider + ":完成任务");
	}
}
