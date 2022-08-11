package com.shang.javatest.standard.test;

import java.util.Scanner;

/**
 * @author baj
 * @creat 2022-05-22 8:58
 */
public class LeetCode151 {
    public static void main(String[] args){
        //输入一个字符串，输出反转后的字符串
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            //输入要处理的字符串
            String s = in.nextLine();
            String ans = reverseWords(s);
            System.out.println(ans);
        }

            //输入要处理的字符串
//            String s = in.nextLine();
//            String ans = reverseWords(s);
//            System.out.println(ans);

    }
        /**
         * 1.去除首尾以及中间多余空格
         * 2.反转整个字符串
         * 3.反转各个单词
         */
        public static String reverseWords(String s) {
            // 1.去除首尾以及中间多余空格
            StringBuilder sb = removeSpace(s);
            // 2.反转整个字符串
            reverseString(sb, 0, sb.length() - 1);
            // 3.反转各个单词
            reverseEachWord(sb);
            return sb.toString();
        }

        /**
         进行移除空格
         定义start和end，当start所在的索引对应的字符是空格的时候，将start自增，同理将end自减，直到start和end对应的索引所在的值都是非空格的时候，执行下一步
         将start的字符值赋值给c，如果c为非空格，或者
         */
        private static StringBuilder removeSpace(String s) {
            int start = 0;
            int end = s.length() - 1;
            while (s.charAt(start) == ' ') start++;
            while (s.charAt(end) == ' ') end--;
            StringBuilder sb = new StringBuilder();
            while (start <= end) {
                char c = s.charAt(start);
                //当进行多余空格的删除操作的时候，在经过了，上面两个while的删除前后的多余空格后，使用如下的if进行删除内部多余的空格
                //例如当内部出现了连续两个以上空格的形式时，说明后面的sb.charAt(sb.length() - 1) != ' '成立，将第一个空格加入到了sb中
                //当到了第二个空格的时候，由于第一次已经在sb中加入了一个空格，此时if内的两个判断都不成立，则第二个空格不会被加入到sb
                if (c != ' ' || sb.charAt(sb.length() - 1) != ' ') {
                    sb.append(c);
                }
                //此处若使用else包裹则会超时！！！！！！！
                start++;
            }
            return sb;
        }

        /**
         * 反转字符串指定区间[start, end]的字符
         */
        public static void reverseString(StringBuilder sb, int start, int end) {
            while (start < end) {
                char temp = sb.charAt(start);
                //setCharAt(int index,char ch),将指定的字符ch放到index指出的位置
                sb.setCharAt(start, sb.charAt(end));
                sb.setCharAt(end, temp);
                start++;
                end--;
            }
        }

        private static void reverseEachWord(StringBuilder sb) {
            int start = 0;
            int end = 1;
            int n = sb.length();
            while (start < n) {
                while (end < n && sb.charAt(end) != ' ') {
                    end++;
                }
                reverseString(sb, start, end - 1);
                start = end + 1;
                end = start + 1;
            }
        }


}
