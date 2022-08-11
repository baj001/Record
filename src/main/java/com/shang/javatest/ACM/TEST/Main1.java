package com.shang.javatest.ACM.TEST;

import java.util.Scanner;

/**
 * @author baj
 * @creat 2022-05-19 19:51
 */
public class Main1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
//        System.out.println("");
        int num = in.nextInt();
        for(int i = 0; i < num; i++){
            int n = in.nextInt();
            while(n > 0){
                System.out.println("剩余输入"+n+"个数");

            }
        }
    }
}

/*

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        for (int i = 0; i < num; i++){
            int n = in.nextInt();
            while(n-->0){
            //
            }
        }
    }

 */