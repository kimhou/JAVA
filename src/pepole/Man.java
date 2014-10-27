package pepole;

//继承
public class Man extends Pepole{
    public Man(String name){
        this.name = name;
        this.sex = 1;
    }
    public void say(){
        System.out.println("我是个男生, 我叫: " + this.name);
    }
}
