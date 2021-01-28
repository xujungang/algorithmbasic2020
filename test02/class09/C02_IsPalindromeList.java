package class09;

import common.Node;

/**
 * 是否是回文结构: 12321, 1221
 * 容器方案: 使用一个栈
 * 非容器方案:
 * 1.找到中间位置
 * 2.将右侧取反,将mid指向null
 * 3.再回复回去
 * <p>
 * 衍生题: 一个链表:L1 L2 L3 L4 R1 R2 R3 R4 转换成 L1 R4 L2 R3 L3 R2 L4 R1
 *
 * @author xujungang
 * @date 2021-01-27
 */
public class C02_IsPalindromeList {

    public static void main(String[] args) {
        Node h1 = Node.createLinkedList("1,2,3,2,1");
        System.out.println(isPalindrome(h1));   // true
        System.out.println("=========================");
        Node h2 = Node.createLinkedList("1,2,2,1");
        System.out.println(isPalindrome(h2));   // true
        System.out.println("=========================");
        Node h3 = Node.createLinkedList("1,2,3,4,1");
        System.out.println(isPalindrome(h3));   // false
        System.out.println("=========================");
        Node h4 = Node.createLinkedList("1,2,3,1");
        System.out.println(isPalindrome(h4));   // false
        System.out.println("=========================");
    }

    public static boolean isPalindrome(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        // 找到中间节点
        Node p1 = head; // 快指针
        Node mid = head;// 慢指针
        while (p1.next != null && p1.next.next != null) {
            p1 = p1.next.next;
            mid = mid.next;
        }
        // 此时 mid的位置就是中间的位置
        // 将右侧链表反转
        p1 = mid.next;
        mid.next = null;
        Node next, tail = mid;
        while (p1 != null) {
            next = p1.next;
            p1.next = tail;
            tail = p1;
            p1 = next;
        }
        // 此时 tail的位置是最后一个节点位置
        // 两侧同时往中间走
        p1 = head;
        next = tail;
        boolean isPalindrome = true;
        while (next.next != null && p1.next != null) {
            if (next.value != p1.value) {
                isPalindrome = false;
                break;
            }
            next = next.next;
            p1 = p1.next;
        }
        isPalindrome = isPalindrome ? next.value == p1.value : isPalindrome;
        // 将反转的链表还原: pre = cur, p1 = pre
        p1 = null;
        while (tail != mid) {
            next = tail.next;
            tail.next = p1;
            p1 = tail;
            tail = next;
        }
        return isPalindrome;
    }

}
