package class01;

import common.ArrayUtils;

/**
 * 二分法: 找到<=value的最右位置
 *
 * @author xujungang
 * @date 2021-01-23
 */
public class C06_BSNearRight {

    public static void main(String[] args) {
        int[] arr = ArrayUtils.createIntArray("1,2,3,4,5,6,6,6,7,7,7,8,9");
        ArrayUtils.printArray(arr);
        System.out.println("nearestRight: 5 -> 4, " + nearestRight(arr, 5));
        System.out.println("nearestRight: 2 -> 1, " + nearestRight(arr, 2));
        System.out.println("nearestRight: 7 -> 10, " + nearestRight(arr, 7));
        System.out.println("nearestRight: 0 -> -1, " + nearestRight(arr, 0));
        System.out.println("nearestRight: 11 -> 12, " + nearestRight(arr, 11));
    }

    public static int nearestRight(int[] arr, int value) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        int L = 0, R = arr.length - 1, index = -1;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] <= value) {
                index = mid;
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }
        return index;
    }
}
