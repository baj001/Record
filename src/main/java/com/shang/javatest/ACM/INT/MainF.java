package com.shang.javatest.ACM.INT;

import java.util.Scanner;

/**
 * @author baj
 * @creat 2022-05-18 22:29
 */
public class MainF {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while(in.hasNextInt()){
            int n = in.nextInt();
            int sum = 0;
            while(n > 0){
                sum += in.nextInt();
//                System.out.println();
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
//        while (in.hasNextInt()) { // 注意 while 处理多个 case
//            int n = in.nextInt();
//            int sum = 0;
//            while (n > 0) {
//                sum += in.nextInt();
//                n--;
//            }
//            System.out.println(sum);
//        }
//    }
//}
