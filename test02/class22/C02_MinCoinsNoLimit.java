package class22;

/**
 * arr是面值数组，其中的值都是正数且没有重复。再给定一个正数aim。
 * 每个值都认为是一种面值，且认为张数是无限的。
 * 返回组成aim的最少货币数
 *
 * @author xujungang
 * @date 2021-01-31
 */
public class C02_MinCoinsNoLimit {

    public static void main(String[] args) {
        int[] arr = new int[]{2, 5, 10};
        System.out.println(minCoins(arr, 52));
        System.out.println(dp(arr, 52));
    }

    public static int minCoins(int[] arr, int aim) {
        return process(arr, 0, aim);
    }

    private static int process(int[] arr, int index, int rest) {
        if (rest == 0) {    // base
            return 0;
        } else if (index == arr.length) {   // 没有组合成功
            return Integer.MAX_VALUE;
        }
        int ans = Integer.MAX_VALUE;
        for (int zhang = 0; arr[index] * zhang <= rest; zhang++) {
            int next = process(arr, index + 1, rest - arr[index] * zhang);
            if (next != Integer.MAX_VALUE) {
                ans = Math.min(ans, next + zhang);
            }
        }
        return ans;
    }

    public static int dp(int[] arr, int aim) {
        int n = arr.length;
        int[][] dp = new int[n + 1][aim + 1];
        dp[n][0] = 0;
        for (int r = 1; r <= aim; r++) {
            dp[n][r] = Integer.MAX_VALUE;
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int r = 1; r <= aim; r++) {
//                // 未优化:
//                int ans = Integer.MAX_VALUE;
//                for (int zhang = 0; arr[i] * zhang <= r; zhang++) {
//                    int next = dp[i + 1][r - arr[i] * zhang];
//                    if (next != Integer.MAX_VALUE) {
//                        ans = Math.min(ans, next + zhang);
//                    }
//                }
//                dp[i][r] = ans;
                // 优化:
                dp[i][r] = dp[i + 1][r];
                if (r - arr[i] >= 0 && dp[i][r - arr[i]] != Integer.MAX_VALUE) {
                    dp[i][r] = Math.min(dp[i][r], dp[i][r - arr[i]] + 1);
                }
            }
        }
        return dp[0][aim];
    }
}
