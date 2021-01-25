package class07;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 最大线段重合问题（用堆的实现）
 * 给定很多线段，每个线段都有两个数[start, end]，表示线段开始位置和结束位置，左右都是闭区间
 * 规定：
 * 1）线段的开始和结束位置一定都是整数值
 * 2）线段重合区域的长度必须>=1
 * 返回线段最多重合区域中，包含了几条线段
 * <p>
 * 解题思路:
 * 1.现将线段按照起始位置从小到达排序
 * 2.创建一个小根堆
 * 3.遍历排序后的数组,将小于等于起始位置的节点从小根堆弹出,并将终止位置加入小根堆中.看下小根堆中有多少数
 * 线段           小根堆元素个数     小根堆弹出条件     小根堆剩余元素
 * [1, 5]           1                <= 1               5
 * [2, 4]           2                <= 2               4, 5
 * [4, 7]           2                <= 4               5, 7
 *
 * @author xujungang
 * @date 2021-01-24
 */
public class C01_CoverMax {

    public static void main(String[] args) {
        Line l1 = new Line(4, 9);
        Line l2 = new Line(1, 4);
        Line l3 = new Line(7, 15);
        Line l4 = new Line(2, 4);
        Line l5 = new Line(4, 6);
        Line l6 = new Line(3, 7);
        Line[] lines = {l1, l2, l3, l4, l5, l6};
        System.out.println(maxCover(lines));    // 3
    }

    public static int maxCover(Line[] ls) {
        // 将数据排序
        Arrays.sort(ls, Comparator.comparingInt(l -> l.begin));
        // 创建一个小根堆
        PriorityQueue<Integer> smallHeap = new PriorityQueue<>();
        int max = 0;
        for (int i = 0; i < ls.length; i++) {
            while (!smallHeap.isEmpty() && smallHeap.peek() <= ls[i].begin) {
                smallHeap.poll();
            }
            smallHeap.add(ls[i].end);
            max = Math.max(max, smallHeap.size());
        }
        return max;
    }

    static class Line {
        int begin;
        int end;

        public Line(int begin, int end) {
            this.begin = begin;
            this.end = end;
        }
    }
}
