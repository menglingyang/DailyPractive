package com.mly.test.duoxiancheng;

public class SingleDemo {
	public static void main(String[] args) {
	}
}
//饿汉式
class Single{
	private static final Single s = new Single();
	private Single() {}
	public static Single getInstance() {
		return s;
	}
}
//懒汉式
class Single1{
	private static Single1 s = null;
	private Single1() {}
	
	public static Single1 getInstance() {
		if(s==null) { //解决效率问题
			synchronized (Single1.class) { //解决安全问题
				if(s==null) {	
					s=new Single1();
				}
			}
		}
		return s ;
	}
}


