package com.libo.threadlocal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * ��ʹ��threadloaclʱ��ע���ڶ�����ʹ�õ�ʱ����Ҫremove���󣬷�����ܳ����ڴ�й¶�����⣬��ȻThreadLocal�ڲ�ʹ��
 * ��WeakReference��ʵ��.
 * 
 * ���߳̽���ʱ��Thread.exit()����������ThreadLocal.ThreadLoacalMap.
 */
public class ThreadLocalGcDemo {
	
	private static ThreadLocal<SimpleDateFormat> sf = new ThreadLocal<SimpleDateFormat>(){
		@Override
		protected void finalize() throws Throwable {
			System.out.println(this.toString() + "is gc");
		}
	}; 
	
	static volatile CountDownLatch latch = new CountDownLatch(1000);
	
	public static class ParseDate implements Runnable{
		
		int i = 0 ;
		
		public ParseDate(int i) {
			this.i = i;
		}

		@Override
		public void run() {
			try {
				SimpleDateFormat threadSf = sf.get();
				if(threadSf == null){
					sf.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"){
						@Override
						protected void finalize() throws Throwable {
							System.out.println("is gc");
						}
					});
				}
				Date t = sf.get().parse("2017-06-07 10:44:"+i%60);
				//System.out.println(i + ":" + t);
			} catch (ParseException e) {
				e.printStackTrace();
			} finally {
				latch.countDown();
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Executor executor = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 1000; i++) {
			executor.execute(new ParseDate(i));
		}
		latch.await();
		System.out.println("mission complete!");
		sf = null ;
		System.gc();
		System.out.println("first gc complete");
		
		//������Threadlocalʱ�������ThreadLocalMap�е���Ч�Ķ���
		sf = new ThreadLocal<SimpleDateFormat>();
		latch = new CountDownLatch(1000);
		for (int i = 0; i < 1000; i++) {
			executor.execute(new ParseDate(i));
		}
		latch.await();
		Thread.sleep(1000);
		System.gc();
		Thread.sleep(5000);
		System.gc();
		System.out.println("second gc complete");
	}

}
