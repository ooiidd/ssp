package thread;

public class SampleThread {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		// ?Š¤? ˆ?“œ ?ƒ?„±
		ThreadClass thread1 = new ThreadClass("[Thread1]");
		ThreadClass thread2 = new ThreadClass("[Thread2]");
		thread1.start();
		thread2.start();
		
		for(int i=0; i<10; i++) {
			System.out.println("[Main]" + i);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// ?Š¤? ˆ?“œê°? ??‚  ?•Œê¹Œì? ê¸°ë‹¤ë¦?
		thread1.join();
		thread2.join();
	}

}

class ThreadClass extends Thread{
	String threadName;
	public ThreadClass(String name) {
		this.threadName = name;
	}
	
	public void run() {
		for(int i = 0; i < 10; i++) {
			System.out.println(threadName + i);
			try {
				sleep(10);
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
