package CT10_thread;

/**
 * Created by tencent on 14/10/28.
 * 创建线程的两种方式
 * 1. 继承, 2. 实现Runnable接口, 3. 多线程的串行方法
 */
public class MyThread extends Thread {
    public void run(){
        for(int count = 1, row = 1; row < 20; count++, row++){
            for(int i = 0; i < count; i++){
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void main(String args[]){
        //testSerial();
        testSerial2();
    }

    //多线程并发
    static void testThread(){
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
        MyThread t3 = new MyThread();
        t1.start();
        t2.start();
        t3.start();

        Mythread2 rn1 = new Mythread2();
        Mythread2 rn2 = new Mythread2();
        Mythread2 rn3 = new Mythread2();
        Thread tt1 = new Thread(rn1);
        Thread tt2 = new Thread(rn2);
        Thread tt3 = new Thread(rn3);
        tt1.start();
        tt2.start();
        tt3.start();
    }

    //多线程串行
    //方法1
    static void testSerial(){
        //线程串行
        System.out.println("线程串行测试");
        MyThread th1 = new MyThread();
        MyThread th2 = new MyThread();
        th1.start();
        while (th1.isAlive()){
            try{
                Thread.sleep(100);
            }catch (Exception e){

            }
        }
        th2.start();
    }
    //方法2
    static void testSerial2(){
        System.out.println("线程串行测试");
        MyThread th1 = new MyThread();
        MyThread th2 = new MyThread();
        th1.start();
        try{
            th1.join();
        }catch (Exception e){

        }
        th2.start();
    }
}

class Mythread2 implements Runnable{
    public void run(){
        for(int count = 1, row = 1; row < 20; count++, row++){
            for(int i = 0; i < count; i++){
                System.out.print("@");
            }
            try{
                Thread.sleep(1000);
                System.out.println("waiting ...");
            }catch (Exception e){

            }
            System.out.println();
        }
    }
}
