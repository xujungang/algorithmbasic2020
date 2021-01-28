package class08;

import common.ArrayUtils;

/**
 * 计数排序(桶排序): 一般来讲，计数排序要求，样本是整数，且范围比较窄
 *
 * @author xujungang
 * @date 2021-01-25
 */
public class C03_CountSort {

    public static void main(String[] args) {
        int[] arr = ArrayUtils.createIntArray("6,4,2,7,4,6,2,8,3,6,1");
        ArrayUtils.printArray(arr);
        countSort(arr); // 1,2,2,3,4,4,6,6,6,7,8
        ArrayUtils.printArray(arr);
    }

    public static void countSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int[] bucket = new int[max + 1];
        for (int i = 0; i < arr.length; i++) {
            bucket[arr[i]]++;
        }

        int index = 0;
        for (int i = 0; i < bucket.length; i++) {
            while (bucket[i]-- > 0) {
                arr[index++] = i;
            }
        }
    }
}
