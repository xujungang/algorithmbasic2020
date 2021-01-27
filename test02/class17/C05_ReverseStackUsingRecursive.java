package class17;

import java.util.Stack;

/**
 * 给你一个栈，请你逆序这个栈，不能申请额外的数据结构，只能使用递归函数。 如何实现?
 *
 * @author xujungang
 * @date 2021-01-27
 */
public class C05_ReverseStackUsingRecursive {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        reverse(stack);
        stack.forEach(System.out::println);
    }

    public static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        Integer cur = f(stack);
        reverse(stack);
        stack.push(cur);
    }

    /**
     * 得到栈的最后一个元素并返回
     *
     * @param stack
     * @return
     */
    public static Integer f(Stack<Integer> stack) {
        Integer cur = stack.pop();
        if (stack.isEmpty()) {
            return cur;
        }
        Integer result = f(stack);
        stack.push(cur);
        return result;
    }
}
