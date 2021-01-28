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
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }
        // 找到中间节点
        Node p1 = head.next.next;   // 快指针
        Node mid = head.next;       // 慢指针
        while (p1 != null && p1.next != null) {
            p1 = p1.next.next;
            mid = mid.next;
        }
        // 此时 mid 为中间节点或中上节点
        p1 = head;
        Node p1Next, p2Next, p2 = mid;
        while (p1.next != mid) {
            p1Next = p1.next;
            p2Next = p2.next;
            p1.next = p2;
            p2.next = p1Next;
            p1 = p1Next;
            p2 = p2Next;
        }
        p1.next = p2;
    }
}
