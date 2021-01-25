package class03;

import common.DoubleNode;

/**
 * 反转双向链表
 *
 * @author xujungang
 * @date 2021-01-23
 */
public class C01_ReverseDoubleList {

    public static void main(String[] args) {
        DoubleNode head = DoubleNode.createDoubleList("1,2,3,4,5");
        DoubleNode.printDoubleNode(head);
        DoubleNode.printDoubleNode(reverseDoubleList(head));
    }

    public static DoubleNode reverseDoubleList(DoubleNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        DoubleNode pre = null, cur = head, next;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            cur.pre = next;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
