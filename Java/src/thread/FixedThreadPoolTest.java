package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolTest {


    public static void main(String[] args) {
        class MyThread extends Thread{
            int sec;
            MyThread(int sec){
                super();
                this.sec = sec;
            }
            @Override
            public void run(){

                System.out.println(this.getName()+" start");

                try {
                    this.sleep(sec*1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println(this.getName()+" end");
            }
        }
        class MyThread2 extends Thread{
            int sec;
            ExecutorService executorService;
            MyThread2(int sec, ExecutorService executorService){
                super();
                this.executorService= executorService;
                this.sec = sec;
            }
            @Override
            public void run(){
                System.out.println("run "+sec);
                executorService.execute(new MyThread(sec+3));
            }
        }

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for(int i=1;i<5;i++){
            MyThread2 myThread = new MyThread2(i,executorService);
            myThread.start();
        }
    }
}
