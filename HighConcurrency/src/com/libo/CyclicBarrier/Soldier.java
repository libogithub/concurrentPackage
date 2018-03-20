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
			//�ȴ�����ʿ������
			cyclicBarrier.await();
			dowork();
			//�ȴ�����ʿ���������
			cyclicBarrier.await();
		} catch (BrokenBarrierException | InterruptedException e) {
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
		System.out.println(solider + ":�������");
	}

}
