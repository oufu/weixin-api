package com.sanyka.weixin.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

//将 io 转换为 base64编码
//将 base64编码转换为 io 文件流，生成一幅新图片
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64 {
    BASE64Decoder decoder = new BASE64Decoder();

    public static String ioToBase64() throws IOException {
        String fileName = "C:/Users/yaoj/Desktop/Base64/gril.gif"; //源文件
        String strBase64 = null;
        try {
            InputStream in = new FileInputStream(fileName);
            // in.available()返回文件的字节长度
            byte[] bytes = new byte[in.available()];
            // 将文件中的内容读入到数组中
            in.read(bytes);
            strBase64 = new BASE64Encoder().encode(bytes);      //将字节流数组转换为字符串
            System.out.println("Base64: "+strBase64);
            in.close();
        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return strBase64;
    }

    public static String ioToBase64(File file) throws IOException {
        String strBase64 = null;
        try {
            InputStream in = new FileInputStream(file);
            // in.available()返回文件的字节长度
            byte[] bytes = new byte[in.available()];
            // 将文件中的内容读入到数组中
            in.read(bytes);
            strBase64 = new BASE64Encoder().encode(bytes);      //将字节流数组转换为字符串
            in.close();
        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return strBase64;
    }
    
    public static void base64ToIo(String strBase64) throws IOException {
        String string = strBase64;
        String fileName = "C:/Users/yaoj/Desktop/Base64/gril2.gif"; //生成的新文件
        try {
            // 解码，然后将字节转换为文件
            byte[] bytes = new BASE64Decoder().decodeBuffer(string);   //将字符串转换为byte数组
            ByteArrayInputStream in = new ByteArrayInputStream(bytes);
            byte[] buffer = new byte[1024];
            FileOutputStream out = new FileOutputStream(fileName);
            int bytesum = 0;
            int byteread = 0;
            while ((byteread = in.read(buffer)) != -1) {
                bytesum += byteread;
                out.write(buffer, 0, byteread); //文件写操作
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
    	base64ToIo(ioToBase64());
    	System.out.println("测试完毕");
	}
}