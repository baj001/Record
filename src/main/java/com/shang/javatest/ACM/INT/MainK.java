package com.shang.javatest.ACM.INT;

import java.util.Scanner;

/**
 * @author baj
 * @creat 2022-05-19 19:06
 */
public class MainK {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNextLong()){
            Long a = in.nextLong();
            Long b = in.nextLong();
            System.out.println(a + b);
        }
    }
}


//public class Main {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        while (in.hasNextLong()) { // 注意 while 处理多个 case
//            Long a = in.nextLong();
//            Long b = in.nextLong();
//            System.out.println(a + b);
//        }
//    }
//}
