package class18;

/**
 * 从1~N的数组上.终点aim是K.当前机器人在C位置上.计算机器人走到终点的走法有几种
 *
 * @author xujungang
 * @date 2021-01-28
 */
public class C01_RobotWalk {

    public static void main(String[] args) {
        System.out.println("1~5,目标:4,机器人位置:2,正确结果: 4种:21234,23234,23434,23454");
        System.out.println(way1(2, 4, 4, 5));
        System.out.println(way2(2, 4, 4, 5));
        System.out.println(way3(2, 4, 4, 5));
    }

    /**
     * 暴力递归
     *
     * @param cur  当前位置
     * @param rest 还能走几步
     * @param aim  中年位置
     * @param N    边缘
     */
    public static int way1(int cur, int rest, int aim, int N) {
        return process1(cur, rest, aim, N);
    }

    public static int process1(int cur, int rest, int aim, int N) {
        if (rest == 0) {
            return cur == aim ? 1 : 0;
        } else if (cur == 1) {
            return process1(2, rest - 1, aim, N);
        } else if (cur == N) {
            return process1(N - 1, rest - 1, aim, N);
        } else {
            return process1(cur - 1, rest - 1, aim, N)
                    + process1(cur + 1, rest - 1, aim, N);
        }
    }

    /**
     * 暴力递归 + 缓存
     *
     * @param cur
     * @param rest
     * @param aim
     * @param N
     * @return
     */
    public static int way2(int cur, int rest, int aim, int N) {
        int[][] dp = new int[N + 1][rest + 1];
        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < rest + 1; j++) {
                dp[i][j] = -1;
            }
        }
        return process2(cur, rest, aim, N, dp);
    }

    public static int process2(int cur, int rest, int aim, int N, int[][] dp) {
        if (dp[cur][rest] != -1) {
            return dp[cur][rest];
        }
        int ans;
        if (rest == 0) {
            ans = cur == aim ? 1 : 0;
        } else if (cur == 1) {
            ans = process1(2, rest - 1, aim, N);
        } else if (cur == N) {
            ans = process1(N - 1, rest - 1, aim, N);
        } else {
            ans = process1(cur - 1, rest - 1, aim, N)
                    + process1(cur + 1, rest - 1, aim, N);
        }
        dp[cur][rest] = ans;
        return ans;
    }

    /**
     * 动态规划
     *
     * @param cur
     * @param rest
     * @param aim
     * @param N
     * @return
     */
    public static int way3(int cur, int rest, int aim, int N) {
        int[][] dp = new int[N + 1][rest + 1];
        /*
        行: 当前位置cur; 列: 剩余部署 rest
            0   1   2   3   4
        0   X   X   X   X   X
        1   0
        2   0
        3   0
        4   1
        5   0
         */
        dp[aim][0] = 1; // rest == 0, 只有cur == aim的位置才是1
        for (int i = 1; i <= rest; i++) {
            dp[1][i] = dp[2][i - 1];
            for (int j = 2; j < N; j++) {
                dp[j][i] = dp[j - 1][i - 1] + dp[j + 1][i - 1];
            }
            dp[N][i] = dp[N - 1][i - 1];
        }
        return dp[cur][rest];
    }
}
