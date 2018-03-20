package com.libo.threadlocal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 当使用threadloacl时，注意在对象不在使用的时候需要remove对象，否则可能出现内存泄露的问题，虽然ThreadLocal内部使用
 * 了WeakReference来实现.
 * 
 * 在线程结束时（Thread.exit()）会调用清除ThreadLocal.ThreadLoacalMap.
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
		
		//在设置Threadlocal时，会清除ThreadLocalMap中的无效的对象
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
