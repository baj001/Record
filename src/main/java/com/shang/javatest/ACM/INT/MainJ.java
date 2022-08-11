package com.shang.javatest.ACM.INT;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author baj
 * @creat 2022-05-19 18:45
 */
public class MainJ {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNextLine()){
            String[] s = in.nextLine().split(",");
            Arrays.sort(s);
            for (int i = 0; i < s.length; i++) {
                System.out.println(s[i] + ",");
            }
            System.out.println(s[s.length - 1]);
        }
    }
}

//import java.util.*;
//public class Main {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        while (in.hasNextLine()) { // 注意 while 处理多个 case
//            String[] s = in.nextLine().split(",");
//            Arrays.sort(s);
//            int l = s.length;
//            for (int i = 0; i < l - 1; i++) {
//                System.out.print(s[i] + ",");
//            }
//            System.out.println(s[l-1]);
//        }
//    }
//}
