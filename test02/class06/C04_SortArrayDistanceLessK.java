package class06;

import common.ArrayUtils;

import java.util.PriorityQueue;

/**
 * 已知一个几乎有序的数组。几乎有序是指，如果把数组排好顺序的话，每个元素移动的距离一定不超过k，并且k相对于数组长度来说是比较小的。
 * <p>
 * 思路:
 * 创建一个K大小的小根堆,将前K个值放入进去,然后依次放入1个取出1个.时间复杂度O(N*logK)
 *
 * @author xujungang
 * @date 2021-01-24
 */
public class C04_SortArrayDistanceLessK {

    public static void main(String[] args) {
        int[] arr = new int[]{8, -3, -5, 16, 21, -25, 11, -2, 1, 37, 27, 69, 45, 66, 44};
        int k = 5;
        ArrayUtils.printArray(arr);
        sortedArrDistanceLessK(arr, k);
        ArrayUtils.printArray(arr);
    }

    public static void sortedArrDistanceLessK(int[] arr, int k) {
        if (arr == null || arr.length < 1 || k == 0) {
            return;
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int i1 = 0;
        for (int i = 0; i < Math.min(arr.length, k); i++, i1++) {
            heap.add(arr[i]);
        }
        int i2 = 0;
        for (; i1 < arr.length; i2++, i1++) {
            heap.add(arr[i1]);
            arr[i2] = heap.poll();
        }
        while (!heap.isEmpty()) {
            arr[i2] = heap.poll();
        }
    }
}
