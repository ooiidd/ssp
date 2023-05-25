package com.lgcns.test;

import java.util.Scanner;

public class RunManager {

	public static void main(String[] args) throws InterruptedException {
//		System.out.println("ss");
		MutexThread thread1 = new MutexThread("0");
		MutexThread thread2 = new MutexThread("1");
		thread1.start();
		thread2.start();
		
//		thread1.join();
//		thread2.join();
		
		Scanner scan = new Scanner(System.in);
		while(true) {
//			System.out.println("add : ");
			String line = scan.nextLine();
			MutexThread.lock.lock();
			SingletonQueue.getInstance().add(line);
//			System.out.println("add : " + line);
			MutexThread.lock.unlock();
		}
		
	}
}
