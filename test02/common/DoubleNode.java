package common;

/**
 * 双向链表
 *
 * @author xujungang
 * @date 2021-01-23
 */
public class DoubleNode {

    public int value;
    public DoubleNode pre;
    public DoubleNode next;

    public DoubleNode(int value) {
        this.value = value;
    }

    public static DoubleNode createDoubleList(String arrStr) {
        if (arrStr == null || arrStr.length() == 0) {
            return null;
        }
        DoubleNode head = null, pre, cur = null;
        for (String s : arrStr.split(",")) {
            DoubleNode n = new DoubleNode(Integer.valueOf(s));
            if (head == null) {
                head = n;
                cur = n;
            } else if (cur != null) {
                pre = cur;
                cur.next = n;
                cur = cur.next;
                cur.pre = pre;
            }
        }
        return head;
    }

    public static void printDoubleNode(DoubleNode head) {
        DoubleNode last = null;
        int index = 0;
        // 正向
        StringBuilder sb = new StringBuilder("===>");
        while (head != null) {
            sb.append(" " + head.value + "(" + (index++) + ") ->");
            if (head.next == null) {
                last = head;
            }
            head = head.next;
        }
        sb.append(" null");
        System.out.println(sb.toString());
        // 反向
        sb = new StringBuilder("===>");
        while (last != null) {
            sb.append(" " + last.value + "(" + (--index) + ") ->");
            last = last.pre;
        }
        sb.append(" null");
        System.out.println(sb.toString());
    }
}
