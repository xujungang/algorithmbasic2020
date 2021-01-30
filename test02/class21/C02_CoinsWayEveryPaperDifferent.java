package class21;

import common.ArrayUtils;

/**
 * arr是货币数组，其中的值都是正数。再给定一个正数aim。
 * 每个值都认为是一张货币，
 * 即便是值相同的货币也认为每一张都是不同的，
 * 返回组成aim的方法数
 * 例如：arr = {1,1,1}，aim = 2
 * 第0个和第1个能组成2，第1个和第2个能组成2，第0个和第2个能组成2
 * 一共就3种方法，所以返回3
 *
 * @author xujungang
 * @date 2021-01-30
 */
public class C02_CoinsWayEveryPaperDifferent {

    public static void main(String[] args) {
        int[] arr = ArrayUtils.createIntArray("1,1,1");
//        System.out.println(coinsWayEveryPaperDifferent(arr, 2));
        System.out.println(dp(arr, 2));
    }

    public static int coinsWayEveryPaperDifferent(int[] arr, int aim) {
        return process(arr, 0, aim);
    }

    public static int process(int[] arr, int index, int rest) {
        if (rest < 0) {
            return 0;
        }
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        return 0;
    }

    public static int dp(int[] arr, int aim) {
        int n = arr.length;
        int[][] dp = new int[n + 1][aim + 1];
        dp[n][0] = 1;
        for (int i = n - 1; i >= 0; i--) {
            for (int r = 0; r <= aim; r++) {
                int p1 = dp[i + 1][r];
                int p2 = r - arr[i] < 0 ? 0 : dp[i + 1][r - arr[i]];
                dp[i][r] = p1 + p2;
            }
        }
        return dp[0][aim];
    }
}
