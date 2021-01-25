package class01;

import common.ArrayUtils;

/**
 * 插入排序
 * 0 ~ 0 有序
 * 0 ~ 1 有序
 * ...
 * 时间复杂度: O(N^2)
 * 空间复杂度: O(1)
 *
 * @author xujungang
 * @date 2021-01-23
 */
public class C03_InsertionSort {

    public static void main(String[] args) {
        int[] arr = ArrayUtils.createIntArray("1,5,3,2,7,4,9");
        ArrayUtils.printArray(arr);
        insertionSort(arr);
        ArrayUtils.printArray(arr);
    }

    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j >= 0 && arr[j] < arr[j - 1]; j--) {
                swap(arr, j, j - 1);
            }
        }
    }

    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
