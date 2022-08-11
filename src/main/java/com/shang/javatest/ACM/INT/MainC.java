package com.shang.javatest.ACM.INT;

import java.util.Scanner;

/**
 * @author baj
 * @creat 2022-05-18 22:12
 */
public class MainC {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while(in.hasNextInt()){
            int a = in.nextInt();
            int b = in.nextInt();
            if(a == 0 && b == 0){
                System.out.println("接收到两个0，即将结束");
                break;
            }
            System.out.println(a+b);
        }
    }
}


//import java.util.Scanner;
//public class Main {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//
//        while (in.hasNextInt()) { // 注意 while 处理多个 case
//            int a = in.nextInt();
//            int b = in.nextInt();
//            if(a ==0 && b == 0) break;
//            System.out.println(a + b);
//        }
//    }
//}
