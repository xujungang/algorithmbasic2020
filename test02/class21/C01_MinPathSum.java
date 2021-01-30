package class21;

/**
 * 给定一个二维数组matrix，一个人必须从左上角出发，最后到达右下角
 * 沿途只可以向下或者向右走，沿途的数字都累加就是距离累加和
 * 返回最小距离累加和
 * <p>
 * 思路: 创建一个一样的矩阵dp[i][j]记录从0,0到当前位置的最优化路径.
 * 优化方案: 只要有一个数组长度等[j]
 *
 * @author xujungang
 * @date 2021-01-30
 */
public class C01_MinPathSum {

    public static void main(String[] args) {
        int[][] arr = new int[][]{
                {1, 3, 5, 9},
                {8, 1, 3, 4},
                {5, 0, 6, 1},
                {8, 8, 4, 0}
        };
        // 答案: 12
        System.out.println(minPathSum(arr));
        System.out.println(dp(arr));
    }

    public static int dp(int[][] arr) {
        int x = arr[0].length;
        int y = arr.length;
        int[] dp = new int[x];
        // 第一行完成
        dp[0] = arr[0][0];
        for (int i = 1; i < arr[0].length; i++) {
            dp[i] = dp[i - 1] + arr[0][i];
        }
        // 其余行
        for (int j = 1; j < arr.length; j++) {
            dp[0] = dp[0] + arr[j][0];
            for (int i = 1; i < arr[j].length; i++) {
                dp[i] = Math.min(dp[i - 1], dp[i]) + arr[i][j];
            }
        }
        return dp[y - 1];
    }

    /**
     * 暴力递归
     *
     * @param arr
     * @return
     */
    public static int minPathSum(int[][] arr) {
        return process(arr, 0, 0, arr[0].length - 1, arr.length - 1);
    }

    public static int process(int[][] arr, int a, int b, int x, int y) {
        if (a == x && b == y) {
            return arr[a][b];
        } else if (a == x) {
            return arr[a][b] + process(arr, a, b + 1, x, y);
        } else if (b == y) {
            return arr[a][b] + process(arr, a + 1, b, x, y);
        }
        // 往右走
        int p1 = process(arr, a + 1, b, x, y);
        // 往下走
        int p2 = process(arr, a, b + 1, x, y);
        return Math.min(p1, p2) + arr[a][b];
    }
}
