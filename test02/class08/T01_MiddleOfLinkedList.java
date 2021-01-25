package class08;

import common.Node;

/**
 * 链表练习题
 * 1）输入链表头节点，奇数长度返回中点，偶数长度返回上中点
 * 2）输入链表头节点，奇数长度返回中点，偶数长度返回下中点
 * 3）输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
 * 4）输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个
 *
 * @author xujungang
 * @date 2021-01-25
 */
public class T01_MiddleOfLinkedList {

    public static void main(String[] args) {
        Node odd = Node.createLinkedList("1,2,3,4,5,6,7,8,9");
        Node even = Node.createLinkedList("1,2,3,4,5,6,7,8,9,0");
        System.out.println(findMiddleOrLast(odd));  // 5
        System.out.println(findMiddleOrLast(even)); // 5
        System.out.println(findMiddleOrNext(odd));  // 5
        System.out.println(findMiddleOrNext(even)); // 6
    }

    public static Node findMiddleOrLast(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        Node s = head.next;
        Node f = head.next.next;
        while (f.next != null && f.next.next != null) {
            s = s.next;
            f = f.next.next;
        }
        return s;
    }

    public static Node findMiddleOrNext(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node s = head.next;
        Node f = head.next.next;
        while (f != null && f.next != null) {
            s = s.next;
            f = f.next.next;
        }
        return s;
    }

    public static Node findBeforeMiddleOrBeforeLast(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        return null;
    }

    public static Node findBeforeMiddleOrBeforeNext(Node head) {
        return null;
    }
}
