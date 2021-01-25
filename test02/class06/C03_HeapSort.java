package class06;

import common.ArrayUtils;

import java.util.Arrays;

/**
 * 堆排序:
 * N的父节点: (N - 1) / 2
 * N的子节点: 2N + 1, 2N + 2
 * heapInsert: 与自己的父节点比较,上移
 * heapify: 与自己的子节点比较,下移
 * 步骤:
 * 1.遍历数组,将每个元素一次添加到大根堆中;
 * 2.依次交换第一个元素与最后一个元素,并保持大根堆
 *
 * @author xujungang
 * @date 2021-01-24
 */
public class C03_HeapSort {

    public static void main(String[] args) {
        int[] arr = ArrayUtils.createIntArray("7,3,1,5,2,2,4");
        ArrayUtils.printArray(arr);
        int[] arr1 = Arrays.copyOf(arr, arr.length);
        heapSort1(arr1);
        ArrayUtils.printArray(arr1);
        System.out.println("====================");
        int[] arr2 = Arrays.copyOf(arr, arr.length);
        heapSort2(arr2);
        ArrayUtils.printArray(arr2);
    }

    // heapInsert: O(N*logN)
    public static void heapSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }
        for (int i = arr.length - 1; i > 0; i--) {
            swap(arr, 0, i);
            heapify(arr, 0, i);
        }
    }

    // heapify: O(N)
    public static void heapSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = arr.length - 1; i > 0; i--) {
            heapify(arr, i, arr.length);
        }
        for (int i = arr.length - 1; i > 0; i--) {
            swap(arr, 0, i);
            heapify(arr, 0, i);
        }
    }

    // 上升
    private static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    // 下降
    private static void heapify(int[] arr, int index, int size) {
        int left = 2 * index + 1;
        while (left < size) {
            int largest = left + 1 < size && arr[left + 1] > arr[left] ? left + 1 : left;
            largest = arr[largest] > arr[index] ? largest : index;
            if (index == largest) {
                break;
            }
            swap(arr, index, largest);
            index = largest;
            left = 2 * index + 1;
        }
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
