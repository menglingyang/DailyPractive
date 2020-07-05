	package com.mly.test.duoxiancheng;
	
	public class JoinDemo {
		public static void main(String[] args) {
			DemoJ d = new DemoJ();
			Thread t1 = new Thread(d);
			Thread t2 = new Thread(d);
			
			t1.start();
			t2.start();
	//		t2.setPriority(Thread.MAX_PRIORITY); //线程设置优先级
			try {
				t1.join();	//t1线程要申请加入进来，运行;临时加入线程运算时可以使用join方法
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			for (int i = 0; i < 50; i++) {
				System.out.println(Thread.currentThread().getName()+"....."+i);
			}
		}
	
	}
	
	class DemoJ implements Runnable{
	
		@Override
		public void run() {
			for (int i = 0; i < 100; i++) {
				System.out.println(Thread.currentThread().toString()+"...."+i);
				Thread.yield();//	线程暂停
			}
			
		}
		
	}