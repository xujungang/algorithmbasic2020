package class02;

import common.ArrayUtils;

/**
 * 一个数组中有两个数出现了奇数次,其余数都出现了偶数次.找到并打印出现奇数次的数
 * <p>
 * 1.将数组所有元素异或得到 EOR = A ^ B
 * 2.算出 EOR最右侧的1 例如 右侧第三位 100
 * 3.假设 A 最右侧的1 是 右侧第三位 B最右侧的1就不可能是 右侧第三位
 * 4.把集合划分为 右侧第三为是1 和 不是1 的两个集合
 * 5.是1的集合 异或 得到 A,
 * 6 EOR ^ A 得到 B
 *
 * @author xujungang
 * @date 2021-01-23
 */
public class C02_EvenTimesOddTimes2 {

    public static void main(String[] args) {
        // 3, 5
        int[] arr = ArrayUtils.createIntArray("1,1,2,2,3,4,4,4,4,5,5,5,6,6,6,6");
        printOddTimesNum(arr);
    }

    public static void printOddTimesNum(int[] arr) {
        int eor = 0;
        // 将所有的数异或
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        // 找到左右的数据异或的结果最右侧的1
        int rightOne = eor & -eor;
        // 可以将数组的元素分为两部分
        // 第一部分: 第N位的数是1
        // 第二部分: 第N位的数是0
        int eor1 = 0;
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] & rightOne) != 0) { // 说明第N位是1
                eor1 ^= arr[i];
            }
        }
        // 此时eor1就是其中一个出现奇数次的数
        // 此时 eor ^ eor1 就是另一个出现奇数次的数
        System.out.println((eor ^ eor1) + ", " + eor1);
    }
}
