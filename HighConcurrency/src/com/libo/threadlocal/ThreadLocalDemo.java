package com.libo.threadlocal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * ʹ��Threadlocal��ʵ��ͨ��������Դ�ķ�ʽ��֤�˶��̻߳����µ����ж�����̰߳�ȫ��
 * 
 * ������Thread�����еĳ�Ա����threadLocals�ı���
 * 
 * ThreadLocal values pertaining to this thread. This map is maintained
 * by the ThreadLocal class.
 *  
 * ThreadLocal.ThreadLocalMap threadLocals = null;
 * 
 * ÿ��Thread�������һ��ThreadLocal.ThreadLocalMap���󣬸ö����ά����ͨ��ThreadLocal���в����ġ�
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
