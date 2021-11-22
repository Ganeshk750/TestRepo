package com.ganesh;

class MyRunnable implements Runnable {

	@Override
	public void run() {
		
		for(int i = 0; i < 100; i++) {
			System.out.println("Value of I Using RunnableI: "+ i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}

public class UsingRunnable {
	
	public static void main(String[] args) {
		
		Thread t1 = new Thread(new MyRunnable());
		t1.start();
		Thread t2 = new Thread(new MyRunnable());
		t2.start();
		
	}

}
