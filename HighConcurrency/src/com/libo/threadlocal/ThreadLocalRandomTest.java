package com.libo.threadlocal;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * ���Զ��߳������������ʹ��ThreadLocal�Ͳ�ʹ�ã����ַ�ʽ�����ܲ��
 */
public class ThreadLocalRandomTest {

	public static final int GEN_COUNT = 10000000;
	public static final int THREAD_COUNT = 4;
	static ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
	public static Random rnd = new Random(123);
	// ThreadLocal��ʽ
	public static ThreadLocal<Random> tRnd = new ThreadLocal<Random>() {

		@Override
		protected Random initialValue() {
			return new Random(123);
		}

	};
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		Future<Long>[] futs = new Future[THREAD_COUNT];
		for (int i = 0; i < futs.length; i++) {
			futs[i] = executorService.submit(new RndTask(0));
		}
		long totalTime = 0 ;
		for (int i = 0; i < THREAD_COUNT; i++) {
			totalTime += futs[i].get();
		}
		System.out.println("���̷߳���ͬһ��Randomʵ��:"+ totalTime + "ms");
		
		//ThreadLocal�����
		for (int i = 0; i < futs.length; i++) {
			futs[i] = executorService.submit(new RndTask(1));
		}
		totalTime = 0 ;
		for (int i = 0; i < THREAD_COUNT; i++) {
			totalTime += futs[i].get();
		}
		System.out.println("ʹ��Threadlocal��װRandomʵ��:"+ totalTime + "ms");
		
		executorService.shutdown();
	}

	public static class RndTask implements Callable<Long> {

		private int mode = 0;

		public RndTask(int mode) {
			this.mode = mode;
		}

		public Random getRandom() {
			if (mode == 0) {
				return rnd;
			} else if (mode == 1) {
				return tRnd.get();
			} else {
				return null;
			}
		}

		@Override
		public Long call() throws Exception {
			long b = System.currentTimeMillis();
			for (int i = 0; i < GEN_COUNT; i++) {
				getRandom().nextInt();
			}
			long e = System.currentTimeMillis();
			System.out.println(Thread.currentThread().getName() + " : speed " + (e-b) +"ms");
			return e-b;
		}

	}

}
