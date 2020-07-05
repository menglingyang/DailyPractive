package com.mly.test.duoxiancheng;

public class ResourceDemo {
	public static void main(String[] args) {
//		创建资源
		Resource r = new Resource();
//		创建任务
		Input input = new Input(r);
		Output outPut = new Output(r);
//		创建线程，执行路径
		Thread t1 = new Thread(input);
		Thread t2 = new Thread(outPut);
//		开启线程
		t1.start();
		t2.start();
	}
}

class Resource {
	private String name;
	private String sex;
	private boolean flag = false;

	public synchronized void set(String name, String sex) {
		if (flag)
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		this.name = name;
		this.sex = sex;
		flag = true;
		this.notify();
	}

	public synchronized void out() {
		if (!flag)
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		System.out.println(name + "...." + sex);
		flag = false;
		this.notify();
	}

}

class Input implements Runnable {
	Resource r;

	public Input(Resource r) {
		this.r = r;
	}

	@Override
	public void run() {
		int x = 0;
		while(true) {
			if (x == 0) {
				r.set("mike", "man");
			} else {
				r.set("丽丽", "女女女");
			}
			x = (x + 1) % 2;
		}
	}

}

class Output implements Runnable {
	Resource r;

	public Output(Resource r) {
		this.r = r;
	}

	@Override
	public void run() {
		int a = 0;
		while (a <= 10) {
			r.out();
			a++;
		}
	}

}