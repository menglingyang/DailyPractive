package com.mly.test.duoxiancheng;

public class ThreadTest {
	public static void main(String[] args) {
		
		new Thread(new Runnable() {
			public void run() {
				System.out.println("runnable run ");
		}}) {
			public void run() {
				System.out.println("subThread run");
			}
			
			
		}.start();
	}
}
//	如果错误，错发生在哪一行？
class Test1 implements Runnable{


	public void run(Thread d) {
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
