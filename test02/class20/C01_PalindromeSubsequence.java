package class20;

/**
 * 给定一个字符串str，返回这个字符串的最长回文子序列长度
 * 比如 ： str = “a12b3c43def2ghi1kpm”
 * 最长回文子序列是“1234321”或者“123c321”，返回长度7
 * 方法1(未封装): 将str逆序,与str取最大公共子序列
 * <p>
 * https://leetcode-cn.com/problems/longest-palindromic-subsequence/
 *
 * @author xujungang
 * @date 2021-01-30
 */
public class C01_PalindromeSubsequence {

    public static void main(String[] args) {
        // 答案: 7
        System.out.println(longestPalindromeSubseq1("a12b3c43def2ghi1kpm"));
        System.out.println(longestPalindromeSubseq2("a12b3c43def2ghi1kpm"));
    }

    /**
     * 暴力递归
     *
     * @param str
     * @return
     */
    public static int longestPalindromeSubseq1(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        return process(str.toCharArray(), 0, str.length() - 1);
    }

    public static int process(char[] chs, int L, int R) {
        if (L == R) {// 还剩1个元素,肯定是回文
            return 1;
        } else if (L == R - 1) {    // 还剩两个元素,相同肯定是回文,不同,其中一个元素算作回文
            return chs[L] == chs[R] ? 2 : 1;
        }
        // 第一种情况: 没有L, 没有R 回文
        int p1 = process(chs, L + 1, R - 1);
        // 第二中情况: 有L, 没有R 回文
        int p2 = process(chs, L, R - 1);
        // 第三种情况: 没有L, 有R 回文
        int p3 = process(chs, L + 1, R);
        // 第四种情况: 有L, 有R 回文 要求chs[L] == chs[R]
        int p4 = chs[L] != chs[R] ? 0 : 2 + process(chs, L + 1, R - 1);
        return Math.max(Math.max(p1, p2), Math.max(p3, p4));
    }

    /**
     * 动态规划
     *
     * @param str
     * @return
     */
    public static int longestPalindromeSubseq2(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] chs = str.toCharArray();
        int N = chs.length;
        int[][] dp = new int[N][N];
        /*
            0   1   2   3
        0   1  2:1
        1       1  2:1
        2           1  2:1
        3               1
         */
        dp[N - 1][N - 1] = 1;
        for (int i = 0; i < N - 1; i++) {
            dp[i][i] = 1;
            dp[i][i + 1] = chs[i] == chs[i + 1] ? 2 : 1;
        }
        for (int L = N - 3; L >= 0; L--) {
            for (int R = L + 2; R < N; R++) {
//                // 未优化版本
//                // 第一种情况: 没有L, 没有R 回文
//                int p1 = dp[L + 1][R - 1];
//                // 第二中情况: 有L, 没有R 回文
//                int p2 = dp[L][R - 1];
//                // 第三种情况: 没有L, 有R 回文
//                int p3 = dp[L + 1][R];
//                // 第四种情况: 有L, 有R 回文 要求chs[L] == chs[R]
//                int p4 = chs[L] != chs[R] ? 0 : 2 + dp[L + 1][R - 1];
//                dp[L][R] = Math.max(Math.max(p1, p2), Math.max(p3, p4));
                // 优化版本:
                // 当前节点dp[L][R] 大于等于 左(dp[L][R - 1]) 右(dp[L + 1][R]) 左下(dp[L + 1][R - 1])
                // 左dp[L][R - 1]  大于等于 左(dp[L][R - 2]) 右(dp[L + 1][R - 1]) 左下(dp[L + 1][R - 2])
                // 当前节点左下 等于 左节点的下, 所以不需要比较第一种情况
                dp[L][R] = Math.max(dp[L][R - 1], dp[L + 1][R]);    // 比较左和下,取最大值
                if (chs[L] == chs[R]) { // chs[L] == chs[R],需要再与 左下+2 比较一次
                    dp[L][R] = Math.max(dp[L][R], dp[L + 1][R - 1] + 2);
                }
            }
        }
        return dp[0][N - 1];
    }
}
