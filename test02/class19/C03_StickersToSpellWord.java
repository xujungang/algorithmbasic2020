package class19;

/**
 * 给定一个字符串str，给定一个字符串类型的数组arr，出现的字符都是小写英文
 * arr每一个字符串，代表一张贴纸，你可以把单个字符剪开使用，目的是拼出str来
 * 返回需要至少多少张贴纸可以完成这个任务。
 * 例子：str= "babac"，arr = {"ba","c","abcd"}
 * ba + ba + c  3  abcd + abcd 2  abcd+ba 2
 * 所以返回2
 * https://leetcode-cn.com/problems/stickers-to-spell-word
 *
 * @author xujungang
 * @date 2021-01-30
 */
public class C03_StickersToSpellWord {

    public static void main(String[] args) {
        // 2
        System.out.println(minStickers(new String[]{"ba", "c", "abcd"}, "babac"));
    }

    public static int minStickers(String[] stickers, String target) {
        if (target == null || target.length() == 0) {
            return 0;
        }
        int ans = process(stickers, target);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    /**
     * @param stickers 还有多少贴纸
     * @param target   目标还有多少字符
     * @return 最少张数
     */
    public static int process(String[] stickers, String target) {
        if (target.length() == 0) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (String first : stickers) {
            String rest = minus(target, first);
            if (target.length() != rest.length()) {
                int next = process(stickers, rest);
                min = Math.min(min, next);
            }
        }
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

    public static String minus(String target, String sticker) {
        char[] chs1 = target.toCharArray();
        char[] chs2 = sticker.toCharArray();
        int[] count = new int[26];
        for (char c : chs1) {
            count[c - 'a']++;
        }
        for (char c : chs2) {
            count[c - 'a']--;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count.length; i++) {
            if (count[i] > 0) {
                for (int j = 0; j < count[i]; j++) {
                    sb.append((char) (i + 'a'));
                }
            }
        }
        return sb.toString();
    }
}
