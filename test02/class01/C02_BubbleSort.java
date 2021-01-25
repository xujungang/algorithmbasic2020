package class01;

import common.ArrayUtils;

/**
 * 冒泡排序
 * 0 ~ N-1 最大值往后移动
 * 0 ~ N-2 最大值往后移动
 * ...
 * 时间复杂度: O(N^2)
 * 空间复杂度: O(1)
 *
 * @author xujungang
 * @date 2021-01-23
 */
public class C02_BubbleSort {

    public static void main(String[] args) {
        int[] arr = ArrayUtils.createIntArray("1,5,3,2,7,4,9");
        ArrayUtils.printArray(arr);
        bubbleSort(arr);
        ArrayUtils.printArray(arr);
    }

    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 2; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
