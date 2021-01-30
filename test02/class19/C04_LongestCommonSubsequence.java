package class19;

/**
 * 给定两个字符串str1和str2，
 * 返回这两个字符串的最长公共子序列长度
 * 比如 ： str1 = “a12b3c456d”,str2 = “1ef23ghi4j56k”
 * 最长公共子序列是“123456”，所以返回长度6
 * https://leetcode-cn.com/problems/longest-common-subsequence/
 *
 * @author xujungang
 * @date 2021-01-30
 */
public class C04_LongestCommonSubsequence {

    public static void main(String[] args) {
        // 答案: 3
        System.out.println(longestCommonSubsequence1("abcde", "ace"));
        System.out.println(longestCommonSubsequence2("abcde", "ace"));
    }

    /**
     * 暴力递归
     *
     * @param text1
     * @param text2
     * @return
     */
    public static int longestCommonSubsequence1(String text1, String text2) {
        if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) {
            return 0;
        }
        char[] str1 = text1.toCharArray();
        char[] str2 = text2.toCharArray();
        return process(str1, str2, str1.length - 1, str2.length - 1);
    }

    /**
     * 0...i 和0...j公共子序列多少个
     *
     * @param str1 字符串1
     * @param str2 字符串2
     * @param i    0...i
     * @param j    0...j
     * @return 公共子序列个数
     */
    public static int process(char[] str1, char[] str2, int i, int j) {
        if (i == 0 && j == 0) {
            return str1[i] == str2[j] ? 1 : 0;
        } else if (i == 0) {
            if (str1[i] == str2[j]) {
                return 1;
            } else {
                return process(str1, str2, i, j - 1);
            }
        } else if (j == 0) {
            if (str1[i] == str2[j]) {
                return 1;
            } else {
                return process(str1, str2, i - 1, j);
            }
        }
        // 第一种情况: str1 的i 不可能, str2 的j 可能
        int p1 = process(str1, str2, i - 1, j);
        // 第二中情况: str1 的i 可能, str2 的j 不可能
        int p2 = process(str1, str2, i, j - 1);
        // 第三种情况: str1 的i 必有, str2 的j 必有
        int p3 = str1[i] == str2[j] ? 1 + process(str1, str2, i - 1, j - 1) : 0;
        return Math.max(Math.max(p1, p2), p3);
    }

    /**
     * 动态规划
     *
     * @param text1
     * @param text2
     * @return
     */
    public static int longestCommonSubsequence2(String text1, String text2) {
        if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) {
            return 0;
        }
        char[] str1 = text1.toCharArray();
        char[] str2 = text2.toCharArray();
        int[][] dp = new int[str1.length][str2.length];
        // i == 0 && j == 0
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        // i == 0 && j != 0;
        for (int j = 1; j < str2.length; j++) {
            dp[0][j] = str1[0] == str2[j] ? 1 : dp[0][j - 1];
        }
        // i != 0 && j == 0
        for (int i = 1; i < str1.length; i++) {
            dp[i][0] = str1[i] == str2[0] ? 1 : dp[i - 1][0];
        }
        for (int i = 1; i < str1.length; i++) {
            for (int j = 1; j < str2.length; j++) {
                // 第一种情况: str1 的i 不可能, str2 的j 可能
                int p1 = dp[i - 1][j];
                // 第二中情况: str1 的i 可能, str2 的j 不可能
                int p2 = dp[i][j - 1];
                // 第三种情况: str1 的i 必有, str2 的j 必有
                int p3 = str1[i] == str2[j] ? 1 + dp[i - 1][j - 1] : 0;
                dp[i][j] = Math.max(Math.max(p1, p2), p3);
            }
        }
        return dp[str1.length - 1][str2.length - 1];
    }
}
