package CT10_thread;

/**
 * Created by tencent on 14/10/28.
 */
public class MyThread extends Thread {
    public void run(){
        for(int count = 1, row = 1; row < 30; count++, row++){
            for(int i = 0; i < count; i++){
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void main(String args[]){
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
        MyThread t3 = new MyThread();
        t1.start();
        t2.start();
        t3.start();
    }
}
