package class21;

/**
 * 给定5个参数，N，M，row，col，k
 * 表示在N*M的区域上，醉汉Bob初始在(row,col)位置
 * Bob一共要迈出k步，且每步都会等概率向上下左右四个方向走一个单位
 * 任何时候Bob只要离开N*M的区域，就直接死亡
 * 返回k步之后，Bob还在N*M的区域的概率
 *
 * @author xujungang
 * @date 2021-01-30
 */
public class C05_BobDie {

    public static void main(String[] args) {
        // 答案: 0.9970207214355469
        System.out.println(bobDie(50, 50, 6, 6, 10) / Math.pow(4, 10));
        System.out.println(dp(50, 50, 6, 6, 10) / Math.pow(4, 10));
    }

    public static int dp(int m, int n, int x, int y, int k) {
        int[][][] dp = new int[m][n][k + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j][0] = 1;
            }
        }
        for (int rest = 1; rest <= k; rest++) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    int ways = 0;
                    ways += pick(dp, m, n, i, j - 1, rest - 1);
                    ways += pick(dp, m, n, i + 1, j, rest - 1);
                    ways += pick(dp, m, n, i, j + 1, rest - 1);
                    ways += pick(dp, m, n, i - 1, j, rest - 1);
                    dp[i][j][rest] = ways;
                }
            }
        }
        return dp[x][y][k];
    }

    public static int pick(int[][][] dp, int m, int n, int x, int y, int k) {
        if (x < 0 || x == m || y < 0 || y == n) {// 出界
            return 0;
        }
        return dp[x][y][k];
    }

    public static int bobDie(int m, int n, int x, int y, int k) {
        return process(m, n, x, y, k);
    }

    private static int process(int m, int n, int x, int y, int rest) {
        if (x < 0 || x == m || y < 0 || y == n) {// 出界
            return 0;
        }
        if (rest == 0) {    // 走完了
            return 1;
        }
        int ways = 0;
        ways += process(m, n, x, y - 1, rest - 1);
        ways += process(m, n, x + 1, y, rest - 1);
        ways += process(m, n, x, y + 1, rest - 1);
        ways += process(m, n, x - 1, y, rest - 1);
        return ways;
    }
}
