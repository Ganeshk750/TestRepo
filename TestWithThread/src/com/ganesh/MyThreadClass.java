package com.ganesh;


class MyExample extends Thread{
	
	public void run() {
		for(int i = 0; i < 100; i++) {
			System.out.println("Value of I: "+ i);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

public class MyThreadClass {
	
	public static void main(String[] args) {
		
		MyExample m = new MyExample();
		m.start();
		MyExample m1 = new MyExample();
		m1.start();
	}

}
