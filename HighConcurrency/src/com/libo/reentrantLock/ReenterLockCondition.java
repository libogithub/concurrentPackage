package com.libo.reentrantLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
/**
 * ��鿴JDK�ȷ����е�ArrayBlockingQueue
 */
public class ReenterLockCondition implements Runnable {
	public static ReentrantLock lock = new ReentrantLock();
	public static Condition condition = lock.newCondition();

	@Override
	public void run() {
		try {
			lock.lock();
			condition.await(); //�ͷ�����Դ
			System.out.println("�̼߳���ִ��....");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		ReenterLockCondition s1 = new ReenterLockCondition();
		Thread thread = new Thread(s1);
		thread.start();
		Thread.sleep(2000);
		/*
		 * Ҫ���̱߳������������֪ͨ�����̺߳�������unlock����������ʹ�����̻߳��֪ͨ��Ҳ�޷������
		 */
		lock.lock();
		condition.signal(); 
		Thread.sleep(5000);
		lock.unlock();
	}

}
