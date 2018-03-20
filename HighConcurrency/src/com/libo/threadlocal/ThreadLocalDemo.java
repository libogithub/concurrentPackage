package com.libo.threadlocal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 使用Threadlocal其实是通过增加资源的方式保证了多线程环境下的所有对象的线程安全。
 * 
 * 以下是Thread对象中的成员变量threadLocals的表述
 * 
 * ThreadLocal values pertaining to this thread. This map is maintained
 * by the ThreadLocal class.
 *  
 * ThreadLocal.ThreadLocalMap threadLocals = null;
 * 
 * 每个Thread对象存在一个ThreadLocal.ThreadLocalMap对象，该对象的维护是通过ThreadLocal进行操作的。
 * 
 */
public class ThreadLocalDemo {
	
	//private static SimpleDateFormat sf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
	
	private static ThreadLocal<SimpleDateFormat> sf = new ThreadLocal<SimpleDateFormat>(); 
	
	public static void main(String[] args) {
		Executor executor = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 1000; i++) {
			executor.execute(new ParseDate(i));
		}
	}
	
	
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
					sf.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
				}
				Date t = sf.get().parse("2017-06-07 10:44:"+i%60);
				System.out.println(i + ":" + t);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
	}

}
