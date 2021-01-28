package class09;

/**
 * 一种特殊的单链表节点类描述如下
 * class Node {
 * int value;
 * Node next;
 * Node rand;
 * Node(int val) { value = val; }
 * }
 * rand指针是单链表节点结构中新增的指针，rand可能指向链表中的任意一个节点，也可能指向null。
 * 给定一个由Node节点类型组成的无环单链表的头节点 head，请实现一个函数完成这个链表的复制，并返回复制的新链表的头节点。
 * 【要求】
 * 时间复杂度O(N)，额外空间复杂度O(1)
 *
 * @author xujungang
 * @date 2021-01-28
 */
public class C04_CopyListWithRandom {

    public static void main(String[] args) {
        Node h1 = new Node(1);
        h1.next = new Node(2);
        h1.next.next = new Node(3);
        h1.rand = h1.next;      // 1.rand = 2
        h1.next.rand = null;    // 2.rand = null
        h1.next.next.rand = h1; // 3.rand = 1
        Node h2 = copyListWithRand(h1);
        while (h1 != null) {
            System.out.print(h1.value + "(hashCode=" + h1.hashCode() + ",rand="
                    + (h1.rand == null ? "null" : h1.rand.hashCode()) + ") -> ");
            h1 = h1.next;
        }
        System.out.println("null");
        while (h2 != null) {
            System.out.print(h2.value + "(hashCode=" + h2.hashCode() + ",rand="
                    + (h2.rand == null ? "null" : h2.rand.hashCode()) + ") -> ");
            h2 = h2.next;
        }
        System.out.println("null");
    }

    public static Node copyListWithRand(Node head) {
        if (head == null) {
            return null;
        }
        // 按照next依次遍历链表,在每个元素后面添加一个新节点
        Node p = head, next;
        while (p != null) {
            next = p.next;
            Node newNode = new Node(p.value);
            newNode.next = next;
            p.next = newNode;
            p = next;
        }
        // 从头遍历旧新节点,设置rand指针
        p = head;
        while (p != null) {
            p.next.rand = p.rand == null ? null : p.rand.next;
            p = p.next.next;
        }
        // 从头遍历,将旧新节点拆开
        p = head;
        Node result = p.next;
        while (p.next.next != null) {
            next = p.next.next;
            p.next.next = next.next;
            p.next = next;
            p = next;
        }
        p.next = null;
        return result;
    }

    static class Node {
        int value;
        Node next;
        Node rand;

        Node(int val) {
            value = val;
        }
    }
}
