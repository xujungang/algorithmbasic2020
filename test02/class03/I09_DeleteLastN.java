package class03;

import common.Node;

/**
 * 地址: https://www.cnblogs.com/wuyouwei/p/11767778.html
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * 示例：
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 *
 * 给定的 n 保证是有效的。
 * @author xujungang
 * @date 2021-03-06
 */
public class I09_DeleteLastN {

    public static void main(String[] args) {
        Node.printNode(delete(Node.createLinkedList("1"), 1));
        Node.printNode(delete(Node.createLinkedList("1,2,3,4,5"), 2));
        Node.printNode(delete(Node.createLinkedList("1,2"), 1));
    }

    private static Node delete(Node head, int n) {
        if (head == null || (head.next == null && n == 1) ) {
            return null;
        }
        Node pre = new Node(0), del = head, tail = head;
        pre.next = head;
        for (int i = 0; i < n; i++) {
            tail = tail.next;
        }
        // tail和head相差n位
        // 场景1: 删除的是head,此时tail == null
        // 场景2: 删除的不是head,此时tail != null
        while (tail != null) {
            tail = tail.next;
            del = del.next;
            pre = pre.next;
        }
        pre.next = del.next;
        return head;
    }
}
