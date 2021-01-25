package class02;

import common.ArrayUtils;

/**
 * 一个数组中只有一个数出现了奇数次,其余数都出现了偶数次.找到并打印出现奇数次的数
 * N ^ N = 0
 * N ^ 0 = N
 *
 * @author xujungang
 * @date 2021-01-23
 */
public class C02_EvenTimesOddTimes {

    public static void main(String[] args) {
        int[] arr = ArrayUtils.createIntArray("1,1,2,2,3,3,4,4,4,4,5,5,5,6,6,6,6");
        System.out.println("出现奇数次数的数: 5, " + findOddNum(arr));
    }

    public static int findOddNum(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        return eor;
    }

}
