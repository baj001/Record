package fkshang.com.standard.test;

/**
 * @author baj
 * @creat 2022-06-16 23:40
 */
import java.util.*;
public class test616 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int row = in.nextInt();
        int col = in.nextInt();
        int[][] arr = new int[row][col];
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                arr[i][j] = in.nextInt();
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }
}
