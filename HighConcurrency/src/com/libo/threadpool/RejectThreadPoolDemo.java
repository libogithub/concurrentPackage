package com.libo.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class RejectThreadPoolDemo {

	public static class MyTask implements Runnable {

		@Override
		public void run() {
			System.out.println(System.currentTimeMillis() + ":Thread ID:" + Thread.currentThread().getId());
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		MyTask myTask = new MyTask();
//		MyTask1 myTask = new MyTask1();
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 5, 0, 
															TimeUnit.SECONDS, 
															new LinkedBlockingDeque<Runnable>(10),
															Executors.defaultThreadFactory(),
															new RejectedExecutionHandler() {
																@Override
																public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
																	System.out.println(r.toString() + " is discard");
																}
															});
		
		ThreadPoolExecutor extThreadPoolExecutor = new ThreadPoolExecutor(5, 5, 0, 
				TimeUnit.SECONDS, 
				new LinkedBlockingDeque<Runnable>(10),
				Executors.defaultThreadFactory(),
				new RejectedExecutionHandler() {
					@Override
					public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
						System.out.println(r.toString() + " is discard");
					}
				}){

					@Override
					protected void beforeExecute(Thread t, Runnable r) {
						System.out.println("准备执行.....");
					}

					@Override
					protected void afterExecute(Runnable r, Throwable t) {
						System.out.println("执行完成.....");
					}

					@Override
					protected void terminated() {
						System.out.println("线程池退出.....");
					}
				};
		
		
		Future future = extThreadPoolExecutor.submit(myTask);
		extThreadPoolExecutor.shutdown();
//		for (int i = 0; i < Integer.MAX_VALUE; i++) {
//			threadPoolExecutor.submit(myTask);
//			Thread.sleep(10);
//		}
	}

}
