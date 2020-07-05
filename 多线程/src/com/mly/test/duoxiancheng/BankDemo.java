package com.mly.test.duoxiancheng;

public class BankDemo {
	public static void main(String[] args) {
		Cus c = new Cus();
		Thread  t1 = new Thread(c);
		Thread  t2 = new Thread(c);
		t1.start();
		t2.start();
	}
}
class Bank{
	private int sum;
	
	public synchronized void add(int num) { //同步函数
		sum=sum+num;
		System.out.println("sum:"+sum);
	}
}
class Cus implements Runnable{
	Bank b = new Bank();
	@Override
	public void run() {
		
		for (int i = 0; i < 3; i++) {
				b.add(100);
		}		
	}
}
