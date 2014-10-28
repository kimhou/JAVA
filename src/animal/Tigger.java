package animal;

/**
 * Created by tencent on 14/10/28.
 * 接口实现, 内部类实现, 类clone实现
 */

import animal.Animal;

public class Tigger implements Animal {
    public void sleep(){
        System.out.println("I'm tigger, I'm sleeping, type="+this.type);
    }
    public void run(){
        System.out.println("I'm tigger, I'm runing!");
    }
    public void showFish(){
        Fish f = new Fish("littleFish");
        Fish f2 = (Fish)f.clone();
        f2.name = "fish 2";
        f.showSmallFish();
        f2.showSmallFish();
    }
}
//内部类的实现
//类clone实现
class Fish implements Cloneable{
    String name;
    public Fish(String n){
        name = n;
    }
    public void showSmallFish(){
       SmallFish fish1 = new SmallFish();
        fish1.say();
    }
    //内部类
    class SmallFish{
        public void say(){
            System.out.println(name + " is a small fish");
        }
    }

    public Object clone(){
        Fish f = null;
        try{
            f = (Fish)super.clone();
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return f;
    }
}
