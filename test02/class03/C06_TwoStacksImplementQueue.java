package class03;

import java.util.Stack;

/**
 * 2个栈实现队列
 *
 * @author xujungang
 * @date 2021-01-23
 */
public class C06_TwoStacksImplementQueue {

    public static void main(String[] args) {
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

    static class MyQueue {
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> help = new Stack<>();

        public void add(Integer element) {
            stack.add(element);
        }

        public Integer pull() {
            if (help.isEmpty() && stack.isEmpty()) {
                return null;
            }
            if (help.isEmpty()) {
                while (!stack.isEmpty()) {
                    help.add(stack.pop());
                }
            }
            return help.pop();
        }

    }
}
