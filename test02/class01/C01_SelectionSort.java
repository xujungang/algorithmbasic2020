package class01;

import common.ArrayUtils;

/**
 * 选择排序
 * 在0 ~ N-1中找到最小值跟0交换
 * 在1 ~ N-1中找到最小值跟1校验
 * ...
 * 时间复杂度: O(N^2)
 * 空间复杂度: O(1)
 *
 * @author xujungang
 * @date 2021-01-23
 */
public class C01_SelectionSort {

    public static void main(String[] args) {
        int[] arr = ArrayUtils.createIntArray("1,5,3,2,7,4,9");
        ArrayUtils.printArray(arr);
        selectionSort(arr);
        ArrayUtils.printArray(arr);
    }

    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);
        }
    }

    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
