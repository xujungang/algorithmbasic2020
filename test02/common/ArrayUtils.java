package common;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 数组工具类
 *
 * @author xujungang
 * @date 2021-01-23
 */
public class ArrayUtils {

    /**
     * 根据字符串创建数组.数组元素全是数字,多个使用[,]间隔
     *
     * @param arrStr
     * @return
     */
    public static int[] createIntArray(String arrStr) {
        if (arrStr == null || arrStr.length() == 0) {
            return new int[0];
        }
        String[] strs = arrStr.split(",");
        int[] result = new int[strs.length];
        for (int i = 0; i < strs.length; i++) {
            result[i] = Integer.valueOf(strs[i]);
        }
        return result;
    }

    /**
     * 打印数组
     *
     * @param arr
     */
    public static void printArray(int[] arr) {
        if (arr == null) {
            System.out.println("===> null");
        } else if (arr.length == 0) {
            System.out.println("===> []");
        } else {
            System.out.println(Arrays.stream(arr).boxed()
                    .map(String::valueOf).collect(Collectors.joining(", ")));
        }
    }

}
