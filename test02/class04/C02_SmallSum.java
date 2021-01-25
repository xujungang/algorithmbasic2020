package class04;

import common.ArrayUtils;

/**
 * 计算小和的总和
 * 在一个数组中，一个数左边比它小的数的总和，叫数的小和，所有数的小和累加起来，叫数组小和。求数组小和。
 * 例子： [1,3,4,2,5]
 * 1左边比1小的数：没有
 * 3左边比3小的数：1
 * 4左边比4小的数：1、3
 * 2左边比2小的数：1
 * 5左边比5小的数：1、3、4、 2
 * 所以数组的小和为1+1+3+1+1+3+4+2=16
 * <p>
 * 解题思路:采用归并排序,时间复杂度N(N*logN)
 * 1.merge期间,复制右侧不计算,复制左侧计算右侧当前指针P2与右侧终点期间有N各数,N*左侧迁移数
 * 2.数相等,先复制右组
 *
 * @author xujungang
 * @date 2021-01-24
 */
public class C02_SmallSum {

    public static void main(String[] args) {
        int[] arr = ArrayUtils.createIntArray("1,3,4,2,5"); // 16
        ArrayUtils.printArray(arr);
        System.out.println(smallSum(arr));
        ArrayUtils.printArray(arr);
        System.out.println("========================");
        arr = ArrayUtils.createIntArray("5,2,4,1,7,3,5,8"); // 0+0+2+0+12+3+10+27=54
        ArrayUtils.printArray(arr);
        System.out.println(smallSum(arr));
        ArrayUtils.printArray(arr);
    }

    public static int smallSum(int[] arr) {
        if (arr == null || arr.length == 0) {
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
            if (arr[li] < arr[ri]) {
                ans += arr[li] * (R - ri + 1);
                help[hi++] = arr[li++];
            } else {
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
