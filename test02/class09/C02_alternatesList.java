package class09;

import common.Node;

/**
 * 一个链表:L1 L2 L3 L4 R1 R2 R3 R4 转换成 L1 R4 L2 R3 L3 R2 L4 R1
 *
 * @author xujungang
 * @date 2021-01-28
 */
public class C02_alternatesList {

    public static void main(String[] args) {
        Node head = Node.createLinkedList("11,12,13,14,15,16,21,22,23,24,25,26");
        Node.printNode(head);
        alternatesList(head);
        Node.printNode(head);
    }

    public static void alternatesList(Node head) {
        if (head == null || head.next == null) {
            return;
        }
        // 找到中间节点
        Node p1 = head.next.next;   // 快指针
        Node mid = head;            // 慢指针
        while (p1 != null && p1.next != null) {
            p1 = p1.next.next;
            mid = mid.next;
        }
        // 此时 mid 为中间节点或中上节点
        // 逆序mid之后的节点
        p1 = mid.next;
        Node next, pre = null;
        mid.next = null;
        while (p1 != null) {
            next = p1.next;
            p1.next = pre;
            pre = p1;
            p1 = next;
        }
        // 此时 链表基于中间节点整对称的.并且pre是尾
        // 组成新的链表
        Node cur = head;
        p1 = head.next;
        cur.next = pre;
        Node p2 = pre.next;
        cur = cur.next;
        while (p1 != null) {
            cur.next = p1;
            p1 = p1.next;
            cur.next.next = p2;
            p2 = p2.next;
            cur = cur.next.next;
        }
    }
}
