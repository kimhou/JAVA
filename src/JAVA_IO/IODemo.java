package JAVA_IO;

/**
 * Created by tencent on 14/10/31.
 * IO, 编解码 DEMO
 *
 * 参考:
 * http://www.ibm.com/developerworks/cn/java/j-lo-javaio/
 * http://developer.51cto.com/art/201309/410902.htm
 * http://www.cnblogs.com/lich/archive/2011/12/11/2283700.html
 * 字节流与字符流的区别
 字节流和字符流使用是非常相似的，那么除了操作代码的不同之外，还有哪些不同呢？
 字节流在操作的时候本身是不会用到缓冲区（内存）的，是与文件本身直接操作的，而字符流在操作的时候是使用到缓冲区的
 字节流在操作文件时，即使不关闭资源（close方法），文件也能输出，但是如果字符流不使用close方法的话，则不会输出任何内容，说明字符流用的是缓冲区，并且可以使用flush方法强制进行刷新缓冲区，这时才能在不close的情况下输出内容
 那开发中究竟用字节流好还是用字符流好呢？
 在所有的硬盘上保存文件或进行传输的时候都是以字节的方法进行的，包括图片也是按字节完成，而字符是只有在内存中才会形成的，所以使用字节的操作是最多的。
 如果要java程序实现一个拷贝功能，应该选用字节流进行操作（可能拷贝的是图片），并且采用边读边写的方式（节省内存）。
 */
import java.io.*;

public class IODemo {
    FileOutputStream outputStream;
    OutputStreamWriter streamWriter;
    public static void main(String args[]){
        new IODemo();
    }
    public  IODemo(){
        //文件读写
        //方式1, streamWriter, streamReader 流式读写, writer和reader是JAVA提供的可直接操作字符的类, stream则只能读写byte[]字节
        try{
            outputStream = new FileOutputStream("ioTest.txt");
            streamWriter = new OutputStreamWriter(outputStream, "UTF-8");
            streamWriter.write("我是一个粉刷匠abcdABCD!222");
            streamWriter.close();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
        }

        try{
            FileInputStream inputStream = new FileInputStream("ioTest.txt");
            InputStreamReader streamReader = new InputStreamReader(inputStream, "UTF-8");
            StringBuffer buffer = new StringBuffer();
            char[] buf = new char[1024];
            int count = 0;
            while(count != -1){
                count = streamReader.read(buf);
                buffer.append(buf);
                System.out.println(count);
            }
            System.out.println("result:"+buffer.toString());
        }catch (IOException e){
            e.printStackTrace();
        }
        //方式2, FileWrite, FileReader 直接读写字符
        try{
            File file = new File("ioTest2.txt");
            FileWriter writer = new FileWriter(file);
            writer.write("直接写的字符");
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        try{
            File file = new File("ioTest2.txt");
            FileReader reader = new FileReader(file);
            int readed = 0, size = (int)file.length();
            char[] c = new char[size];
            while(reader.ready()){
                readed += reader.read(c, readed, size - readed);
            }
            reader.close();
            System.out.println("fileReader get: " + (new String(c,0, readed)));
        }catch (IOException e){
            e.printStackTrace();
        }
        //方式3, stream 读写byte[]
        try{
            FileOutputStream outputStream1 = new FileOutputStream("ioText3.txt");
            outputStream1.write("这是一段文字".getBytes());
            outputStream1.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        try{
            File f = new File("ioText3.txt");
            FileInputStream inputStream = new FileInputStream(f);
            byte[] b = new byte[(int)f.length()];
            int len = inputStream.read(b);
            System.out.println("stream read byte[]: " + (new String(b, 0, len)));
        }catch (IOException e){
            e.printStackTrace();
        }

        //编码, 解码
        try{
            String str = "123abcABC?¶一二三";
            System.out.print("\t\t\t");
            for(char c:str.toCharArray()) {
                System.out.print(c + ",\t");
            }
            System.out.println("");
            System.out.print("bytes: \n\t\t\t");
            for(byte b:str.getBytes()){
                System.out.print(b + ",\t");
            }
            System.out.print("\n\t\t\t");
            for(byte b:str.getBytes()){
                System.out.print((b&0xff) + ",\t");
            }


            System.out.println("");
            System.out.println("ASCII: \n\t\t" + getBytes(str.getBytes("ASCII")));
            System.out.println("iso8859: \n\t\t" + getBytes(str.getBytes("ISO-8859-1")));
            System.out.println("gb2312: \n\t\t" + getBytes(str.getBytes("GB2312")));
            System.out.println("gbk: \n\t\t" + getBytes(str.getBytes("GBK")));
            //utf-8自适应由1-6个字节来表示, 一般汉字是两个字节
            System.out.println("utf-8: \n\t\t" + getBytes(str.getBytes("UTF-8")));
            //utf-16强制第个字符用两个字节来表示
            System.out.println("utf-16: \n\t\t" + getBytes(str.getBytes("UTF-16")));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //转16进制
    String getBytes(byte[] b){
        String str = "", str1 = "", str16 = "";
        for(byte item:b){
            str += "\t" + item;
            str1 += "\t" + (item&0xff);
            str16 += "\t" + Integer.toHexString(item&0xFF);
        }
        return str + "\n\t\t" + str1 + "\n\t\t" + str16;
    }
}
