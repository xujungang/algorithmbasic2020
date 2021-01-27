package class17;

/**
 * 打印n层汉诺塔从最左边移动到最右边的全部过程
 * 将n-1从from移动到other
 * 将n移动到to
 * 将n-1从other移动到from
 *
 * @author xujungang
 * @date 2021-01-27
 */
public class C02_Hanoi {

    public static void main(String[] args) {
        hanoi(3, "左", "右", "中");
    }

    public static void hanoi(int n, String from, String to, String other) {
        if (n == 1) {
            System.out.println("1: " + from + " => " + to);
            return;
        }
        hanoi(n - 1, from, other, to);
        System.out.println(n + ": " + from + " => " + to);
        hanoi(n - 1, other, to, from);
    }
}
