package com.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * VM Args： -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 *
 * 限制Java堆的大小为20MB， 不可扩展（将堆的最小值-Xms参数与最大值-Xmx参数
 * 设置为一样即可避免堆自动扩展） ，
 * 通过参数-XX： +HeapDumpOnOutOf-MemoryError可以让虚拟机
 * 在出现内存溢出异常的时候Dump出当前的内存堆转储快照以便进行事后分析
 *
 * @author fdn
 * @since 2021-09-04 16:46
 */
public class HeapOOM {
    static class OOMObject{

    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true){
            list.add(new OOMObject());
        }
    }
}
