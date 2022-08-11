package com.shang.javatest.ACM.CHAR;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author baj
 * @creat 2022-05-18 22:43
 */
public class MainH {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            String[] s = in.nextLine().split(" ");
            Arrays.sort(s);
            for (int i = 0; i < s.length; i++) {
                System.out.println(s[i]);
            }
        }
    }
}
//public class MainH {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
////        int n = in.nextInt();
//        //读取一行数据
////        in.nextLine();
//        while(in.hasNext()){
//            String[] s = in.nextLine().split(" ");
//            Arrays.sort(s);
//            for (int i = 0; i < s.length; i++) {
//                System.out.println(s[i] + " ");
//            }
//        }
//    }
//}


//import java.util.*;
//public class Main {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
//        in.nextLine();
//        while (in.hasNext()) { // 注意 while 处理多个 case
//            String[] s = in.nextLine().split(" ");
//            Arrays.sort(s);
//            for (int i = 0; i < s.length; i++) {
//                System.out.println(s[i] + " ");
//            }
//
//        }
//    }
//}

