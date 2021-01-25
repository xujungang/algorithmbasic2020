package common;

/**
 * 链表节点
 *
 * @author xujungang
 * @date 2021-01-23
 */
public class Node {

    public int value;
    public Node next;

    public Node(int value) {
        this.value = value;
    }

    public static Node createLinkedList(String arrStr) {
        if (arrStr == null || arrStr.length() == 0) {
            return null;
        }
        Node head = null, cur = null;
        for (String s : arrStr.split(",")) {
            Node n = new Node(Integer.valueOf(s));
            if (head == null) {
                head = n;
                cur = n;
            } else if (cur != null) {
                cur.next = n;
                cur = cur.next;
            }
        }
        return head;
    }

    public static void printNode(Node head) {
        StringBuilder sb = new StringBuilder("===>");
        int index = 0;
        while (head != null) {
            sb.append(" " + head.value + "(" + (index++) + ") ->");
            head = head.next;
        }
        sb.append(" null");
        System.out.println(sb.toString());
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}
