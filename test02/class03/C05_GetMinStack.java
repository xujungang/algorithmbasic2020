package class03;

import java.util.Stack;

/**
 * @author xujungang
 * @date 2021-01-23
 */
public class C05_GetMinStack {

    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        System.out.println(myStack.getMin());       // null
        System.out.println(myStack.pop());          // null
        myStack.push(5);
        System.out.println(myStack.getMin());       // 5
        myStack.push(1);
        myStack.push(3);
        System.out.println(myStack.getMin());       // 1
        System.out.println(myStack.pop());          // 3
        System.out.println(myStack.pop());          // 1
        System.out.println(myStack.getMin());       // 5
    }

    static class MyStack {

        private Stack<Integer> stack = new Stack<>();
        private Stack<Integer> minStack = new Stack<>();


        public void push(Integer element) {
            stack.push(element);
            Integer peek = getMin();
            minStack.push(peek == null ? element : Math.min(peek, element));
        }

        public Integer pop() {
            if (stack.isEmpty()) {
                return null;
            }
            minStack.pop();
            return stack.pop();
        }

        public Integer getMin() {
            if (minStack.isEmpty()) {
                return null;
            }
            return minStack.peek();
        }
    }
}
