package CT10_thread;

/**
 * Created by tencent on 14/10/28.
 * 线程通信, 通过notify唤醒获取了本对象锁的线程
 */
public class ThreadCommunicate {
    public static void main(String args[]){
        Queue q = new Queue();
        Productor p = new Productor(q);
        Customer c = new Customer(q);
        p.start();
        c.start();
    }
}

class Queue{
    int count = 0;
    public synchronized void put(int putCount){
        if(count > 0){
            try{
                System.out.println("生产者等待");
                wait();
            }catch (Exception e){}
        }
        count += putCount;
        System.out.println("生产者生产了: " + putCount);
        notify();
    }
    public synchronized int get(){
        if(count <= 0){
            try{
                System.out.println("消费者等待");
                wait();
            }catch (Exception e){}
        }
        count--;
        System.out.println("消息者消费了一个产品");
        notify();
        return count;
    }
}

class Productor extends Thread{
    Queue q;
    Productor(Queue q){
        this.q = q;
    }
    public void run(){
        for(int i = 0; i < 5; i++){
            try{
                this.q.put(3);
                Thread.sleep((int)Math.random()*100);
            }catch (Exception e){}
        }
    }
}

class Customer extends Thread{
    Queue q;
    Customer(Queue q){
        this.q = q;
    }
    public void run(){
        while (true){
            this.q.get();
        }
    }
}
