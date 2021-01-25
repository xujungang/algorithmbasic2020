package class01;

import common.ArrayUtils;

/**
 * 二分法: 查找元素是否存在
 *
 * @author xujungang
 * @date 2021-01-23
 */
public class C04_BSExist {

    public static void main(String[] args) {
        int[] arr = ArrayUtils.createIntArray("1,2,3,4,5,6,7,8,9");
        ArrayUtils.printArray(arr);
        System.out.println("exist: 5 -> 4, " + exist(arr, 5));
        System.out.println("exist: 2 -> 1, " + exist(arr, 2));
        System.out.println("exist: 7 -> 6, " + exist(arr, 7));
        System.out.println("exist: 0 -> -1, " + exist(arr, 0));
        System.out.println("exist: 11 -> -1, " + exist(arr, 11));
    }

    public static int exist(int[] arr, int value) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        int L = 0, R = arr.length - 1;
        while (L < R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] == value) {
                return mid;
            } else if (arr[mid] > value) {
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return -1;
    }
}
