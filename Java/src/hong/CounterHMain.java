package hong;

public class CounterHMain {
    public static void main(String[] args) {
        class MyThread extends Thread{
            String str;
            MyThread(String str){
                this.str = str;
            }
            @Override
            public void run(){
                CounterH counterH = new CounterH(str,2);
                try {
                    counterH.run();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        for(int i=0;i<10;i++) {
            MyThread t = new MyThread("test1");
            t.start();
        }

    }
}
