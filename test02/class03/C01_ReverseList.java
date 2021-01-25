package class03;

import common.Node;

/**
 * 反转单链表
 *
 * @author xujungang
 * @date 2021-01-23
 */
public class C01_ReverseList {

    public static void main(String[] args) {
        Node head = Node.createLinkedList("1,2,3,4,5");
        Node.printNode(head);
        Node.printNode(reverseList(head));
    }

    public static Node reverseList(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node pre = null, cur = head, next;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
