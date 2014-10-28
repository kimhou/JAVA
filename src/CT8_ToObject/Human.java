package CT8_ToObject;

/**
 * Created by tencent on 14/10/28.
 * 类封装, 类属性继承
 */
public class Human {
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    private int age;

    public void testFatherSon(){
        Father f = new Father();
        Son s = new Son();

        s.defaultName = "son default name";

        f.say("father say: ");
        s.say("son say: ");
    }

}

class Father{
    private String privateName = "father privateName";
    protected String protectedName = "father protectedName"; //非同包子类可以访问
    String defaultName = "father defaultName";//非同包子类不能访问

    public void say(String str){
        System.out.println(str + "privateName: " + this.privateName + ", protectedName: " + protectedName + ", defaultName: " + defaultName);
    }
    private void say(int i){
        System.out.println(i + "privateName: " + this.privateName + ", protectedName: " + protectedName + ", defaultName: " + defaultName);
    }
    void say(float f){
        System.out.println(f + "privateName: " + this.privateName + ", protectedName: " + protectedName + ", defaultName: " + defaultName);
    }
}

class Son extends Father{

    public Son(){
        this.protectedName = "son pn";

    }
}