package com.mly.test.duoxiancheng;

public class StopThreadDemo {
	
	public static void main(String[] args) {
		StopThread stop=new StopThread();
		Thread t1 =new Thread(stop);
		Thread t2 =new Thread(stop);
		t1.start();
		t2.setDaemon(true);
		t2.start();
		 int num=1;
		 for(;;) {
			 if(++num==50) {
				 t1.interrupt();//将线程从冻结状态强制恢复到运行状态中来，让线程具备cpu执行资格
//				 t2.interrupt();
				 break;
			 }
			 System.out.println("main...."+num);
		 }
		 System.out.println("over");
	}
}
class StopThread implements Runnable{
	private boolean flag =true;
	
	public void setFlag() {
		this.flag=false;
	}

	@Override
	public synchronized void run() {
		while(flag) {
			try {
				wait();
			} catch (Exception e) {
				 System.out.println(Thread.currentThread().getName()+"----"+e);
				 flag=false;
			}
			System.out.println(Thread.currentThread().getName()+"----");
		}
		
	}
	
}