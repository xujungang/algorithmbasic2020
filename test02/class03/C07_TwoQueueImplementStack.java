package class03;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 2个队列实现栈
 *
 * @author xujungang
 * @date 2021-01-23
 */
public class C07_TwoQueueImplementStack {

    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        System.out.println(myStack.pop());          // null
        myStack.push(5);
        myStack.push(1);
        myStack.push(3);
        System.out.println(myStack.pop());          // 3
        System.out.println(myStack.pop());          // 1
        System.out.println(myStack.pop());          // 5
    }

    static class MyStack {

        Queue<Integer> data = new LinkedList<>();
        Queue<Integer> help = new LinkedList<>();

        public void push(Integer element) {
            data.add(element);
        }

        public Integer pop() {
            if (data.isEmpty()) {
                return null;
            }
            int ans = data.poll();
            while (!data.isEmpty()) {
                help.add(ans);
                ans = data.poll();
            }
            swap();
            return ans;
        }

        private void swap() {
            Queue<Integer> temp = data;
            data = help;
            help = temp;
        }
    }

}
