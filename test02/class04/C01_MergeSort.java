package class04;

import common.ArrayUtils;

/**
 * 递归排序
 *
 * @author xujungang
 * @date 2021-01-24
 */
public class C01_MergeSort {

    public static void main(String[] args) {
        int[] arr = ArrayUtils.createIntArray("1,5,3,2,7,4,9");
        ArrayUtils.printArray(arr);
        mergeSort(arr);
        ArrayUtils.printArray(arr);
    }

    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        int mid = L + ((R - L) >> 1);
        process(arr, L, mid);
        process(arr, mid + 1, R);
        merge(arr, mid, L, R);
    }

    public static void merge(int[] arr, int M, int L, int R) {
        int[] help = new int[R - L + 1];
        int hi = 0, li = L, ri = M + 1;
        while (li <= M && ri <= R) {
            help[hi++] = arr[li] <= arr[ri] ? arr[li++] : arr[ri++];
        }
        while (li <= M) {
            help[hi++] = arr[li++];
        }
        while (ri <= R) {
            help[hi++] = arr[ri++];
        }
        for (int i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
    }
}
