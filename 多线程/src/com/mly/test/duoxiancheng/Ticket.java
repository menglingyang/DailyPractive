package com.mly.test.duoxiancheng;

public class Ticket  implements Runnable {
	
	private  int num =300;
	Object obj = new Object();
	
	public void run() {
			sale();
	}
	
	public void sale() {
		while(true) {
			synchronized (this) {
				if(num>0) {
					System.out.println(Thread.currentThread().getName()+"卖票："+num--);
				}else {
					System.out.println("票卖完啦");
					break;
				}
			}
		}
	}

}
