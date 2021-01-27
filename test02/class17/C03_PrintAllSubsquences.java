package class17;

import java.util.*;

/**
 * 1.打印一个字符串的全部子序列
 * 2.打印一个字符串的全部子序列，要求不要出现重复字面值的子序列
 * 例如: abc: 空,a,b,c,ab,ac,bc,abc
 * 以第N个元素为主,分别递归包含N元素和不包含N元素的场景
 *
 * @author xujungang
 * @date 2021-01-27
 */
public class C03_PrintAllSubsquences {

    public static void main(String[] args) {
        subs1("abc");
        System.out.println("===================");
        subs2("acccc");
    }

    /**
     * 打印一个字符串的全部子序列
     *
     * @param str
     */
    public static void subs1(String str) {
        char[] chs = str.toCharArray();
        List<String> ans = new ArrayList<>();
        process(chs, 0, "", ans);
        ans.forEach(System.out::println);
    }

    /**
     * 打印一个字符串的全部子序列，要求不要出现重复字面值的子序列
     *
     * @param str
     */
    public static void subs2(String str) {
        char[] chs = str.toCharArray();
        Set<String> ans = new HashSet<>();
        process(chs, 0, "", ans);
        ans.forEach(System.out::println);
    }

    /**
     * 打印一个字符串的全部子序列
     *
     * @param chs   字符串的字符数组
     * @param index 元素下标
     * @param path  已经遍历过的元素路径
     * @param ans   返回结果
     */
    public static void process(char[] chs, int index, String path, Collection<String> ans) {
        if (index == chs.length) {
            ans.add(path);
            return;
        }
        process(chs, index + 1, path, ans);                      // 递归不包含N元素的场景
        process(chs, index + 1, path + chs[index], ans);    // 递归包含N元素的场景
    }
}
