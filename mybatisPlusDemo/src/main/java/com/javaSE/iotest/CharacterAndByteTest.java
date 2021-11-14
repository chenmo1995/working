package com.javaSE.iotest;

import java.io.*;

/**
 * 现有的UTF-8编码，中英文字符都是占两个字节
 *
 * 涉及到编码的地方一般都是在字符和字节相互转换上
 *
 * Reader类是Java的I/O中读取字符流的父类；InputStream是Java的I/O中读取字节流的父类
 * InputStreamReader 类就是关联字节到字符的桥梁，它负责在 I/O 过程中处理读取字节到字符的转换，
 * 而具体字节到字符的解码实现它由 StreamDecoder 去实现
 *
 * 写的情况也是类似，字符的父类是 Writer，字节的父类是 OutputStream，通过 OutputStreamWriter 转换字符到字节
 *
 * @author fdn
 * @since 2021-10-25 10:14
 */
public class CharacterAndByteTest {
    public static void main(String[] args) throws IOException {
        String file = "D:/E/working/mybatisPlusDemo/src/main/java/com/javaSE/iotest/stream.txt";
        String charset = "UTF-8";
        // 写字符换转成字节流
        FileOutputStream outputStream = new FileOutputStream(file);
        OutputStreamWriter writer = new OutputStreamWriter(
                outputStream, charset);
        try {
            writer.write("这是要保存的中文字符");
        } finally {
            writer.close();
        }
        // 读取字节转换成字符
        FileInputStream inputStream = new FileInputStream(file);
        InputStreamReader reader = new InputStreamReader(
                inputStream, charset);
        StringBuffer buffer = new StringBuffer();
        char[] buf = new char[64];
        int count = 0;
        try {
            while ((count = reader.read(buf)) != -1) {
                buffer.append(buffer, 0, count);
            }
        } finally {
            reader.close();
        }
    }
}
