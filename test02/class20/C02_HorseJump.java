package class20;

/**
 * 请同学们自行搜索或者想象一个象棋的棋盘，
 * 然后把整个棋盘放入第一象限，棋盘的最左下角是(0,0)位置
 * 那么整个棋盘就是横坐标上9条线、纵坐标上10条线的区域
 * 给你三个 参数 x，y，k
 * 返回“马”从(0,0)位置出发，必须走k步
 * 最后落在(x,y)上的方法数有多少种?
 *
 * @author xujungang
 * @date 2021-01-30
 */
public class C02_HorseJump {

    public static void main(String[] args) {
        int x = 7;
        int y = 7;
        int step = 10;
        // 答案: 515813
        System.out.println(jump(x, y, step));
        System.out.println(dp(x, y, step));
    }

    /**
     * 暴力递归
     *
     * @param x
     * @param y
     * @param k k步骤
     */
    public static int jump(int x, int y, int k) {
        return process(0, 0, x, y, k);
    }

    /**
     * @param a    当前位置X轴
     * @param b    当前位置Y轴
     * @param x
     * @param y
     * @param rest 还剩几步
     * @return
     */
    public static int process(int a, int b, int x, int y, int rest) {
        if (a < 0 || a > 8 || b < 0 || b > 9) { // 出界
            return 0;
        }
        if (rest == 0) {    // base
            return a == x && b == y ? 1 : 0;
        }
        int ways = 0;
        // 从12点顺时针
        // 第1种情况:
        ways += process(a + 1, b - 2, x, y, rest - 1);
        // 第2种情况:
        ways += process(a + 2, b - 1, x, y, rest - 1);
        // 第3种情况:
        ways += process(a + 2, b + 1, x, y, rest - 1);
        // 第4种情况:
        ways += process(a + 1, b + 2, x, y, rest - 1);
        // 第5种情况:
        ways += process(a - 1, b + 2, x, y, rest - 1);
        // 第6种情况:
        ways += process(a - 2, b + 1, x, y, rest - 1);
        // 第7种情况:
        ways += process(a - 2, b - 1, x, y, rest - 1);
        // 第8种情况:
        ways += process(a - 1, b - 2, x, y, rest - 1);
        return ways;
    }

    public static int dp(int x, int y, int k) {
        int[][][] dp = new int[9][10][k + 1];
        dp[x][y][0] = 1;
        for (int rest = 1; rest <= k; rest++) {
            for (int a = 0; a < 9; a++) {
                for (int b = 0; b < 10; b++) {
                    int ways = 0;
                    // 从12点顺时针
                    // 第1种情况:
                    ways += pick(dp, a + 1, b - 2, rest - 1);
                    // 第2种情况:
                    ways += pick(dp, a + 2, b - 1, rest - 1);
                    // 第3种情况:
                    ways += pick(dp, a + 2, b + 1, rest - 1);
                    // 第4种情况:
                    ways += pick(dp, a + 1, b + 2, rest - 1);
                    // 第5种情况:
                    ways += pick(dp, a - 1, b + 2, rest - 1);
                    // 第6种情况:
                    ways += pick(dp, a - 2, b + 1, rest - 1);
                    // 第7种情况:
                    ways += pick(dp, a - 2, b - 1, rest - 1);
                    // 第8种情况:
                    ways += pick(dp, a - 1, b - 2, rest - 1);
                    dp[a][b][rest] = ways;
                }
            }
        }
        return dp[0][0][k];
    }

    public static int pick(int[][][] dp, int a, int b, int rest) {
        if (a < 0 || a > 8 || b < 0 || b > 9) { // 出界
            return 0;
        }
        return dp[a][b][rest];
    }
}
