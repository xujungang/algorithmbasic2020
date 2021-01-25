package class05;

import common.ArrayUtils;

import java.util.Arrays;

/**
 * 快速排序
 *
 * @author xujungang
 * @date 2021-01-24
 */
public class C02_PartitionAndQuickSort {

    public static void main(String[] args) {
        int[] arr = ArrayUtils.createIntArray("7,3,1,5,2,2,4");
        ArrayUtils.printArray(arr);
        System.out.println("快排1.0:");
        int[] arr1 = Arrays.copyOf(arr, arr.length);
        quickSort1(arr1);
        ArrayUtils.printArray(arr1);
        System.out.println("快排2.0:");
        int[] arr2 = Arrays.copyOf(arr, arr.length);
        quickSort2(arr2);
        ArrayUtils.printArray(arr2);
        System.out.println("快排3.0:");
        int[] arr3 = Arrays.copyOf(arr, arr.length);
        quickSort3(arr3);
        ArrayUtils.printArray(arr3);
    }

    public static void quickSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process1(arr, 0, arr.length - 1);
    }

    public static void process1(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        int mid = partition(arr, L, R);
        process1(arr, L, mid - 1);
        process1(arr, mid + 1, R);
    }

    public static void quickSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process2(arr, 0, arr.length - 1);
    }

    public static void process2(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        int[] is = netherlandsFlag(arr, L, R);
        process2(arr, L, is[0] - 1);
        process2(arr, is[1] + 1, R);
    }

    public static void quickSort3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process3(arr, 0, arr.length - 1);
    }

    public static void process3(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        int random = (int) (Math.random() * (R - L + 1));
        swap(arr, L + random, R);
        int[] is = netherlandsFlag(arr, L, R);
        process3(arr, L, is[0] - 1);
        process3(arr, is[1] + 1, R);
    }

    public static int partition(int[] arr, int L, int R) {
        if (L == R) {
            return L;
        }
        int less = L;
        for (int i = L; i < R; i++) {
            if (arr[i] <= arr[R]) {
                swap(arr, i, less);
                less++;
            }
        }
        swap(arr, less, R);
        return less;
    }

    public static int[] netherlandsFlag(int[] arr, int L, int R) {
        if (L == R) {
            return new int[]{L, R};
        }
        int less = L;
        int more = R;
        int i = L;
        while (i < more) {
            if (arr[i] < arr[R]) {
                swap(arr, i, less);
                less++;
                i++;
            } else if (arr[i] > arr[R]) {
                swap(arr, i, --more);
            } else {
                i++;
            }
        }
        swap(arr, more, R);
        return new int[]{less, more};
    }

    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
