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

    }

    static class TrieTree {



        public void insert(String str) {

        }

        public int search(String str) {
            return 0;
        }

        public void delete(String str) {

        }

        public int prefixNumber(String str) {

            return 0;
        }
    }

    static class Node {
        String c;
        int pass = 0;
        int end = 0;
        Node[] next;

        public Node(String c) {
            this.c = c;
        }
    }
}
