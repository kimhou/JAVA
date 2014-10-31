package JAVA_IO;

/**
 * Created by tencent on 14/10/31.
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
        //方式1, 流式字节读写
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
