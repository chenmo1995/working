package com.javaSE;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * @author fdn
 * @since 2022-04-13 23:05
 */
public class PipedStream {
    public static void main(String[] args) throws IOException {
        // 创建管道
        PipedOutputStream out = new PipedOutputStream();
        PipedInputStream in = new PipedInputStream(out);


    }
}
