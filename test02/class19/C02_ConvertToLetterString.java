package class19;

/**
 * 规定1和A对应、2和B对应、3和C对应...26和Z对应
 * 那么一个数字字符串比如"111”就可以转化为:
 * "AAA"、"KA"和"AK"
 * 给定一个只有数字字符组成的字符串str，返回有多少种转化结果
 *
 * @author xujungang
 * @date 2021-01-30
 */
public class C02_ConvertToLetterString {

    public static void main(String[] args) {
        // 答案: 54
        System.out.println(number("7210231231232031203123"));
        System.out.println(dp("7210231231232031203123"));
    }

    /**
     * 暴力递归
     *
     * @param str
     * @return
     */
    public static int number(String str) {
        char[] chs = str.toCharArray();
        return process(chs, 0);
    }

    /**
     * 转化0...i-1
     *
     * @param chs
     * @param i
     * @return
     */
    public static int process(char[] chs, int i) {
        if (i == chs.length) {
            return 1;
        } else if (chs[i] == '0') {     // 上一次拆分是错误的
            return 0;
        }
        // 自己单转
        int ways = process(chs, i + 1);
        // 自己和下一个元素一起转
        if (i + 1 < chs.length && (chs[i] - '0') * 10 + (chs[i + 1] - '0') < 27) {
            ways += process(chs, i + 2);
        }
        return ways;
    }

    /**
     * 动态规划
     *
     * @param str
     * @return
     */
    public static int dp(String str) {
        char[] chs = str.toCharArray();
        int[] dp = new int[chs.length + 1];
        dp[chs.length] = 1;
        for (int i = chs.length - 1; i >= 0; i--) {
            if (chs[i] == '0') {
                continue;
            }
            // 自己单转
            int ways = dp[i + 1];
            // 自己和下一个元素一起转
            if (i + 1 < chs.length && (chs[i] - '0') * 10 + (chs[i + 1] - '0') < 27) {
                ways += dp[i + 2];
            }
            dp[i] = ways;
        }
        return dp[0];
    }
}
