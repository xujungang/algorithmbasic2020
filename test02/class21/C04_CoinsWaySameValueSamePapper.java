package class21;

import common.ArrayUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * arr是货币数组，其中的值都是正数。再给定一个正数aim。
 * 每个值都认为是一张货币，
 * 认为值相同的货币没有任何不同，
 * 返回组成aim的方法数
 * 例如：arr = {1,2,1,1,2,1,2}，aim = 4
 * 方法：1+1+1+1、1+1+2、2+2
 * 一共就3种方法，所以返回3
 *
 * @author xujungang
 * @date 2021-01-30
 */
public class C04_CoinsWaySameValueSamePapper {

    public static void main(String[] args) {
        int[] arr = ArrayUtils.createIntArray("1,2,1,1,2,1,2");
        System.out.println(coinsWaySameValueSamePapper(arr, 4));
        System.out.println(dp(arr, 4));
    }

    private static int dp(int[] arr, int aim) {
        Info info = getInfo(arr);
        int n = info.coins.length;
        int[][] dp = new int[n + 1][aim + 1];
        dp[n][0] = 1;
        for (int i = n - 1; i >= 0; i--) {
            for (int r = 0; r <= aim; r++) {
//                // 未优化
//                int ways = 0;
//                for (int z = 0; info.coins[i] * z <= r && z <= info.zhangs[i]; z++) {
//                    ways += process(info, i + 1, r - info.coins[i] * z);
//                }
//                dp[i][r] = ways;
                /* 优化:
                行: index, 列: rest
                当前arr[index]=3元, 3元张数 = 2, rest =  14
                    0   1   2   3   4   5   6   7   8   9  10  11  12  13  14
                  0
                  1
                  2                                            bf          cur
                  3         E           D           C           B           A
                  cur = A + B + C
                  bf = B + C + D
                  所以: cur = A + bf - D
                 */
                dp[i][r] = dp[i + 1][r];
                if (r - info.coins[i] >= 0) {
                    dp[i][r] += dp[i][r - info.coins[i]];
                }
                if (r - info.coins[i] * (info.zhangs[i] + 1) >= 0) {
                    dp[i][r] -= dp[i + 1][r - info.coins[i] * (info.zhangs[i] + 1)];
                }
            }
        }
        return dp[0][aim];
    }

    public static int coinsWaySameValueSamePapper(int[] arr, int aim) {
        Info info = getInfo(arr);
        return process(info, 0, aim);
    }

    private static int process(Info info, int index, int rest) {
        if (index == info.coins.length) {
            return rest == 0 ? 1 : 0;
        }
        int ways = 0;
        for (int z = 0; info.coins[index] * z <= rest && z <= info.zhangs[index]; z++) {
            ways += process(info, index + 1, rest - info.coins[index] * z);
        }
        return ways;
    }

    private static Info getInfo(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (!map.containsKey(arr[i])) {
                map.put(arr[i], 1);
            } else {
                map.put(arr[i], map.get(arr[i]) + 1);
            }
        }
        int[] coins = new int[map.size()];
        int[] zhangs = new int[map.size()];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            coins[i] = entry.getKey();
            zhangs[i] = entry.getValue();
            i++;
        }
        return new Info(coins, zhangs);
    }

    public static class Info {
        public int[] coins;
        public int[] zhangs;

        public Info(int[] c, int[] z) {
            coins = c;
            zhangs = z;
        }
    }
}
