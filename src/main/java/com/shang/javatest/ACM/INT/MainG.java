package com.shang.javatest.ACM.INT;

import java.util.Scanner;

/**
 * @author baj
 * @creat 2022-05-18 22:31
 */
public class MainG {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            //使用 nextLine 表示读取一行数据
            String[] s = in.nextLine().split(" ");
            int sum = 0;
            for (int i = 0; i < s.length; i++) {
                sum += Integer.parseInt(s[i]);
            }
            System.out.println(sum);
        }
    }
}
//import java.util.Scanner;
//public class Main {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        while (in.hasNextLine()) { // 注意 while 处理多个 case
//            String[] s = in.nextLine().split(" ");
//            int sum = 0;
//            for (int i = 0; i < s.length; i++) {
//                sum += Integer.parseInt(s[i]);
//            }
//            System.out.println(sum);
//        }
//    }
//}

