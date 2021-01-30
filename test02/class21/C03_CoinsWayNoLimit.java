package class21;

import common.ArrayUtils;

/**
 * arr是面值数组，其中的值都是正数且没有重复。再给定一个正数aim。
 * 每个值都认为是一种面值，且认为张数是无限的。
 * 返回组成aim的方法数
 * 例如：arr = {1,2}，aim = 4
 * 方法如下：1+1+1+1、1+1+2、2+2
 * 一共就3种方法，所以返回3
 *
 * @author xujungang
 * @date 2021-01-30
 */
public class C03_CoinsWayNoLimit {

    public static void main(String[] args) {
        int[] arr = ArrayUtils.createIntArray("1,2");
        System.out.println(coinsWayNoLimit(arr, 4));
        System.out.println(dp(arr, 4));
    }

    public static int coinsWayNoLimit(int[] arr, int aim) {
        return process(arr, 0, aim);
    }

    private static int process(int[] arr, int index, int rest) {
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        int ways = 0;
        for (int z = 0; arr[index] * z <= rest; z++) {
            ways += process(arr, index + 1, rest - arr[index] * z);
        }
        return ways;
    }

    private static int dp(int[] arr, int aim) {
        int n = arr.length;
        int[][] dp = new int[n + 1][aim + 1];
        dp[n][0] = 1;
        for (int i = n - 1; i >= 0; i--) {
            for (int r = 0; r <= aim; r++) {
                int ways = 0;
//                // 未优化
//                // 要当前张
//                for (int z = 0; arr[i] * z <= r; z++) {
//                    ways += process(arr, i + 1, r - arr[i] * z);
//                }
                /* 优化:
                行: index, 列: rest
                当前arr[index]=3元, rest =  14
                    0   1   2   3   4   5   6   7   8   9  10  11  12  13  14
                  0
                  1
                  2                                            bf          cur
                  3         E           D           C           B           A
                  cur = A + B + C + D + E
                  bf = B + C + D + E
                  所以: cur = A + bf
                 */
                ways += dp[i + 1][r] + (r - arr[i] < 0 ? 0 : dp[i][r - arr[i]]);

                dp[i][r] = ways;

            }
        }
        return dp[0][aim];
    }
}
