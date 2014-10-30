package CT10_thread;

/**
 * Created by tencent on 14/10/28.
 */
public class TestLock {
    public static void main(String args[]){
        ShareData oShare = new ShareData();
        Thread1 t1 = new Thread1("Thread1", oShare);
        Thread1 t2 = new Thread1("Thread2", oShare);
        t1.start();
        t2.start();

        ShareData oShare2 = new ShareData();
        Thread2 clockThread = new Thread2("Thread1", oShare2);
        Thread2 clockThread2 = new Thread2("Thread2", oShare2);
        clockThread.start();
        clockThread2.start();
    }
}

class ShareData {
    public  static String szData = "";
}

class Thread1 extends Thread{
    private ShareData oShare;
    Thread1(){}
    Thread1(String szName, ShareData shareData){
        super(szName);
        this.oShare = shareData;
    }

    public void run(){
        for(int i = 0; i < 10; i++){
            if(this.getName().equals("Thread1")){
                this.oShare.szData = "这是第1线程";
                try{
                    Thread.sleep((int)Math.random()*100);
                }catch (Exception e){}
                System.out.println(this.getName() + ": " + oShare.szData);
            }else if(this.getName().equals("Thread2")){
                this.oShare.szData = "这是第2个线程";
                try{
                    Thread.sleep((int)Math.random()*100);
                }catch (Exception e){}
                System.out.println(this.getName() + ": " + oShare.szData);
            }

        }
    }
}
class Thread2 extends Thread{
    private ShareData oShare;
    Thread2(){}
    Thread2(String szName, ShareData shareData){
        super(szName);
        this.oShare = shareData;
    }

    public void run(){
        synchronized (oShare){
            for(int i = 0; i < 10; i++){
                if(this.getName().equals("Thread1")){
                    this.oShare.szData = "这是第1个线程:有锁";
                    try{
                        Thread.sleep((int)Math.random()*100);
                    }catch (Exception e){}
                    System.out.println(this.getName() + ": " + oShare.szData);
                }else if(this.getName().equals("Thread2")){
                    this.oShare.szData = "这是第2个线程:有锁";
                    try{
                        Thread.sleep((int)Math.random()*100);
                    }catch (Exception e){}
                    System.out.println(this.getName() + ": " + oShare.szData);
                }
            }
        }
    }
}
