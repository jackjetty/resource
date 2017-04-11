package cn.rising.util;  
import java.io.File;
import java.io.FileInputStream;   
import java.io.FileNotFoundException;   
import java.io.FileOutputStream;   
import java.io.IOException;   
import java.io.InputStream;   
import java.io.OutputStream;   
import java.net.URISyntaxException;
import java.util.Properties;    
  
  
/**  
* @author  
* @version  
*/   
public class  PropertyUtil {   
    //属性文件的路径   
    static String profilename="store-info.properties"; 
    static String profiledir="";
    //static String profiledir=PropertyUtil.class.getClassLoader().getResource(profilename).toURI().getPath();
    static File  profilefile=null;
    /**  
    * 采用静态方法  
    */   
    
   static{  
 
  	  try{
  		profiledir=PropertyUtil.class.getClassLoader().getResource("").toURI().getPath(); 
  		profiledir=new File(profiledir).getParent() ; 
    	profilefile =new File(profiledir,profilename);
  		if (!profilefile.exists()) {  
  			profilefile.createNewFile();  
        }  
  	  }catch(Exception ex){
  		ex.printStackTrace();
  	  }
  	  
  	  
   }
  
    /**  
    * 读取属性文件中相应键的值  
    * @param key  
    *            主键  
    * @return String  
    */   
    public static String getKeyValue(String key) { 
    	Properties props = new Properties();   
    	try {   
        	//InputStream inputStream = PropertyUtil.class.getClassLoader().getResourceAsStream(profilename);  
        	InputStream inputStream=new FileInputStream(profilefile) ; 
    		props.load(inputStream);       
        } catch (FileNotFoundException e) {   
            e.printStackTrace();   
            return null;
        } catch (IOException e) {          
            return null;
        }   
    	 
        return  props.getProperty(key,"");   
    }   
  
    
      
    /**  
    * 更新（或插入）一对properties信息(主键及其键值)  
    * 如果该主键已经存在，更新该主键的值；  
    * 如果该主键不存在，则插件一对键值。  
    * @param keyname 键名  
    * @param keyvalue 键值  
    */   
    public static void writeProperties(String keyname,String keyvalue) { 
    	Properties props = new Properties(); 
        try {   
        	//String filePath = PropertyUtil.class.getClassLoader().getResource(profilename).toURI().getPath();
            OutputStream fos = new FileOutputStream(profilefile);    
            props.setProperty(keyname, keyvalue);   
            // 以适合使用 load 方法加载到 Properties 表中的格式，   
            // 将此 Properties 表中的属性列表（键和元素对）写入输出流   
            props.store(fos, "Update '" + keyname + "' value");   
        } catch (Exception e) {   
            e.printStackTrace();
        }   
    }   
  
    /**  
    * 更新properties文件的键值对  
    * 如果该主键已经存在，更新该主键的值；  
    * 如果该主键不存在，则插件一对键值。  
    * @param keyname 键名  
    * @param keyvalue 键值  
    */   
    public static void updateProperties(String keyname,String keyvalue) {  
    	Properties props = new Properties(); 
        try {   
        	InputStream inputStream = new FileInputStream(profilefile) ; 
        	props.load(inputStream);   
            // 调用 Hashtable 的方法 put，使用 getProperty 方法提供并行性。   
            // 强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
        	 OutputStream fos = new FileOutputStream(profilefile);  
        	
        	
            props.setProperty(keyname, keyvalue);   
            // 以适合使用 load 方法加载到 Properties 表中的格式，   
            // 将此 Properties 表中的属性列表（键和元素对）写入输出流   
            props.store(fos, "Update '" + keyname + "' value");   
        } catch (Exception e) {   
        	e.printStackTrace();
             
        }   
    }   
    //测试代码   
    public static void main(String[] args) throws URISyntaxException {  
    	 
    	
    	 
         
    	//readValue("mail.properties", "MAIL_SERVER_PASSWORD");   
    	 updateProperties("MAIL_SERVER_INCOMING", "327@qq.com");  
    	 System.out.println(getKeyValue("MAIL_SERVER_INCOMING"));
    	 System.out.println("操作完成");   
    }   
}   