package class17;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 1.打印一个字符串的全部排列
 * 2.打印一个字符串的全部排列，要求不要出现重复的排列
 * 例如: abc: abc, acb, bac, bca, cab, cba
 * 0<=>0 1<=>1 2<=>2
 * ----- 1<=>2 2<=>2
 * 0<=>1 1<=>1 2<=>2
 * ----- 1<=>2 2<=>2
 * 0<=>2 1<=>1 2<=>2
 * ----- 1<=>2 2<=>2
 *
 * @author xujungang
 * @date 2021-01-27
 */
public class C04_PrintAllPermutations {

    public static void main(String[] args) {
        // 打印一个字符串的全部排列
        permutation1("abc");
        System.out.println("=============");
        // 打印一个字符串的全部排列，要求不要出现重复的排列
        permutation2("abaaac");
    }

    /**
     * 打印一个字符串的全部排列
     *
     * @param str
     */
    public static void permutation1(String str) {
        if (str == null || str.length() == 0) {
            return;
        }
        List<String> ans = new ArrayList<>();
        process1(str.toCharArray(), 0, ans);
        ans.forEach(System.out::println);
    }

    /**
     * 打印一个字符串的全部排列
     *
     * @param chs   字符串字节数组
     * @param index 当前递归到的元素下标
     * @param ans   返回结果
     */
    public static void process1(char[] chs, int index, Collection<String> ans) {
        if (index == chs.length) {
            ans.add(new String(chs));
            return;
        }
        for (int i = index; i < chs.length; i++) {
            swap(chs, index, i);
            process1(chs, index + 1, ans);
            swap(chs, index, i);    // 还原
        }
    }

    /**
     * 打印一个字符串的全部排列，要求不要出现重复的排列
     *
     * @param str
     */
    public static void permutation2(String str) {
        if (str == null || str.length() == 0) {
            return;
        }
        List<String> ans = new ArrayList<>();
        process2(str.toCharArray(), 0, ans);
        ans.forEach(System.out::println);
    }

    /**
     * 打印一个字符串的全部排列，要求不要出现重复的排列
     *
     * @param chs   字符串字节数组
     * @param index 当前递归到的元素下标
     * @param ans   返回结果
     */
    public static void process2(char[] chs, int index, Collection<String> ans) {
        if (index == chs.length) {
            ans.add(new String(chs));
            return;
        }
        boolean[] exist = new boolean[256];
        for (int i = index; i < chs.length; i++) {
            if (!exist[chs[i]]) {
                exist[chs[i]] = true;   // 剪枝
                swap(chs, index, i);
                process2(chs, index + 1, ans);
                swap(chs, index, i);    // 还原
            }
        }
    }

    public static void swap(char[] chs, int a, int b) {
        char temp = chs[a];
        chs[a] = chs[b];
        chs[b] = temp;
    }
}
