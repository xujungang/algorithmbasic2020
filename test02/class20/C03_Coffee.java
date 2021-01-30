package class20;

import common.ArrayUtils;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给定一个数组arr，arr[i]代表第i号咖啡机泡一杯咖啡的时间
 * 给定一个正数N，表示N个人等着咖啡机泡咖啡，每台咖啡机只能轮流泡咖啡
 * 只有一台咖啡机，一次只能洗一个杯子，时间耗费a，洗完才能洗下一杯
 * 每个咖啡杯也可以自己挥发干净，时间耗费b，咖啡杯可以并行挥发
 * 假设所有人拿到咖啡之后立刻喝干净，
 * 返回从开始等到所有咖啡机变干净的最短时间
 * 三个参数：int[] arr、int N，int a、int b
 *
 * TODO 需要再好好理解下
 *
 * @author xujungang
 * @date 2021-01-30
 */
public class C03_Coffee {

    public static void main(String[] args) {
        // 洗: 1, 蒸发: 5, 人数: 6
        // 咖啡机: 10, 4, 8, 7, 1, 7, 3, 4, 6, 7,
        int[] arr = ArrayUtils.createIntArray("10,4,8,7,1,7,3,4,6,7");
        // 答案: 7
        System.out.println(coffee(arr, 6, 1, 5));
        System.out.println(dp(arr, 6, 1, 5));
    }

    /**
     * 暴力递归
     *
     * @param arr
     * @param N
     * @param wash
     * @param air
     * @return
     */
    public static int coffee(int[] arr, int N, int wash, int air) {
        // 计算所有人喝完咖啡时间数组
        int[] drink = getDrinkArr(arr, N);
        return process(drink, wash, air, 0, 0);
    }

    /**
     * @param drink 所有杯子开始洗的时间
     * @param wash  洗花费的时间
     * @param air   蒸发花费的时间
     * @param index 当前第几杯
     * @param waste 距离开始清洗了多长时间
     * @return
     */
    public static int process(int[] drink, int wash, int air, int index, int waste) {
        if (index == drink.length) {    // base
            return 0;
        }
        // 洗当前杯子
        int self1 = Math.max(drink[index], waste) + wash;
        int rest1 = process(drink, wash, air, index + 1, self1);
        int p1 = Math.max(self1, rest1);
        // 不洗当前杯子
        int self2 = drink[index] + air;
        int rest2 = process(drink, wash, air, index + 1, waste);
        int p2 = Math.max(self2, rest2);

        return Math.min(p1, p2);
    }

    public static int dp(int[] arr, int N, int wash, int air) {
        // 计算所有人喝完咖啡时间数组
        int[] drink = getDrinkArr(arr, N);
        // 计算最大清洗时间
        int maxWaste = 0;
        for (int i = 0; i < drink.length; i++) {
            maxWaste = Math.max(maxWaste, drink[i]) + wash;
        }
        int[][] dp = new int[N + 1][maxWaste + 1];
        for (int index = N - 1; index >= 0; index--) {
            for (int waste = 0; waste <= maxWaste; waste++) {
                // 洗当前杯子
                int self1 = Math.max(drink[index], waste) + wash;
                if (self1 > maxWaste) { // 自己清洗时间超过了最大清洗时间.跳过.
                    break;
                }
                int rest1 = dp[index + 1][self1];
                int p1 = Math.max(self1, rest1);
                // 不洗当前杯子
                int self2 = drink[index] + air;
                int rest2 = dp[index + 1][waste];
                int p2 = Math.max(self2, rest2);
                dp[index][waste] = Math.min(p1, p2);
            }
        }
        return dp[0][0];
    }

    public static int[] getDrinkArr(int[] arr, int N) {
        int[] drink = new int[N];
        PriorityQueue<Machine> heap = new PriorityQueue<>(Comparator.comparingInt(o -> (o.timePoint + o.workTime)));
        for (int i = 0; i < arr.length; i++) {
            heap.add(new Machine(0, arr[i]));
        }
        for (int i = 0; i < drink.length; i++) {
            Machine m = heap.poll();
            m.timePoint += m.workTime;
            drink[i] = m.timePoint;
            heap.add(m);
        }
        return drink;
    }

    static class Machine {
        public int timePoint;   // 当前时间点
        public int workTime;    // 咖啡机工作时间

        public Machine(int t, int w) {
            timePoint = t;
            workTime = w;
        }
    }
}
