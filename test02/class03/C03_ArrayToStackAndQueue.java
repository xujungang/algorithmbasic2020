package class03;

/**
 * 用数组写栈和队列
 *
 * @author xujungang
 * @date 2021-01-23
 */
public class C03_ArrayToStackAndQueue {

    public static void main(String[] args) {
        testMyStack();
        System.out.println();
        testMyQueue();
    }

    public static void testMyStack() {
        MyStack myStack = new MyStack(3);
        System.out.println(myStack.pop());              // null
        System.out.println(myStack.push(1));    // true
        System.out.println(myStack.pop());              // 1
        System.out.println(myStack.push(2));    // true
        System.out.println(myStack.push(3));    // true
        System.out.println(myStack.pop());              // 3
        System.out.println(myStack.push(4));    // true
        System.out.println(myStack.push(5));    // true
        System.out.println(myStack.push(6));    // false
        System.out.println(myStack.pop());              // 5
        System.out.println(myStack.pop());              // 4
        System.out.println(myStack.pop());              // 2
        System.out.println(myStack.pop());              // null
    }

    public static void testMyQueue() {
        MyQueue myQueue = new MyQueue(3);
        System.out.println(myQueue.pull());             // null
        System.out.println(myQueue.add(1));             // true
        System.out.println(myQueue.pull());             // 1
        System.out.println(myQueue.add(2));             // true
        System.out.println(myQueue.add(3));             // true
        System.out.println(myQueue.pull());             // 2
        System.out.println(myQueue.add(4));             // true
        System.out.println(myQueue.add(5));             // true
        System.out.println(myQueue.add(6));             // false
        System.out.println(myQueue.pull());             // 3
        System.out.println(myQueue.pull());             // 4
        System.out.println(myQueue.pull());             // 5
        System.out.println(myQueue.pull());             // null

    }

    static class MyStack {
        Integer[] data;
        int offset;

        public <T> MyStack(int cap) {
            this.offset = 0;
            data = new Integer[cap];
        }

        public boolean push(Integer element) {
            if (offset == data.length) {
                return false;
            }
            data[offset++] = element;
            return true;
        }

        public Integer pop() {
            if (isEmpty()) {
                return null;
            }
            return data[--offset];
        }

        public boolean isEmpty() {
            return offset == 0;
        }
    }

    static class MyQueue {
        Integer[] data;
        int offset = 0;
        int limit = 0;
        int count = 0;

        public MyQueue(int cap) {
            data = new Integer[cap];
        }

        public boolean add(Integer element) {
            if (isFull()) {
                return false;
            }
            data[limit] = element;
            limit = (limit + 1) % data.length;
            count++;
            return true;
        }

        public Integer pull() {
            if (isEmpty()) {
                return null;
            }
            Integer ans = data[offset];
            offset = (offset + 1) % data.length;
            count--;
            return ans;
        }

        public boolean isEmpty() {
            return count == 0;
        }

        public boolean isFull() {
            return count == data.length;
        }
    }
}
