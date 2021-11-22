package com.ganesh;

import java.util.Scanner;

class MyVolatileTest extends Thread{
	
	private volatile 
	boolean bool = true;
	public void run() {
		while(bool) {
			System.out.println("Hello World Was Called!!");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public void stopThread() {
		bool = false;
	}
}

public class MyVolatile {

	public static void main(String[] args) {

		MyVolatileTest mvTest = new MyVolatileTest();
		mvTest.run();
		System.out.println("My Thread called!!");
		Scanner sc = new Scanner(System.in);
		sc.nextLine();
		mvTest.stopThread();
	}

}
