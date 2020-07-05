package com.mly.test.duoxiancheng;

public class ThreadDemo2{
	public static void main(String[] args) {
//		Demo d1 = new Demo("旺财");
//		Demo d2 = new Demo("xaingqiang");
//		d1.start();
//		d2.start();
		Demo2 d = new Demo2();
		Thread thread1 =new Thread(new Demo2(),"旺财2");
		thread1.start();
		Thread thread2 =new Thread(new Demo2(),"小强");
		thread2.start();
		
		System.out.println(Thread.currentThread().getName());
	}

}
class Demo extends Thread{
	private String name;
	Demo(String name){
		super(name);
		this.name = name;
	}
	public void show() {
		for (int i = 0; i < 10; i++) {
			System.out.println(name+"....x="+i+"...."+getName());
		}
	}
	
	public void run() {
		this.show();
	}
}

class Demo2 implements Runnable{

	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			System.out.println(Thread.currentThread().getName());
		}
	}
	
	
	
}


