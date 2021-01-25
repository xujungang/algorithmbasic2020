package class01;

/**
 * 二分法: 局部最小
 *
 * @author xujungang
 * @date 2021-01-23
 */
public class C07_BSAwesome {

    public static void main(String[] args) {

    }

    public static int getLessIndex(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        if (arr[0] < arr[1]) {
            return 0;
        } else if (arr[arr.length - 2] > arr[arr.length - 1]) {
            return arr.length - 1;
        }
        int L = 0, R = arr.length - 1;
        while (L < R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] > arr[mid - 1]) {
                R = mid - 1;
            } else if (arr[mid] > arr[mid + 1]) {
                L = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
