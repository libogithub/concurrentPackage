package com.libo.readwriteLock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {
	
	private static Lock lock = new ReentrantLock();
	private static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	private static Lock readLock = readWriteLock.readLock();
	private static Lock writeLock = readWriteLock.writeLock();
	private int value ;
	
	public Object handleRead(Lock lock) throws InterruptedException{
		try {
			lock.lock();
			Thread.sleep(1000);  //模拟耗时操作
			System.out.println("----------");
			return value ;
		} finally {
			lock.unlock();
		}
	}
	
	public Object handleWrite(Lock lock,int index) throws InterruptedException{
		try {
			lock.lock();
			Thread.sleep(1000);  //模拟耗时操作
			value = index ;
			return value ;
		} finally {
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		final ReadWriteLockDemo demo = new ReadWriteLockDemo();
		
		Runnable readRunable = new Runnable() {
			@Override
			public void run() {
				try {
//					demo.handleRead(readLock);
					demo.handleRead(lock);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} 
			}
		};
		
		Runnable writeRunable = new Runnable() {
			@Override
			public void run() {
				try {
					demo.handleWrite(writeLock, new Random().nextInt());
//					demo.handleWrite(lock, new Random().nextInt());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		for(int i = 0 ; i <18 ; i++){
			new Thread(readRunable).start();
		}
		for(int i = 0 ; i <18 ; i++){
//			new Thread(writeRunable).start();
		}
	}
}
