package com.rising.mobilepayment;
import javax.crypto.*;  
  
import javax.crypto.spec.SecretKeySpec;  
  
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;  
  
   
  
public class EncryptUtils {  
  
	   public static void main(String[] args) {  
		   
		   
		   
		   //rechargeQQ 
		   //2015-08-20 16:44:23   FAC6F9F1F06D414A7F1D7D2EE628   429EEA6B13B1CAAC95606E227BA295886D7B650488F0474CE7102C7FF25DCF57CB76283BFE7E6D1E78A17BC8B5A28B1858DF3ECFDDC9B4F932F1683753715B9881341842AE884DE9F70A08431C6EDB07A543C86D040B5B888C23A4C5669A303FA3B72522AD154CAA21FD63322DCB08E77602FB235AD36979EDB8085119ABCE018FACCE67632E6C2331A60824C36E2F5A658CBEFECBDB0C4D0E9B8378DCA571902D561103226C13AD31A60824C36E2F5A6B8A5AA6D4242FF6EDB8085119ABCE01C8D71680B4DFC2D1A0EB9D5E169EE537B0F7502AB39859C3E97A1A53DF5DFA90D70A204A294755EDDCCFAD430302AE86A6B8C21D571BECF03DEAEB8133A1B306D811F1A537FEF85BEB687586A9F88FF47B088AAE2247DC520AF1EA3E138F9B6478068E2115C834DCE4ACD39FAB0352924B33482625FE0707ABE3A68B53FC2806C9D71F3154743331D41F725D3B13035A8C89CDF0392B185B4B19D253667CB3B01BCD9BC731E4DB2EBD8E20C15BD57482862730793FADC010DA9BFC41F1FB770BC27DFAC29D0A43AEA701CD43A6B519B50AF00DCB68C37DDC046F66EE8DFB5568
		  
	        String key = "BESTPAYQCOIN";  
	  
	        String password = "123456";   
	 
	  
	       try {  
	  
	            String encrypt = "";  
	  
	            String decrypt = "";  
	  
	            byte[] bkey = EncryptUtils.GetKeyBytes(key);  
	  
	            encrypt = EncryptUtils.byte2Base64(EncryptUtils.encryptMode(bkey, password.getBytes()));  
	  
	            System.out.println("用预转换密钥算加密结果=" + encrypt);  
	  
	            System.out.println("加密后base64表示=" + EncryptUtils.byte2hex(Base64.decode(encrypt)));  
	  
	            System.out.println("调用原始密钥算加密结果=" + EncryptUtils.Encrypt3DES(password, key));  
	  
	        try {  
	        	   
               
                decrypt = new String(EncryptUtils.decryptMode(bkey, Base64.decode(encrypt)));  
  
                System.out.println("用预转换密钥算解密结果=" + decrypt);  
  
                System.out.println("调用原始密钥算解密结果=" + EncryptUtils.Decrypt3DES(encrypt, key));  
  
            } catch (Exception ex) {  
  
                System.out.println("Exception:" + ex.getMessage());  
  
            }
	       
	  
	  
	        } catch (Exception ex) {  
	  
	            System.out.println("Exception:" + ex.getMessage());  
	  
	        }   
	  
	        System.out.println("----------示例结束：使用java写的算法加密解密-----------");  
	  
	    }  
  
    /// <summary>  
  
    /// 3des解码  
  
    /// </summary>  
  
    /// <param name="value">待解密字符串</param>  
  
    /// <param name="key">原始密钥字符串</param>  
  
    /// <returns></returns>  
  
    public static String Decrypt3DES(String value, String key) throws Exception {  
  
        byte[] b = decryptMode(GetKeyBytes(key), Base64.decode(value));  
  
        return new String(b);  
  
    }  
  
   
  
    /// <summary>  
  
    /// 3des加密  
  
    /// </summary>  
  
    /// <param name="value">待加密字符串</param>  
  
    /// <param name="strKey">原始密钥字符串</param>  
  
    /// <returns></returns>  
  
    public static String Encrypt3DES(String value, String key) throws Exception {  
  
        String str = byte2Base64(encryptMode(GetKeyBytes(key), value.getBytes()));  
  
        return str;  
  
    }  
  
   
  
    //计算24位长的密码byte值,首先对原始密钥做MD5算hash值，再用前8位数据对应补全后8位  
  
    public static byte[] GetKeyBytes(String strKey) throws Exception {  
  
        if (null == strKey || strKey.length() < 1)  
  
            throw new Exception("key is null or empty!");  
  
   
  
        java.security.MessageDigest alg = java.security.MessageDigest.getInstance("MD5");  
  
        alg.update(strKey.getBytes());  
  
        byte[] bkey = alg.digest();  
  
        System.out.println("md5key.length=" + bkey.length);  
  
        System.out.println("md5key=" + byte2hex(bkey));  
  
        int start = bkey.length;  
  
        byte[] bkey24 = new byte[24];  
  
        for (int i = 0; i < start; i++) {  
  
            bkey24[i] = bkey[i];  
  
        }  
  
        for (int i = start; i < 24; i++) {//为了与.net16位key兼容  
  
            bkey24[i] = bkey[i - start];  
  
        }  
  
   
  
        System.out.println("byte24key.length=" + bkey24.length);  
  
        System.out.println("byte24key=" + byte2hex(bkey24));  
  
        return bkey24;  
  
    }  
  
   
  
    private static final String Algorithm = "DESede"; //定义 加密算法,可用 DES,DESede,Blowfish         
  
   
  
    //keybyte为加密密钥，长度为24字节  
  
    //src为被加密的数据缓冲区（源）    
  
    public static byte[] encryptMode(byte[] keybyte, byte[] src) {  
  
        try {  
  
            //生成密钥  
  
            SecretKey deskey = new SecretKeySpec(keybyte, Algorithm); //加密   
  
            Cipher c1 = Cipher.getInstance(Algorithm);  
  
            c1.init(Cipher.ENCRYPT_MODE, deskey);  
  
            return c1.doFinal(src);  
  
       } catch (java.security.NoSuchAlgorithmException e1) {  
  
            e1.printStackTrace();  
  
        } catch (javax.crypto.NoSuchPaddingException e2) {  
  
            e2.printStackTrace();  
  
        } catch (java.lang.Exception e3) {  
  
            e3.printStackTrace();  
  
        }  
  
        return null;  
  
    }  
  
   
  
    //keybyte为加密密钥，长度为24字节    
  
    //src为加密后的缓冲区  
  
    public static byte[] decryptMode(byte[] keybyte, byte[] src) {  
  
        try { //生成密钥     
  
            SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);  
  
            //解密       
  
            Cipher c1 = Cipher.getInstance(Algorithm);  
  
            c1.init(Cipher.DECRYPT_MODE, deskey);  
  
            return c1.doFinal(src);  
  
        } catch (java.security.NoSuchAlgorithmException e1) {  
  
            e1.printStackTrace();  
  
        } catch (javax.crypto.NoSuchPaddingException e2) {  
  
            e2.printStackTrace();  
  
        } catch (java.lang.Exception e3) {  
  
            e3.printStackTrace();  
  
        }  
  
        return null;  
  
    }  
  
   
  
    //转换成base64编码  
  
    public static String byte2Base64(byte[] b) {  
  
        return Base64.encode(b);  
  
    }  
  
   
  
    //转换成十六进制字符串    
  
    public static String byte2hex(byte[] b) {  
  
        String hs = "";  
  
        String stmp = "";  
  
        for (int n = 0; n < b.length; n++) {  
  
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));  
  
            if (stmp.length() == 1)  
  
                hs = hs + "0" + stmp;  
  
            else  
  
                hs = hs + stmp;  
  
            if (n < b.length - 1)  
  
                hs = hs + ":";  
  
        }  
  
        return hs.toUpperCase();  
  
    }  
  
}  
  