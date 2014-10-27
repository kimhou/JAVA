package pepole;

public class Pepole {
    public static final int footerCount = 2;//静态常量
    public String name;
    public byte sex;
    public int age;
    public float height;

    private String address;//本类可见
    protected String company;//本类, 子类, 同包可见
    String phone;//本类,同包可见

    //自定义构造函数
    public Pepole(String name){
        this.name = name;
        this.sex = 1;
        this.age = 0;
        this.height = 0;
    }

    public Pepole(){

    }

    public void say(){
        System.out.println("我叫:"+this.name + ", 年龄: "+this.age);
    }

    public static String className(){
        return "Pepole";
    }
}
