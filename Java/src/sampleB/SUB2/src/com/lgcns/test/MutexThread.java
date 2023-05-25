package com.lgcns.test;

import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;

public class MutexThread extends Thread{
	static ReentrantLock lock = new ReentrantLock();
	
	private String threadName;
	Worker worker;
	public MutexThread(String name) {
		this.threadName = name;
		worker = new Worker(Integer.parseInt(name));
	}
	
	public void run() {
		while(true) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			lock.lock();
			PrintNums(threadName);
			lock.unlock();
		}
	}
	
	void PrintNums(String str) {
		Queue<String> queue = SingletonQueue.getInstance().getQueue();
		if(!queue.isEmpty()) {
			String cur = queue.peek();
			String[] split=cur.split(" ");
			if(split[1].equals(this.threadName)){
				String ret = worker.run(Long.parseLong(split[0]),split[2]);
				if(ret != null)
					System.out.println(ret);
				queue.poll();
//				System.out.println(split[0]+" "+split[1]+" "+split[2]);
			}
		}
	}
}
