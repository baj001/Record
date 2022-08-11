package com.shang.javatest.ACM.INT;

import java.util.Scanner;

/**
 * @author baj
 * @creat 2022-05-18 22:20
 */
public class MainD {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int n = in.nextInt();
            if (n == 0) break;
            int sum = 0;
            while (n > 0) {
                System.out.println("还需输入" + n + "个数字");
                sum += in.nextInt();
                n--;
            }
            System.out.println(sum);
        }

    }
}

//import java.util.Scanner;
//public class Main {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//
//        while (in.hasNextInt()) { // 注意 while 处理多个 case
//            int n = in.nextInt();
//            if(n == 0) break;
//            int sum = 0;
//            while (n > 0) {
//                sum += in.nextInt();
//                n--;
//            }
//            System.out.println(sum);
//        }
//    }
//}
