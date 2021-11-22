package com.executer;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MyExecuterService {

	public static void main(String[] args) {
		
		ExecutorService execute = Executors.newSingleThreadExecutor();
		//1. submit(runnable) ----> we get null
		//2. submit(callable) ---> we get something
//		Future futuer = execute.submit(new Runnable() {
//
//			@Override
//			public void run() {
//				System.out.println("Run Method Called!!");
//			}
//			
//		});
		
		Future futuer = execute.submit(new Callable() {

			@Override
			public Object call() throws Exception {
				System.out.println("Run method called !!");
				return "Got Something in return with callable";
			}
		   
		});
		try {
			System.out.println("Future.get() called: "+ futuer.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

	}

}
