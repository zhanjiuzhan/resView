package sort;

import java.util.Arrays;

/**
 * @author Administrator
 */
public class SortMain {




    public static void main(String[] args) {
        int[] tmp1 = {3, 1, 0, 34, 54, 23, 12, 55, 32, 78, 9};
        SortUtils.sortBySelection(tmp1);
        System.out.println("选择排序: " + Arrays.toString(tmp1));
        int[] tmp2 = {3, 1, 0, 34, 54, 23, 12, 55, 32, 78, 9};
        SortUtils.sortByBubble(tmp2);
        System.out.println("冒泡排序: " + Arrays.toString(tmp2));
        int[] tmp3 = {3, 1, 0, 34, 54, 23, 12, 55, 32, 78, 9};
        SortUtils.sortByInsertion(tmp3);
        System.out.println("插入排序: " + Arrays.toString(tmp3));
        int[] tmp4 = {3, 1, 0, 34, 54, 23, 12, 55, 32, 78, 9};
        tmp4 = SortUtils.sortByMerge(tmp4);
        System.out.println("归并排序: " + Arrays.toString(tmp4));
        int[] tmp5 = {3, 1, 0, 34, 54, 23, 12, 55, 32, 78, 9};
        SortUtils.sortByQuick1(tmp5, 0, tmp5.length - 1);
        System.out.println("插入排序1: " + Arrays.toString(tmp5));
        int[] tmp6 = {3, 1, 0, 34, 54, 23, 12, 55, 32, 78, 9};
        SortUtils.sortByQuick2(tmp6, 0, tmp6.length - 1);
        System.out.println("插入排序2: " + Arrays.toString(tmp6));
    }
}
