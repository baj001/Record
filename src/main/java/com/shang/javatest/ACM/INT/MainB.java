package com.shang.javatest.ACM.INT;

import java.util.Scanner;

/**
 * @author baj
 * @creat 2022-05-18 22:02
 * 可以接收多组 a + b 输入的 num 为几，就可以接收几次
 */
public class MainB {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        //接收一个整数
        int num = in.nextInt();
        for(int i = 0; i < num; i++){
            int a = in.nextInt();
            int b = in.nextInt();
            System.out.println(a + b);
        }
//        System.out.println(num);
    }
}


//import java.util.Scanner;
//public class Main {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int num = in.nextInt();
//        for(int i = 0; i < num; i++) { // 注意 while 处理多个 case
//            int a = in.nextInt();
//            int b = in.nextInt();
//            System.out.println(a + b);
//        }
//    }
//}
