package com.libo.lockSupport;

import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo {
	
	public static class Park implements Runnable{

		@Override
		public void run() {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			LockSupport.park();
			System.out.println("---------------");
		}
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		Thread t1 = new Thread(new Park());
		t1.start();
		LockSupport.unpark(t1);
		Thread.sleep(3000);
		System.out.println("-------222222222-------");
		
	}

}
