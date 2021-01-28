package class08;

/**
 * 前缀树（prefix tree   trie）
 * 1）单个字符串中，字符从前到后的加到一棵多叉树上
 * 2）字符放在路上，节点上有专属的数据项（常见的是pass和end值）
 * 3）所有样本都这样添加，如果没有路就新建，如有路就复用
 * 4）沿途节点的pass值增加1，每个字符串结束时来到的节点end值增加1
 * 可以完成前缀相关的查询
 * 设计一种结构。用户可以：
 * 1）void insert(String str)            添加某个字符串，可以重复添加，每次算1个
 * 2）int search(String str)             查询某个字符串在结构中还有几个
 * 3) void delete(String str)           删掉某个字符串，可以重复删除，每次算1个
 * 4）int prefixNumber(String str)  查询有多少个字符串，是以str做前缀的
 *
 * @author xujungang
 * @date 2021-01-25
 */
public class C01_TrieTree {

    public static void main(String[] args) {
        TrieTree trieTree = new TrieTree();
        trieTree.insert("abc");
        trieTree.insert("abcd");
        System.out.println(trieTree.search("avc"));
        System.out.println(trieTree.search("abc"));
        System.out.println(trieTree.search("abcd"));
        System.out.println(trieTree.prefixNumber("abc"));
        trieTree.delete("abc");
        System.out.println(trieTree.search("abc"));
    }

    static class TrieTree {
        Node root = new Node();

        public void insert(String str) {
            char[] chs = str.toCharArray();
            Node r = root;
            r.pass++;
            for (int i = 0; i < chs.length; i++) {
                int index = chs[i] - 'a';
                if (r.next[index] == null) {
                    r.next[index] = new Node();
                }
                r.next[index].pass++;
                r = r.next[index];
            }
            r.end++;
        }

        public int search(String str) {
            char[] chs = str.toCharArray();
            Node r = root;
            for (int i = 0; i < chs.length; i++) {
                int index = chs[i] - 'a';
                if (r.next[index] == null) {
                    return 0;
                }
                r = r.next[index];
            }
            return r.end;
        }

        public void delete(String str) {
            if (search(str) <= 0) {
                return;
            }
            char[] chs = str.toCharArray();
            Node r = root;
            r.pass--;
            for (int i = 0; i < chs.length; i++) {
                int index = chs[i] - 'a';
                r.next[index].pass--;
                if (r.next[index].pass == 0) {  // help gc
                    r.next[index] = null;
                    return;
                }
                r = r.next[index];
            }
            r.end--;
        }

        public int prefixNumber(String str) {
            char[] chs = str.toCharArray();
            Node r = root;
            for (int i = 0; i < chs.length; i++) {
                int index = chs[i] - 'a';
                if (r.next[index] == null) {
                    return -1;
                }
                r = r.next[index];
            }
            return r.pass;
        }
    }

    static class Node {
        int pass = 0;
        int end = 0;
        Node[] next = new Node[26];
    }
}
