package com.libo.CyclicBarrier;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

	public static void main(String[] args) throws Exception {
		final int N = 10 ;
		Thread[] soliders = new Thread[10];
		boolean flag = false ;
		CyclicBarrier barrier = new CyclicBarrier(10, new CyclicBarrierRun(flag, N));
		System.out.println("���鼯��!");
		for (int i = 0; i < soliders.length; i++) {
			System.out.println("ʿ��"+i+"����");
			soliders[i] = new Thread(new Soldier("ʿ��"+i, barrier));
			soliders[i].start();
		}
	}
}
