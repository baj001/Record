package fkshang.com.LeetCode;

import java.util.Scanner;

/**
 * @author baj
 * @creat 2022-07-19 16:59
 * 接受一个字符串，会返回一个字符串
 */
public class LeetCode468 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            String str = in.nextLine();
            String res = validIPAddress(str);
            System.out.println(res);
        }
    }
    public static String validIPAddress(String ip) {
        // 在给定符串中查找另一个字符串
        if (ip.indexOf(".") >= 0 && check4(ip)) return "IPv4";
        if (ip.indexOf(":") >= 0 && check6(ip)) return "IPv6";
        return "Neither";
    }
    static boolean check4(String ip) {
        int n = ip.length(), cnt = 0;
        char[] cs = ip.toCharArray();
        for (int i = 0; i < n && cnt <= 3; ) {
            // 找到连续数字段，以 x 存储
            int j = i, x = 0;
            //计算当前段的数值 小于等于255
            /**
             12..35.12
             */
            while (j < n && cs[j] >= '0' && cs[j] <= '9' && x <= 255) x = x * 10 + (cs[j++] - '0');
            // 非 item 字符之间没有 item，也就是说出现了连续的 "." 。用于处理：12..35.12
            if (i == j) return false;
            // 含前导零 或 数值大于 255  用于处理：12.035.1.2
            if ((j - i > 1 && cs[i] == '0') || (x > 255)) return false;
            i = j + 1;
            if (j == n) continue;
            // 存在除 . 以外的其他非数字字符
            if (cs[j] != '.') return false;
            cnt++;
        }
        // 恰好存在 3 个不位于两端的 .
        return cnt == 3 && cs[0] != '.' && cs[n - 1] != '.';
    }
    static boolean check6(String ip) {
        int n = ip.length(), cnt = 0;
        char[] cs = ip.toCharArray();
        for (int i = 0; i < n && cnt <= 7; ) {
            int j = i;
            while (j < n && ((cs[j] >= 'a' && cs[j] <= 'f') ||
                    (cs[j] >= 'A' && cs[j] <= 'F') ||
                    (cs[j] >= '0' && cs[j] <= '9'))) j++;
            // 非 item 字符之间没有 item 或 长度超过 4
            //此时就是两种情况，首先是出现了连续的"."号，或者是字段的长度大于4了，因为ipv6包含字母，不像ipv4那样可以使用数字计算
            if (i == j || j - i > 4) return false;
            i = j + 1;
            if (j == n) continue;
            // 存在除 : 以外的其他非数字字符
            if (cs[j] != ':') return false;
            cnt++;
        }
        // 恰好存在 7 个不位于两端的 :
        return cnt == 7 && cs[0] != ':' && cs[n - 1] != ':';
    }

}
