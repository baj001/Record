package com.shang.javatest.IPToInt;

/**
 * @author baj
 * @creat 2022-06-12 15:51
 */
import java.util.*;

/**
 * IP地址转十进制数 十进制数转IP地址
 *
 * @author huadekai
 *
 */

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            String str = scan.nextLine();
            // 判断输入是IP地址还是十进制数
            if (str.contains(".")) {
                //将ip转化为int类型
                String[] arr = str.split("\\.");
                long[] ip = new long[arr.length];
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < arr.length; i++) {
                    ip[i] = Long.parseLong(arr[i]);
                    //转化为由二进制表示的无符号长整型值的 字符串 表示形式
                    String a = Long.toBinaryString(ip[i]);
                    //转成8位二进制 利用String.format()控制格式
                    String temp = String.format("%08d", Long.parseLong(a));
                    sb.append(temp);
                }
                long output = Long.parseLong(sb.toString(), 2);
                System.out.println(output);
            } else {
                //将int转化为ip
                String binaryChuan = Long.toBinaryString(Long.parseLong(str));
                int len = binaryChuan.length();
                StringBuilder sb = new StringBuilder(binaryChuan);
                // 不足32位的前面补0
                for (int i = 0; i < 32 - len; i++) {
                    sb.insert(0, "0");
                }
                String fin = sb.toString();
                long a = Long.valueOf(fin.substring(0, 8), 2);
                long b = Long.valueOf(fin.substring(8, 16), 2);
                long c = Long.valueOf(fin.substring(16, 24), 2);
                long d = Long.valueOf(fin.substring(24, 32), 2);
                System.out.println(a + "." + b + "." + c + "." + d);
            }
        }
    }
}
