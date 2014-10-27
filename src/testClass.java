/**
 * Created by tencent on 14/10/27.
 */
import pepole.*;
public class testClass {
    public static void main(String args[]){
        Pepole p1 = new Pepole("kim");
        p1.age = 20;
        Pepole p2 = new Pepole();
        p2.name = "lisa";
        p2.age = 18;
        p1.say();
        p2.say();

        Man hick = new Man("hick");
        hick.say();
    }
}




