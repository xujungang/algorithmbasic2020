package class22;

/**
 * 给定3个参数，N，M，K
 * 怪兽有N滴血，等着英雄来砍自己
 * 英雄每一次打击，都会让怪兽流失[0~M]的血量
 * 到底流失多少？每一次在[0~M]上等概率的获得一个值
 * 求K次打击之后，英雄把怪兽砍死的概率
 *
 * @author xujungang
 * @date 2021-01-31
 */
public class C01_KillMonster {

    public static void main(String[] args) {
        System.out.println(killMonster(5, 3, 2));
        System.out.println(dp(5, 3, 2));
    }

    public static double killMonster(int N, int M, int K) {
        if (N <= 0) {
            return 1;
        }
        return process(N, M, K) / Math.pow(M + 1, K);
    }

    /**
     * @param rest  怪兽剩余血
     * @param M     每次输出砍掉怪兽0~M血
     * @param times 剩余出刀次数
     * @return
     */
    public static int process(int rest, int M, int times) {
        if (times == 0) {
            return rest <= 0 ? 1 : 0;
        } else if (rest <= 0) { // 剪枝
            return (int) Math.pow(M + 1, times);
        }
        int ways = 0;
        for (int m = 0; m <= M; m++) {
            ways += process(rest - m, M, times - 1);
        }
        return ways;
    }

    public static double dp(int N, int M, int K) {
        if (N <= 0) {
            return 1;
        }
        int[][] dp = new int[K + 1][N + 1];
        dp[0][0] = 1;
        for (int t = 1; t <= K; t++) {
            dp[t][0] = (int) Math.pow(M + 1, t);
            for (int r = 1; r <= N; r++) {
//                // 未优化:
//                int ways = 0;
//                for (int m = 0; m <= M; m++) {
//                    if (r - m >= 0) {
//                        ways += dp[t - 1][r - m];
//                    } else {
//                        ways += Math.pow(M + 1, t - 1); // 不是很理解
//                    }
//                }
//                dp[t][r] = ways;
                // 优化后:
                dp[t][r] = dp[t][r - 1] + dp[t - 1][r];
                if (r - M - 1 >= 0) {
                    dp[t][r] -= dp[t - 1][r - M - 1];
                } else {
                    dp[t][r] -= Math.pow(M + 1, t - 1); // 不是很理解
                }
            }
        }
        return dp[K][N] / Math.pow(M + 1, K);
    }
}
