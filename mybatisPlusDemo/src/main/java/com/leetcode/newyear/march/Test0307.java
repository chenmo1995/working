package com.leetcode.newyear.march;

/**
 * @author fdn
 * @since 2022-03-07 21:13
 */
public class Test0307 {

    /**
     * 504. 七进制数
     *
     * @param num
     * @return
     */
    public String convertToBase7(int num) {
        if(num == 0){
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        boolean flag = num >= 0;
        num = (num >= 0) ? num : -num;
        while(num > 0){
            sb.insert(0 , num % 7);
            num /= 7;
        }
        return flag ? sb.toString() : ("-" + sb.toString());
    }
}
