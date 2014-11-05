package network;

/**
 * Created by tencent on 14/11/3.
 */
import java.net.*;
import java.io.*;

public class URLDemo {
    public static void main(String args[]){
        //URL
        try{
            System.out.println("start loading...");
            URL url = new URL("http://www.qq.com");
            InputStreamReader reader = new InputStreamReader(url.openStream(), "GB2312");
            BufferedReader bufferedReader = new BufferedReader(reader);
            String inputLine, inputAll = "";
            while((inputLine = bufferedReader.readLine())!= null){
                inputAll += inputLine + "\n";
            }
            reader.close();
            //System.out.println(inputAll);
            File file = new File("qq.com.html");
            FileWriter writer = new FileWriter(file);
            writer.write(inputAll);
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        //URLConnection
        try{
            URL url = new URL("http://www.qq.com");
            URLConnection connection = url.openConnection();
            InputStreamReader reader = new InputStreamReader(connection.getInputStream(), "GB2312");
            int c;
            StringBuffer buffer = new StringBuffer();
            while((c = reader.read()) != -1){
                buffer.append((char)c);
            }
            reader.close();
            //System.out.println(buffer.toString());
            File f = new File("baidu.com.html");
            FileWriter writer = new FileWriter(f);
            writer.write(buffer.toString());
            writer.close();
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
