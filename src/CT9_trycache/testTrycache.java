package CT9_trycache;

import java.beans.Expression;

/**
 * Created by tencent on 14/10/28.
 * 第9章 异常处理
 */
public class testTrycache {


    public static void main(String args[]){
        String str = null;
        try {
            int b = str.length();
        }catch (NullPointerException e){
            System.out.println("get error");
        }

        try{
            int[] arr = new int[2];
            arr[3] = 10;
            int c = str.length();
        }catch (NullPointerException e){
            System.out.println("NullPointerException");
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("array expresstion");
        }

        try{
            int x = str.length();

        }catch (ArrayIndexOutOfBoundsException e){

        }catch (Exception e2){
            System.out.println("e2");
            e2.printStackTrace();
        }finally {
            System.out.println("finally");
        }

        try{
            method2();
        }catch (Exception e){
            System.out.println("主方法捕获到错误");
        }


        System.out.println("end");
    }


    //异常的再次抛出
    static void method(){
        throw new ArithmeticException();
    }
    static void method2() throws Exception{
        try{
            method();
        }catch(Exception e){
            System.out.println("捕获到方法内部错误");
            throw e;
        }
    }
}
