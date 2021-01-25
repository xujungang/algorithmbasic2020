package class04;

import common.ArrayUtils;

/**
 * 在一个数组中，对于每个数num，求有多少个后面的数 * 2 依然<num，求总个数
 * 比如：[3,1,7,0,2]
 * 3的后面有：1，0
 * 1的后面有：0
 * 7的后面有：0，2
 * 0的后面没有
 * 2的后面没有
 * 所以总共有5个
 * <p>
 * 解题思路:
 * 如果右侧符合条件,右侧向右滑动,直到滑不动为止,符合的个数就是[M+1, R)即 R - M - 1
 *
 * @author xujungang
 * @date 2021-01-24
 */
public class C04_BiggerThanRightTwice {

    public static void main(String[] args) {
        int[] arr = ArrayUtils.createIntArray("3,1,7,0,2");
        ArrayUtils.printArray(arr);
        System.out.println(biggerTwice(arr));
        ArrayUtils.printArray(arr);
    }

    public static int biggerTwice(int[] arr) {
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
        return process(arr, L, mid)
                + process(arr, mid + 1, R)
                + merge(arr, mid, L, R);
    }

    public static int merge(int[] arr, int M, int L, int R) {
        int[] help = new int[R - L + 1];
        int hi = 0, li = L, ri = M + 1, ans = 0;
        while (li <= M) {
            while (ri <= R && arr[li] > arr[ri] * 2) {
                ri++;
            }
            ans += ri - (M + 1);
            li++;
        }
        li = L;
        ri = M + 1;
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
        return ans;
    }
}
