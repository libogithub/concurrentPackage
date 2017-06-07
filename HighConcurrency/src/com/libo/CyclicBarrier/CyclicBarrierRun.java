package com.libo.CyclicBarrier;

public class CyclicBarrierRun implements Runnable {

	Boolean flag ;
	int N ;
	
	public CyclicBarrierRun(Boolean flag, int n) {
		this.flag = flag;
		this.N = n;
	}
	
	@Override
	public void run() {
		if(flag){
			System.out.println("司令:[士兵"+N+"个],完成任务!");
		}else{
			System.out.println("司令:[士兵"+N+"个],集合完毕!");
			flag = true ;
		}
	}

}
