package class02;

import common.ArrayUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 一个数组中有一种数出现了K次,其他数都出现了M次, M > 1, K < M
 * 找到出现了K次的数
 * 要求: 额外空间复杂度O(1), 时间复杂度O(N)
 *
 * @author xujungang
 * @date 2021-01-23
 */
public class C03_KM {

    public static void main(String[] args) {
        int[] arr = ArrayUtils.createIntArray("11,11,11,11,22,22,22,22,33,33,33,44,44,44,44,55,55,55,55");
        ArrayUtils.printArray(arr);
        System.out.println("出现K次的数: " + onlyKTimes(arr, 4, 3));
    }

    public static int onlyKTimes(int[] arr, int m, int k) {
        int[] bitArr = new int[32];
        Map<Integer, Integer> bitMap = initBitMap();
        int num, rightOne;
        // 将所有的数转化成二进制数,如果i位置为1,bitArr[i]+1
        for (int i = 0; i < arr.length; i++) {
            num = arr[i];
            while (num != 0) {
                rightOne = num & -num;
                bitArr[bitMap.get(rightOne)]++;
                num ^= rightOne;
            }
        }
        // 遍历数组,将每位为M*n+K的数取出来
        int ans = 0;
        for (int i = 0; i < bitArr.length; i++) {
            if (bitArr[i] % m == k) {
                ans += Math.pow(2, i);
            }
        }
        return ans;
    }

    public static Map<Integer, Integer> initBitMap() {
        int value = 1;
        Map<Integer, Integer> bitMap = new HashMap<>();
        for (int i = 0; i < 32; i++) {
            bitMap.put(value, i);
            value <<= 1;
        }
        System.out.println(bitMap);
        return bitMap;
    }
}
