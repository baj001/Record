package fkshang.com.sorting;
import java.util.Arrays;

/**
 * @author baj
 * @creat 2022-06-03 10:25
 */
//public class Merge {
//    /**
//     * 归并排序
//     * 列表大小等于或小于该大小，将优先于 mergeSort 使用插入排序
//     */
//    private static final int INSERTION_SORT_THRESHOLD = 7;
//
//    public int[] sortArray(int[] nums) {
//        int len = nums.length;
//        int[] temp = new int[len];
//        mergeSort(nums, 0, len - 1, temp);
//        return nums;
//    }
//    /**
//     * 对数组 nums 的子区间 [left, right] 进行归并排序
//     * @param temp  用于合并两个有序数组的辅助数组，全局使用一份，避免多次创建和销毁
//     */
//    private void mergeSort(int[] nums, int left, int right, int[] temp) {
//        // 小区间使用插入排序
//        if (right - left <= INSERTION_SORT_THRESHOLD) {
//            insertionSort(nums, left, right);
//            return;
//        }
//
//        int mid = left + (right - left) / 2;
//        // Java 里有更优的写法，在 left 和 right 都是大整数时，即使溢出，结论依然正确
//        // int mid = (left + right) >>> 1;
//        mergeSort(nums, left, mid, temp);
//        mergeSort(nums, mid + 1, right, temp);
//        // 如果数组的这个子区间本身有序，无需合并
//        if (nums[mid] <= nums[mid + 1]) {
//            return;
//        }
//        mergeOfTwoSortedArray(nums, left, mid, right, temp);
//    }
//
//    /**
//     * 对数组 arr 的子区间 [left, right] 使用插入排序
//     */
//    private void insertionSort(int[] arr, int left, int right) {
//        for (int i = left + 1; i <= right; i++) {
//            int temp = arr[i];
//            int j = i;
//            while (j > left && arr[j - 1] > temp) {
//                arr[j] = arr[j - 1];
//                j--;
//            }
//            arr[j] = temp;
//        }
//    }
//
//    /**
//     * 合并两个有序数组：先把值复制到临时数组，再合并回去
//     */
//    private void mergeOfTwoSortedArray(int[] nums, int left, int mid,
//                                       int right, int[] temp) {
//        System.arraycopy(nums, left, temp, left, right + 1 - left);
//
//        int i = left;
//        int j = mid + 1;
//
//        for (int k = left; k <= right; k++) {
//            if (i == mid + 1) {
//                nums[k] = temp[j];
//                j++;
//            } else if (j == right + 1) {
//                nums[k] = temp[i];
//                i++;
//            } else if (temp[i] <= temp[j]) {
//                // 注意写成 < 就丢失了稳定性（相同元素原来靠前的排序以后依然靠前）
//                nums[k] = temp[i];
//                i++;
//            } else {
//                // temp[i] > temp[j]
//                nums[k] = temp[j];
//                j++;
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        int[] nums = {5,2,1,7,4,8,12,10};
//        Merge merge = new Merge();
//        int[] res = merge.sortArray(nums);
//        System.out.println(Arrays.toString(res));
//    }
//}
public class Merge {
    //归并排序
    private static final int INSERTION_SORT_THRESHOLD = 7;

    public int[] sortArray(int[] nums) {

        return mergeSort(nums, 0, nums.length - 1);

    }

    public int[] mergeSort(int[] nums, int left, int right) {

        if (right - left <= INSERTION_SORT_THRESHOLD) {
            insertionSort(nums, left, right);
            return nums;
        }

        //递归退出条件
        //如果左指针大于右指针，就退出循环
        //经过左右拆分，数组元素形成单个元素的树
        if (left >= right) {
            return nums;
        }
        //数组中的中位数
        int mid = left + (right - left) / 2;
        // Java 里有更优的写法，在 left 和 right 都是大整数时，即使溢出，结论依然正确
        // int mid = (left + right) >>> 1;
        //数组左拆分
        mergeSort(nums, left, mid);
        //数组右拆分
        mergeSort(nums, mid + 1, right);
        //数组合并，将单个元素进行合并
        return merge(nums, left, mid, right);
    }

    private void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int temp = arr[i];
            int j = i;
            while (j > left && arr[j - 1] > temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    public int[] merge(int[] nums, int left, int mid, int right) {
        //定义一个临时数组，存储排序好的元素
        int[] temp = new int[right - left + 1];
        //左排序的元素数组的左指针
        int i = left;
        //右排序的元素数组的左指针
        int j = mid + 1;
        //定义一个指向临时数组的左指针
        int t = 0;
        //循环判断条件
        //左数组到mid，右数组到right
        //左右数组都有元素的时候，进行比较
        while (i <= mid && j <= right) {
            //取左右数组中较小的元素，填入临时数组中
            //并将较小元素所在数组的左指针和临时数组的左指针都一起右移
            if (nums[i] <= nums[j]) {
                temp[t++] = nums[i++];
            } else {
                temp[t++] = nums[j++];
            }
        }
        //当左右数组其中一个数组没有元素的时候
        //如果左数组中还有剩余元素，就将剩余元素全部加入到临时数组中
        while (i <= mid) {
            temp[t++] = nums[i++];
        }
        //如果有数组中还有元素，就将剩余元素全部加入到临时数组中
        while (j <= right) {
            temp[t++] = nums[j++];
        }
        //将临时数组的元素复制到原数组中去
        for (int k = 0; k < temp.length; k++) {
            //特别注意这便nums数组起始位置要从 k+left开始
            //原因在加右数组的时候，起始位置要加left
            //这里不理解，直接把它记住。
            nums[left + k] = temp[k];
        }
        //返回原数组
        return nums;
    }
    public static void main(String[] args){
//        int[] test = {1,6,3,8,2,9,12,6,89,2,78};
        int[] test = {1,6,3,8};
        Merge merge = new Merge();
        int[] res = merge.sortArray(test);
        System.out.println(Arrays.toString(res));
    }
}
