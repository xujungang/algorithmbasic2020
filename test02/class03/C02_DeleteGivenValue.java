package class03;

import common.Node;

/**
 * 删除给定的元素,整个链表中可能包含多个元素
 *
 * @author xujungang
 * @date 2021-01-23
 */
public class C02_DeleteGivenValue {

    public static void main(String[] args) {
        Node head = Node.createLinkedList("1,2,3,4,1,1,5,1,6,7");
        Node.printNode(head);
        Node.printNode(removeValue(head, 1));
    }

    public static Node removeValue(Node head, int value) {
        if (head == null) {
            return head;
        }
        while (head != null) {
            if (head.value != value) {
                break;
            }
            head = head.next;
        }
        Node pre = head;
        Node cur = head.next;
        while (cur != null) {
            if (cur.value == value) {
                pre.next = cur.next;
            } else {
                pre = pre.next;
            }
            cur = cur.next;
        }
        return head;
    }
}
