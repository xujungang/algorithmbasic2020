package class19;

/**
 * 给定两个长度都为N的数组weights和values，weights[i]和values[i]分别代表 i号物品的重量和价值。
 * 给定一个正数bag，表示一个载重bag的袋子，你装的物品不能超过这个重量。
 * 返回你能装下最多的价值是多少?
 *
 * @author xujungang
 * @date 2021-01-30
 */
public class C01_Knapsack {

    public static void main(String[] args) {
        int[] weights = {3, 2, 4, 7, 3, 1, 7};
        int[] values = {5, 6, 3, 19, 12, 4, 2};
        int bag = 15;
        // 答案: 42
        System.out.println(maxValue(weights, values, bag));
        System.out.println(dp(weights, values, bag));
    }

    /**
     * 暴力递归
     *
     * @param w
     * @param v
     * @param bag
     * @return
     */
    public static int maxValue(int[] w, int[] v, int bag) {
        return process(w, v, bag, 0);
    }

    /**
     * @param w     重量
     * @param v     价值
     * @param rest  bag剩余空间
     * @param index 当前下标
     */
    public static int process(int[] w, int[] v, int rest, int index) {
        if (rest < 0) {
            return -1;
        }
        if (index == w.length) {
            return 0;
        }
        int p1 = process(w, v, rest, index + 1);
        int next = process(w, v, rest - w[index], index + 1);
        int p2 = next == -1 ? 0 : v[index] + next;
        return Math.max(p1, p2);
    }

    /**
     * 动态规划
     *
     * @param w
     * @param v
     * @param bag
     * @return
     */
    public static int dp(int[] w, int[] v, int bag) {
        int N = w.length;
        /*
        行: index(0~N+1), 列: rest(0~bag)
         */
        int[][] dp = new int[N + 1][bag + 1];
        // dp[N][i] = 0, 数组默认值是0,无需赋值
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= bag; rest++) {
                int p1 = dp[index + 1][rest];
                int next = rest - w[index] < 0 ? -1 : dp[index + 1][rest - w[index]];
                int p2 = next == -1 ? 0 : v[index] + next;
                dp[index][rest] = Math.max(p1, p2);
            }
        }
        return dp[0][bag];
    }
}
