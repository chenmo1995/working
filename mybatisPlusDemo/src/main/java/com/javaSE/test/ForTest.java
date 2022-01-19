package com.javaSE.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author fdn
 * @since 2021-12-15 14:37
 */
public class ForTest {
    public static void main(String[] args) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse("2005-12-15 15:30:23");
        String s = new SimpleDateFormat("yyyyMMdd").format(date);
        System.out.println(s);
    }
}
