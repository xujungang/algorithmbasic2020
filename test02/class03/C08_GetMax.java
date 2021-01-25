package class03;

import common.ArrayUtils;

/**
 * 通过递归找到数组中的最大值
 *
 * @author xujungang
 * @date 2021-01-24
 */
public class C08_GetMax {

    public static void main(String[] args) {
        int[] arr = ArrayUtils.createIntArray("4,2,3,7,4,3,1");
        System.out.println(process(arr, 0, arr.length - 1));
    }

    public static int process(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L];
        }
        int mid = L + ((R - L) >> 1);
        int left = process(arr, L, mid);
        int right = process(arr, mid + 1, R);
        return Math.max(left, right);
    }
}
