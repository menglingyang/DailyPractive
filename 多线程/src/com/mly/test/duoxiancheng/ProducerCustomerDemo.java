package com.mly.test.duoxiancheng;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 	生产者，消费者
 * 	多生产者	多消费者
 *
 */
public class ProducerCustomerDemo {
	public static void main(String[] args) {
		Resrouce r = new Resrouce();
		Producer p = new Producer(r);
		Customer c = new Customer(r);
		Thread t0 = new Thread(p);
		Thread t1 = new Thread(p);
		Thread t2 = new Thread(c);
		Thread t3 = new Thread(c);
		t0.start();
		t1.start();
		t2.start();
		t3.start();
	}

}

class Producer implements Runnable{
	
	private Resrouce r ;
	
	public Producer(Resrouce r) {
		this.r=r;
	};

	@Override
	public void run() {
		while(true) {
			r.set("烤鸭");
		}
	}
	
}

class Customer implements Runnable{
	private Resrouce r;
	public Customer(Resrouce r) {
		this.r=r;
	}

	@Override
	public void run() {
		while(true) {
			r.out();
			
		}
		
	}
}

class Resrouce {
	private String name;
	private int count=1;
	private boolean flag =false;
	Lock lock =new ReentrantLock();
	//	通过已有的锁获取两组监视器 	一组生产者监视器，一组消费者监视器
	Condition con1=lock.newCondition();
	Condition con2=lock.newCondition();
	
	public  void set(String name) {
		lock.lock();
		try {
			while(flag)
				try {
					con1.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			this.name=name+count;
			count++;
			System.out.println(Thread.currentThread().getName()+".生产者..."+this.name);
			flag=true;
			con2.signal();
		} catch (Exception e) {
		}finally {
			lock.unlock();
		}
		
		
	}
	public  void out() {
		lock.lock();
		try {
			while(!flag)
				try {
					con2.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			System.out.println(Thread.currentThread().getName()+".消费者______"+this.name);
			this.flag=false;
			con1.signal();
		} catch (Exception e) {
		}finally {
			lock.unlock();
		}
	}
}
