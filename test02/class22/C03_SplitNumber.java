package class22;

/**
 * 给定一个正数n，求n的裂开方法数，
 * 规定：后面的数不能比前面的数小
 * 比如4的裂开方法有：
 * 1+1+1+1、1+1+2、1+3、2+2、4
 * 5种，所以返回5
 *
 * @author xujungang
 * @date 2021-01-31
 */
public class C03_SplitNumber {

    public static void main(String[] args) {
        System.out.println(way(4));
        System.out.println(dp(4));
    }

    public static int way(int num) {
        return process(1, num);
    }

    private static int process(int pre, int rest) {
        if (rest == 0) {
            return 1;
        } else if (pre > rest) {
            return 0;
        }
        int ways = 0;
        for (int n = pre; n <= rest; n++) {
            ways += process(n, rest - n);
        }
        return ways;
    }

    public static int dp(int num) {
        int[][] dp = new int[num + 1][num + 1];
        for (int i = 1; i <= num; i++) {
            dp[i][0] = 1;
            dp[i][i] = 1;
        }
        for (int i = num - 1; i >= 1; i--) {
            for (int r = i + 1; r <= num; r++) {
//                // 未优化:
//                int ways = 0;
//                for (int n = i; n <= r; n++) {
//                    ways += dp[n][r - n];
//                }
//                dp[i][r] = ways;
                // 优化:
                dp[i][r] = dp[i + 1][r] + dp[i][r - i];
            }
        }
        return dp[1][num];
    }
}
