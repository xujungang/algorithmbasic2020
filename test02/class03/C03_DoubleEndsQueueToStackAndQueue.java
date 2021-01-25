package class03;

import common.DoubleNode;

/**
 * 双向链表实现栈和队列
 *
 * @author xujungang
 * @date 2021-01-24
 */
public class C03_DoubleEndsQueueToStackAndQueue {

    public static void main(String[] args) {
        testMyStack();
        System.out.println();
        testMyQueue();
    }

    public static void testMyStack() {
        MyStack myStack = new MyStack();
        System.out.println(myStack.pop());              // null
        myStack.push(1);
        System.out.println(myStack.pop());              // 1
        myStack.push(2);
        myStack.push(3);
        System.out.println(myStack.pop());              // 3
        myStack.push(4);
        myStack.push(5);
        myStack.push(6);
        System.out.println(myStack.pop());              // 6
        System.out.println(myStack.pop());              // 5
        System.out.println(myStack.pop());              // 4
        System.out.println(myStack.pop());              // 2
    }

    public static void testMyQueue() {
        MyQueue myQueue = new MyQueue();
        System.out.println(myQueue.pull());             // null
        myQueue.add(1);
        System.out.println(myQueue.pull());             // 1
        myQueue.add(2);
        myQueue.add(3);
        System.out.println(myQueue.pull());             // 2
        myQueue.add(4);
        myQueue.add(5);
        myQueue.add(6);
        System.out.println(myQueue.pull());             // 3
        System.out.println(myQueue.pull());             // 4
        System.out.println(myQueue.pull());             // 5
        System.out.println(myQueue.pull());             // 6
    }

    static class MyStack {
        DoubleNode head = null;
        DoubleNode tail = null;

        public void push(Integer element) {
            DoubleNode node = new DoubleNode(element);
            if (head == null) {
                head = node;
            } else {
                tail.next = node;
                node.pre = tail;
            }
            tail = node;
        }

        public Integer pop() {
            if (isEmpty()) {
                return null;
            }
            int ans = tail.value;
            if (tail.pre == null) { // 只有一个节点
                head = null;
                tail = null;
            } else {
                tail = tail.pre;
                tail.next = null;
            }
            return ans;
        }

        public boolean isEmpty() {
            return head == null;
        }
    }

    static class MyQueue {
        DoubleNode head = null;
        DoubleNode tail = null;

        public void add(Integer element) {
            DoubleNode node = new DoubleNode(element);
            if (head == null) {
                head = node;
            } else {
                tail.next = node;
                node.pre = tail;
            }
            tail = node;
        }

        public Integer pull() {
            if (isEmpty()) {
                return null;
            }
            int ans = head.value;
            if (head.next == null) {    // 只有一个元素
                head = null;
                tail = null;
            } else {
                head = head.next;
                head.pre = null;
            }
            return ans;
        }

        public boolean isEmpty() {
            return head == null;
        }
    }
}
