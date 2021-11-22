package com.ganesh;

public class MySync {
	
	static int count = 0;
	public static void increment() {
		 count ++;
	}

	public static void main(String[] args) {
		
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				for(int i = 0; i < 5000; i++) {
					increment();
				}
			}
			
		});
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				for(int i = 0; i < 5000; i++) {
					increment();
				}
			}
			
		});
		
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Value Of Count : "+ count);
		
		//Output are not fixed each time it will give u different numbers.

	}

}
