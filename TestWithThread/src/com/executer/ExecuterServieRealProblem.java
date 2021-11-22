package com.executer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Send mails to all students of university regarding their course registration of the academic year
 * 6000 students
 * data from different tables of database
 * 0.5 second in each student(one mail)
 * 0.5 * 6000 = 3000 (50 minutes)
 * in this example Threading used
 */

class SendMail implements Runnable{

	@Override
	public void run() {
       try {
		Thread.sleep(500);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}		
	}
	
}

public class ExecuterServieRealProblem {

	public static void main(String[] args) {
        
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		ExecutorService es = Executors.newFixedThreadPool(10);
		System.out.println("Time:- "+ df.format(new Date()));
		for(int i = 0; i < 10; i++) {
			es.submit(new SendMail());
		}
		es.shutdown();
		try {
			es.awaitTermination(1, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Time:- "+ df.format(new Date()));
		
	}

}
