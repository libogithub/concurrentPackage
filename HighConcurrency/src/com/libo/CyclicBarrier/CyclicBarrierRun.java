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
			System.out.println("˾��:[ʿ��"+N+"��],�������!");
		}else{
			System.out.println("˾��:[ʿ��"+N+"��],�������!");
			flag = true ;
		}
	}

}
