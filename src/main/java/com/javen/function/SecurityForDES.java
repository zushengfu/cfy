
    package com.javen.function;  
      
    import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;  
    import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;  
    import java.io.OutputStream;  
    import java.security.Key;  
    import java.security.SecureRandom;  
      
    import javax.crypto.Cipher;  
    import javax.crypto.CipherInputStream;  
    import javax.crypto.CipherOutputStream;  
    import javax.crypto.KeyGenerator;
import javax.imageio.ImageIO;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;  
      
    public class SecurityForDES {   
      private static BASE64Encoder encoder = new sun.misc.BASE64Encoder();  
      private static BASE64Decoder decoder = new sun.misc.BASE64Decoder(); 
      Key key;   
      public SecurityForDES(String str) {   
        getKey(str);//生成密匙   
      }   
      /**  
      * 根据参数生成KEY  
      */   
      public void getKey(String strKey) {   
        try {   
            KeyGenerator _generator = KeyGenerator.getInstance("DES");   
            _generator.init(new SecureRandom(strKey.getBytes()));   
            this.key = _generator.generateKey();   
            _generator = null;   
        } catch (Exception e) {   
            throw new RuntimeException("Error initializing SqlMap class. Cause: " + e);   
        }   
      }   
      /**  
      * 文件file进行加密并保存目标文件destFile中  
      *  
      * @param file   要加密的文件 如c:/test/srcFile.txt  
      * @param destFile 加密后存放的文件名 如c:/加密后文件.txt  
      */   
      public void encrypt(String file, String destFile) throws Exception {   
        Cipher cipher = Cipher.getInstance("DES");   
        // cipher.init(Cipher.ENCRYPT_MODE, getKey());   
        cipher.init(Cipher.ENCRYPT_MODE, this.key);   
        InputStream is = new FileInputStream(file);   
        OutputStream out = new FileOutputStream(destFile);   
        CipherInputStream cis = new CipherInputStream(is, cipher);   
        byte[] buffer = new byte[1024];   
        int r;   
        while ((r = cis.read(buffer)) > 0) {   
            out.write(buffer, 0, r);   
        }    
        cis.close();   
        is.close();   
        out.close();   
      }   
      /**  
      * 文件采用DES算法解密文件  
      *  
      * @param file 已加密的文件 如c:/加密后文件.txt  
      *         * @param destFile   
      *         解密后存放的文件名 如c:/ test/解密后文件.txt  
      */   
      public void decrypt(String file, String dest) throws Exception {   
        Cipher cipher = Cipher.getInstance("DES");   
        cipher.init(Cipher.DECRYPT_MODE, this.key);   
        InputStream is = new FileInputStream(file);   
        OutputStream out = new FileOutputStream(dest);   
        CipherOutputStream cos = new CipherOutputStream(out, cipher);   
        byte[] buffer = new byte[1024];   
        int r;   
        while ((r = is.read(buffer)) >= 0) {   
            System.out.println();  
            cos.write(buffer, 0, r);   
        }   
        cos.close();   
        out.close();   
        is.close();   
      }   
      /** 
       * 将图片转换为BASE64加密字符串. 
       * @param imagePath 图片路径. 
       * @param format 图片格式. 
       * @return 
       */  
      @SuppressWarnings("restriction")
	public String ImageToByte(String imagePath, String format) {  
          File file = new File(imagePath);  
          BufferedImage bi = null;  
          ByteArrayOutputStream baos = null;  
          String result = null;  
          try {  
              bi = ImageIO.read(file);  
              baos = new ByteArrayOutputStream();  
              ImageIO.write(bi, format == null ? "jpg" : format, baos);  
              byte[] bytes = baos.toByteArray();  
//              OutputStream out = new FileOutputStream(destinationImagePath);
//              out.write(bytes);
              result = encoder.encodeBuffer(bytes).trim();  
              System.out.println("将图片转换为BASE64加密字符串成功！");  
          } catch (IOException e) {  
              System.out.println("将图片转换为 BASE64加密字符串失败: " + e);  
          } finally {  
              try {  
                  if(baos != null) {  
                      baos.close();  
                      baos = null;  
                  }  
              } catch (Exception e) {  
                  System.out.println("关闭文件流发生异常: " + e);  
              }  
          }  
          return result;  
      }  
        
      /** 
       * 将BASE64加密字符串转换为图片. 
       * @param base64String 
       * @param imagePath 图片生成路径. 
       * @param format 图片格式. 
       */  
      @SuppressWarnings("restriction")
	public void ByteToImage(String base64String, String imagePath, String format) {  
          byte[] bytes = null;  
          ByteArrayInputStream bais = null;  
          BufferedImage bi = null;  
          File file = null;  
          try {  
              bytes = decoder.decodeBuffer(base64String);  
              bais = new ByteArrayInputStream(bytes);  
              bi = ImageIO.read(bais);  
              file = new File(imagePath);  
              ImageIO.write(bi, format == null ? "jpg" : format, file);  
              System.out.println("将BASE64加密字符串转换为图片成功！");  
          } catch (IOException e) {  
              System.out.println("将BASE64加密字符串转换为图片失败: " + e);  
          } finally {  
              try { 
                  if(bais != null) {  
                      bais.close();  
                      bais = null;  
                  }  
              } catch (Exception e) {  
                  System.out.println("关闭文件流发生异常: " + e);  
              }  
          }  
      }  
    }  