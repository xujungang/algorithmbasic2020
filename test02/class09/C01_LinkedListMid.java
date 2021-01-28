package class09;

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
public class C01_LinkedListMid {

    public static void main(String[] args) {
        Node odd = Node.createLinkedList("1,2,3,4,5,6,7,8,9");
        Node even = Node.createLinkedList("1,2,3,4,5,6,7,8,9,0");
        // 输入链表头节点，奇数长度返回中点，偶数长度返回上中点
        System.out.println(midOrUpMidNode(odd));        // 5
        System.out.println(midOrUpMidNode(even));       // 5
        // 输入链表头节点，奇数长度返回中点，偶数长度返回下中点
        System.out.println(midOrDownMidNode(odd));      // 5
        System.out.println(midOrDownMidNode(even));     // 6
        // 输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
        System.out.println(midOrUpMidPreNode(odd));     // 4
        System.out.println(midOrUpMidPreNode(even));    // 4
        // 输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个
        System.out.println(midOrDownMidPreNode(odd));   // 4
        System.out.println(midOrDownMidPreNode(even));  // 5
    }

    // 输入链表头节点，奇数长度返回中点，偶数长度返回上中点
    public static Node midOrUpMidNode(Node head) {
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

    // 输入链表头节点，奇数长度返回中点，偶数长度返回下中点
    public static Node midOrDownMidNode(Node head) {
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

    // 输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
    public static Node midOrUpMidPreNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        Node s = head;
        Node f = head.next.next;
        while (f.next != null && f.next.next != null) {
            s = s.next;
            f = f.next.next;
        }
        return s;
    }

    // 输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个
    public static Node midOrDownMidPreNode(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node s = head;
        Node f = head.next.next;
        while (f != null && f.next != null) {
            s = s.next;
            f = f.next.next;
        }
        return s;
    }
}
