package class01;

import common.ArrayUtils;

/**
 * 二分法: 找到>=value的最左位置
 *
 * @author xujungang
 * @date 2021-01-23
 */
public class C05_BSNearLeft {

    public static void main(String[] args) {
        int[] arr = ArrayUtils.createIntArray("1,2,2,2,3,4,5,6,6,6,7,8,9");
        ArrayUtils.printArray(arr);
        System.out.println("nearestLeft: 5 -> 6, " + nearestLeft(arr, 5));
        System.out.println("nearestLeft: 2 -> 1, " + nearestLeft(arr, 2));
        System.out.println("nearestLeft: 7 -> 10, " + nearestLeft(arr, 7));
        System.out.println("nearestLeft: 0 -> 0, " + nearestLeft(arr, 0));
        System.out.println("nearestLeft: 11 -> -1, " + nearestLeft(arr, 11));
    }

    public static int nearestLeft(int[] arr, int value) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        int L = 0, R = arr.length - 1, index = -1;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] >= value) {
                index = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return index;
    }
}
