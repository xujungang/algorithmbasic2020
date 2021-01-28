package class09;

import common.Node;

/**
 * 将单向链表按某值划分成左边小、中间相等、右边大的形式
 * 1）把链表放入数组里，在数组上做partition（笔试用）
 * 2）分成小、中、大三部分，再把各个部分之间串起来（面试用）
 *
 * @author xujungang
 * @date 2021-01-28
 */
public class C03_SmallerEqualBigger {

    public static void main(String[] args) {
        Node head;
        // 小 中 大
        // 1,2,2,3,3,6,7
        head = Node.createLinkedList("3,1,2,6,3,2,7");
        Node.printNode(partition(head, 3));
        System.out.println("=========================");

        // 中 大
        // 3,3,4,5,6,5,7
        head = Node.createLinkedList("3,4,5,6,3,5,7");
        Node.printNode(partition(head, 3));
        System.out.println("=========================");

        // 小 大
        // 1,2,2,4,6,4,7
        head = Node.createLinkedList("4,1,2,6,4,2,7");
        Node.printNode(partition(head, 3));
        System.out.println("=========================");

        // 小 中
        // 1,2,2,2,3,3,3
        head = Node.createLinkedList("3,1,2,3,3,2,2");
        Node.printNode(partition(head, 3));
        System.out.println("=========================");

        // 小
        // 3,1,2,3,3,2,2
        head = Node.createLinkedList("3,1,2,3,3,2,2");
        Node.printNode(partition(head, 5));
        System.out.println("=========================");

        // 大
        // 3,1,2,3,3,2,2
        head = Node.createLinkedList("3,1,2,3,3,2,2");
        Node.printNode(partition(head, 0));
        System.out.println("=========================");

        // 中
        // 3,3,3
        head = Node.createLinkedList("3,3,3");
        Node.printNode(partition(head, 3));
        System.out.println("=========================");
    }

    public static Node partition(Node head, int value) {
        Node sh = null, st = null, eh = null, et = null, bh = null, bt = null, next;
        // 分成小 中 大 三个独立链表
        while (head != null) {
            next = head.next;
            head.next = null;
            if (head.value < value) {
                if (sh == null) {
                    sh = head;
                    st = head;
                } else {
                    st.next = head;
                    st = st.next;
                }
            } else if (head.value == value) {
                if (eh == null) {
                    eh = head;
                    et = head;
                } else {
                    et.next = head;
                    et = et.next;
                }
            } else {
                if (bh == null) {
                    bh = head;
                    bt = head;
                } else {
                    bt.next = head;
                    bt = bt.next;
                }
            }
            head = next;
        }
        // 将 小 中 大 三个链表连接起来
        if (sh == null) {
            sh = eh;
            st = et;
        } else if (eh != null) {
            st.next = eh;
            st = et;
        }
        if (sh == null) {
            sh = bh;
            st = bt;
        } else if (bh != null) {
            st.next = bh;
        }
        return sh;
    }
}
