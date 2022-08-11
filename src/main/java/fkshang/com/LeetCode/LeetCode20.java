package fkshang.com.LeetCode;

/**
 * @author baj
 * @creat 2022-06-17 9:48
 */
import java.util.*;
public class LeetCode20 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            String arr = in.nextLine();
            boolean res = isValid(arr);
            System.out.println(res);
        }
    }
    public static boolean isValid(String s) {
        Deque<Character> deque = new LinkedList<>();
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '('){
                deque.push(')');
            }else if(s.charAt(i) == '{'){
                deque.push('}');
            }else if(s.charAt(i) == '['){
                deque.push(']');
            }else if(deque.isEmpty() || deque.peek() != s.charAt(i)){
                return false;
            }else{
                deque.pop();
            }
        }
        return deque.isEmpty();
    }
}
