package sort;

import java.util.Arrays;

/**
 * @author Administrator
 */
final class SortUtils {

    /**
     * 选择排序 不稳定 时间复杂度n的平方 空间复杂度n
     * 原理: 选择最小的放在前面
     */
    static void sortBySelection(int[] number) {
        for (int i = 0; i < number.length - 1; i++) {
            int minMark = i;
            for (int j = i + 1; j < number.length; j++) {
                if (number[minMark] > number[j]) {
                    minMark = j;
                }
            }
            if (minMark != i) {
                number[i] = number[i] + number[minMark];
                number[minMark] = number[i] - number[minMark];
                number[i] = number[i] - number[minMark];
            }
        }
    }

    /**
     * 冒泡排序
     * 原理：两两比较 最后一个肯定是最大的
     * @param number
     */
    static void sortByBubble(int[] number) {
        for (int i = 0; i < number.length ; i++) {
            for (int j = 1; j < number.length - i; j++) {
                if (number[j - 1] > number[j]) {
                    number[j - 1] = number[j - 1] + number[j];
                    number[j] = number[j - 1] - number[j];
                    number[j - 1] = number[j - 1] - number[j];
                }
            }
        }
    }

    /**
     * 插入排序
     * 原理: 前面渐渐成为有序队列
     * @param number
     */
    static void sortByInsertion(int[] number) {
        for (int i = 1; i < number.length; i++) {
            int tmp = number[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (number[j] > tmp) {
                    number[j+1] = number[j];
                } else {
                    break;
                }
            }
            number[j+1] = tmp;
        }
    }

    /**
     * 归并排序
     * 原理: 二分法排序
     * @param number
     */
    static int[] sortByMerge(int[] number) {
        if (number.length < 2) {
            return number;
        } else {
            int slip = number.length / 2;
            return merge(
                    sortByMerge(Arrays.copyOfRange(number, 0, slip)),
                    sortByMerge(Arrays.copyOfRange(number, slip, number.length)));
        }
    }

    private static int[] merge(int[] left, int[] right) {
        int[] tmp = new int[left.length + right.length];
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (left[i] < right[j]) {
                tmp[k++] = left[i++];
            } else {
                tmp[k++] = right[j++];
            }
        }

        while (i < left.length) {
            tmp[k++] = left[i++];
        }
        while (j < right.length) {
            tmp[k++] = right[j++];
        }

        return tmp;
    }

    static void sortByQuick1(int[] number, int low, int height) {
        if (low < height) {
            int index = findIndex(number, low, height);
            sortByQuick1(number, low, index -1);
            sortByQuick1(number, index + 1, height);
        }
    }

    private static int findIndex(int[] number, int low, int height) {
        int tmp = number[low];
        while (low < height) {
            while (low < height && number[height] > tmp) {
                height--;
            }
            number[low] = number[height];
            while (low < height && number[low] < tmp) {
                low++;
            }
            number[height] = number[low];
        }
        number[low] = tmp;
        return low;
    }

    static void sortByQuick2(int[] number, int low, int height) {
        if (low < height) {
            int index = findIndex2(number, low, height);
            sortByQuick2(number, low, index -1);
            sortByQuick2(number, index + 1, height);
        }
    }

    // {3, 1, 0, 34, 54, 23, 12, 55, 32, 78, 9}
    private static int findIndex2(int[] number, int low, int height) {
        int index = low + 1;
        for (int i = index; i <= height; i++) {
            if (number[i] < number[low]) {
                swap(number, i, index);
                index++;
            }
        }
        swap(number, low, index - 1);
        return index - 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
