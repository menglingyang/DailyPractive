package com.mly.test.duoxiancheng;

/**
 * 死锁
 * 
 *
 */
public class DeadLoackTest {
	public static void main(String[] args) {
		Test a = new Test();
		Thread t1 = new Thread(a);
		Thread t2 = new Thread(a);
		t1.start();
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		a.flag = false;
		t2.start();
	}
}
class Test implements Runnable {
	public boolean flag = true;
	private static final Object locka = new Object();
	private static final Object lockb = new Object();
	Test() {
	}
	@Override
	public void run() {
		if (flag) {
			while (true) {
				synchronized (locka) {
					System.out.println("if locka ....");
					synchronized (lockb) {
						System.out.println("if lockb ....");
					}
				}
			}
		} else {
			while (true) {
				synchronized (lockb) {
					System.out.println("else lockb ....");
					synchronized (locka) {
						System.out.println("else locka ....");

					}
				}
			}
		}
	}
}
