package class09;

import common.Node;

import java.util.Stack;

/**
 * 链表没K个元素进行一次逆序.例如:1 -> 2 -> 3 -> 4 -> 5 -> 6
 * 每K= 3个元素逆序.结果:3 -> 2 -> 1 -> 5 -> 5 -> 4
 * 方案1: 借助栈,K个元素进栈再出栈.
 * 方案2: 每K个元素进行一次逆序,然后拼接.
 * @author xujungang
 * @date 2021-03-04
 */
public class I05_RevListWithStepK {

    public static void main(String[] args) {
        // 使用方案1, k = 3
        Node h1 = Node.createLinkedList("1,2,3,4,5,6");
        Node.printNode(h1);
        Node.printNode(revWithStack(h1, 3));
        // 使用方案1, k = 4
        h1 = Node.createLinkedList("1,2,3,4,5,6");
        Node.printNode(h1);
        Node.printNode(revWithStack(h1, 4));
        // 使用方案2, k = 3
        h1 = Node.createLinkedList("1,2,3,4,5,6");
        Node.printNode(h1);
        Node.printNode(revWithoutStack(h1, 3));
        // 使用方案2, k = 4
        h1 = Node.createLinkedList("1,2,3,4,5,6");
        Node.printNode(h1);
        Node.printNode(revWithoutStack(h1, 4));
    }

    /**
     * 方案1: 通过栈来实现
     * @param head
     * @param k
     * @return
     */
    public static Node revWithStack(Node head, int k) {
        if (head == null || head.next == null || k < 2) {
            return head;
        }
        Node cur = head, rstHead = null, rstTail = null;
        Stack<Node> stack = new Stack<>();
        while (cur != null) {
            // 入栈
            for (int i = 0; i < k && cur != null; i++, cur = cur.next) {
                stack.push(cur);
            }
            // 出栈
            while (!stack.isEmpty()) {
                Node node = stack.pop();
                if (rstHead == null) {
                    rstHead = node;
                    rstTail = node;
                } else {
                    rstTail.next = node;
                    rstTail = rstTail.next;
                }
            }
        }
        rstTail.next = null;    // 切断最后一个元素的next
        return rstHead;
    }

    public static Node revWithoutStack(Node head, int k) {
        if (head == null || head.next == null || k < 2) {
            return head;
        }
        Node cur = head, rstHead = null, rstTail = null;
        while (cur != null) {
            Node h = cur, t = null;    // h本轮逆序的头,t本轮逆序的尾
            // 移动K步
            for (int i = 0; i < k && cur != null; i++, cur = cur.next) {
                t = cur;
            }
            // 此时 t 是第K位元素, cur为第K+1位元素,即下一次循环的head;
            if (rstHead == null) {
                rstHead = t;
            } else {
                rstTail.next = t;
            }
            // 逆序 h ~ t
            Node rc = h, pre = null, next;
            for (int i = 0; i < k && rc != null && rc != cur; i++) {
                next = rc.next;
                rc.next = pre;
                pre = rc;
                rc = next;
            }
            rstTail = h;
        }
        return rstHead;
    }
}
