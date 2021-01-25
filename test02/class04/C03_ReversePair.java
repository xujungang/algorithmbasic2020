package class04;

import common.ArrayUtils;

/**
 * 逆序对
 * 在一个数组中，任何一个前面的数a，和任何一个后面的数b，
 * 如果(a,b)是降序的，就称为逆序对
 * 返回数组中所有的逆序对
 * 3 1 0 4 3 1
 * 逆序对:
 * 3: (3, 1), (3, 0), (3, 1)
 * 1: (1, 0)
 * 0: 无
 * 4: (4, 3), (4, 1)
 * 3: (3, 1)
 * 1: 无
 * <p>
 * 解题思路: 计算右侧有多少个数比自己小
 * 1.从右往左merge
 * 2.相等先复制右边
 *
 * @author xujungang
 * @date 2021-01-24
 */
public class C03_ReversePair {

    public static void main(String[] args) {
        int[] arr = ArrayUtils.createIntArray("3,1,0,4,3,1");
        ArrayUtils.printArray(arr);
        System.out.println(reversePairNumber(arr));
        ArrayUtils.printArray(arr);
    }

    public static int reversePairNumber(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    public static int process(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int mid = L + ((R - L) >> 1);
        int left = process(arr, L, mid);
        int right = process(arr, mid + 1, R);
        return left + right + merge(arr, mid, L, R);
    }

    public static int merge(int[] arr, int M, int L, int R) {
        int[] help = new int[R - L + 1];
        int hi = 0, li = L, ri = M + 1, ans = 0;
        while (li <= M && ri <= R) {
            if (arr[li] <= arr[ri]) {
                help[hi++] = arr[li++];
            } else {
                ans += M - li + 1;
                help[hi++] = arr[ri++];
            }
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
        return ans;
    }
}
