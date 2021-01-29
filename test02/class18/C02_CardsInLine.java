package class18;

import common.ArrayUtils;

/**
 * 范围上尝试的模型
 * 给定一个整型数组arr，代表数值不同的纸牌排成一条线，
 * 玩家A和玩家B依次拿走每张纸牌，
 * 规定玩家A先拿，玩家B后拿，
 * 但是每个玩家每次只能拿走最左或最右的纸牌，
 * 玩家A和玩家B都绝顶聪明。请返回最后获胜者的分数。
 *
 * @author xujungang
 * @date 2021-01-28
 */
public class C02_CardsInLine {

    public static void main(String[] args) {
        int[] arr = ArrayUtils.createIntArray("5,7,4,5,8,1,6,0,3,4,6,1,7");
        // 32
        System.out.println(win1(arr, 0, arr.length - 1));
        System.out.println(win2(arr, 0, arr.length - 1));
        System.out.println(win3(arr));
    }

    /**
     * 暴力递归
     * 1.先手取派最大值f方法,后手取派最大值g方法
     *
     * @param arr
     * @param L
     * @param R
     * @return
     */
    public static int win1(int[] arr, int L, int R) {
        int f = f1(arr, L, R);
        int g = g1(arr, L, R);
        return Math.max(f, g);
    }

    public static int f1(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L];
        }
        int l = arr[L] + g1(arr, L + 1, R); // 先拿左
        int r = arr[R] + g1(arr, L, R - 1); // 先拿右
        return Math.max(l, r);
    }

    public static int g1(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int l = f1(arr, L + 1, R);  // 先手拿了左
        int r = f1(arr, L, R - 1);  // 先手拿了右
        return Math.min(l, r);
    }

    /**
     * 暴力递归 + 缓存
     *
     * @param arr
     * @param L
     * @param R
     * @return
     */
    public static int win2(int[] arr, int L, int R) {
        int[][] fmap = new int[R + 1][R + 1];
        int[][] gmap = new int[R + 1][R + 1];
        for (int i = 0; i < R + 1; i++) {
            for (int j = 0; j < R + 1; j++) {
                fmap[i][j] = -1;
                gmap[i][j] = -1;
            }
        }
        int f = f(arr, L, R, fmap, gmap);
        int g = g(arr, L, R, fmap, gmap);
        return Math.max(f, g);
    }

    public static int f(int[] arr, int L, int R, int[][] fmap, int[][] gmap) {
        if (fmap[L][R] != -1) {
            return fmap[L][R];
        }
        int ans;
        if (L == R) {
            ans = arr[L];
        } else {
            int l = arr[L] + g1(arr, L + 1, R); // 先拿左
            int r = arr[R] + g1(arr, L, R - 1); // 先拿右
            ans = Math.max(l, r);
        }
        fmap[L][R] = ans;
        return ans;
    }

    public static int g(int[] arr, int L, int R, int[][] fmap, int[][] gmap) {
        if (gmap[L][R] != -1) {
            return gmap[L][R];
        }
        int ans;
        if (L == R) {
            ans = 0;
        } else {
            int l = f1(arr, L + 1, R);  // 先手拿了左
            int r = f1(arr, L, R - 1);  // 先手拿了右
            ans = Math.min(l, r);
        }
        gmap[L][R] = ans;
        return ans;
    }

    /**
     * 动态规划
     *
     * @param arr
     * @return
     */
    public static int win3(int[] arr) {
        /*
        arr = 5, 2, 3, 4
        行: L; 列: R
        fmap:                   gmap:
            0   1   2   3           0   1   2   3
        0   5                   0   0
        1   X   2               1   X   0
        2   X   X   3           2   X   X   0
        3   X   X   X   4       3   X   X   X   0
         */
        int N = arr.length;
        int[][] fmap = new int[N][N];
        int[][] gmap = new int[N][N];
        for (int colStart = 1; colStart < N; colStart++) {
            int l = 0;
            int r = colStart;
            while (r < N) {
                fmap[l][r] = Math.max(arr[l] + gmap[l + 1][r], arr[r] + gmap[l][r - 1]);
                gmap[l][r] = Math.min(fmap[l + 1][r], fmap[l][r - 1]);
                l++;
                r++;
            }
        }
        return Math.max(fmap[0][N - 1], gmap[0][N - 1]);
    }
}
