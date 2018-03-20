package com.libo.threadpool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DivTask implements Runnable {
	
	int a , b ;

	public DivTask(int a, int b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public void run() {
		double re = a/b ;
		System.out.println(re); 
	}
	
	public static void main(String[] args) {
//		ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 5, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
//		TraceThreadPool extexecutor = new TraceThreadPool(5, 5, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
//		for (int i = 0; i < 5; i++) {
//			//executor.execute(new DivTask(100, i));
//			//extexecutor.submit(new DivTask(100, i));
//			extexecutor.execute(new DivTask(100, i));
//		}
	}

}
