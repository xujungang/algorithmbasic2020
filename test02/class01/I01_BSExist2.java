package class01;

import common.ArrayUtils;

/**
 * 二分法: 查找元素位置
 * 特殊规则: 将一个有序数组从任意一点切成两部分交换后合并成一个数组.例如561234
 *
 * @author xujungang
 * @date 2021-01-23
 */
public class I01_BSExist2 {
    public static void main(String[] args) {
        int[] arr = ArrayUtils.createIntArray("5,6,7,8,9,1,2,3");
//        // 验证查找split点
//        System.out.println(findSplit(ArrayUtils.createIntArray("1,2,3,4")));
//        System.out.println(findSplit(ArrayUtils.createIntArray("5,6,1,2,3,4")));
        System.out.println(exist(arr, 7) + ", 2");
        System.out.println(exist(arr, 2) + ", 6");
        System.out.println(exist(arr, 10) + ", -1");
    }

    public static int exist(int[] arr, int value) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        // 找到切分点
        int split = findSplit(arr);
        // 先查询左边
        int result = exist(arr, 0, split, value);
        if (result != -1) {
            return result;
        }
        // 查询右边
        result = exist(arr, split + 1, arr.length - 1, value);

        return result;
    }

    private static int exist(int[] arr, int L, int R, int X) {
        while (L <= R) {
            int M = L + ((R - L) >> 1);
            if (arr[M] == X) {
                return M;
            } else if (arr[M] > X) {
                R = M - 1;
            } else {
                L = M + 1;
            }
        }
        return -1;
    }

    private static int findSplit(int[] arr) {
        int L = 0, R = arr.length - 1;
        while (L <= R) {
            int M = L + ((R - L) >> 1);
            if (M - 1 >= 0 && arr[M - 1] > arr[M]) {
                return M - 1;
            } else if (M + 1 <= arr.length - 1 && arr[M] > arr[M + 1]) {
                return M;
            } else if (arr[M] > arr[L]) {
                // 在右侧找
                L = M + 1;
            } else {
                R = M - 1;
            }
        }
        return arr.length - 1;
    }

    private static int search(int[] arr, int L, int R, int X) {
        return 0;
    }

}
