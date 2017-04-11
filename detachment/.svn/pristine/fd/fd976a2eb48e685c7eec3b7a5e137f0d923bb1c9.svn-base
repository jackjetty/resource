package test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
public class ic{
	public static void readTxtFile(String filePath){
		String html="";
        try {
                String encoding="GBK";
                File file=new File(filePath);
                if(file.isFile() && file.exists()){ //判断文件是否存在
                    InputStreamReader read = new InputStreamReader(
                    new FileInputStream(file),encoding);//考虑到编码格式
                    BufferedReader bufferedReader = new BufferedReader(read);
                    String lineTxt = null;
                    while((lineTxt = bufferedReader.readLine()) != null){
                         html+=lineTxt;
                    }
                    read.close();
        }else{
            System.out.println("找不到指定的文件");
            return ;
        }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
            return ;
        }
        Document doc = Jsoup.parse(html); 
        //Element netPayEle= doc.getElementById("netPayForm0");
        String jdsbh=doc.select("input[name=jdsbh]").first().attr("value");
        String orderId=doc.select("input[name=orderId]").first().attr("value");
        String orderTime=doc.select("input[name=orderTime]").first().attr("value");		
        
        System.out.println(jdsbh);
        System.out.println(orderId);
        System.out.println(orderTime);
    }
	public static void main(String [] args)  {
		String filePath = "D:\\pic\\last.html";
 
        readTxtFile(filePath);
		  
	}
}