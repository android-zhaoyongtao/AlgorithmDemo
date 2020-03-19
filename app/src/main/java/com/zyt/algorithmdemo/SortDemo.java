package com.zyt.algorithmdemo;

//排序
public class SortDemo {

    public static void maoPaoSort(int[] array) {
        int temp;
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] num = {3, 45, 78, 64, 52, 11, 64, 55, 99, 11, 18};
        QuickSort(num, 0, num.length - 1);
    }

    static int count = 0;

    /**
     * 快速排序
     *
     * @param array 排序的数组
     * @param left  数组的前针
     * @param right 数组后针
     */
    private static void QuickSort(int[] array, int left, int right) {
        //如果left等于right，即数组只有一个元素，直接返回
        if (left >= right) {
            return;
        }
        //设置最左边的元素为基准值
        int key = array[left];
        //数组中比key小的放在左边，比key大的放在右边，key值下标为i
        int i = left;
        int j = right;
        while (i < j) {
            //j向左移，直到遇到比key小的值
            while (array[j] >= key && i < j) {
                j--;
            }
            //i向右移，直到遇到比key大的值
            while (array[i] <= key && i < j) {
                i++;
            }
            //i和j指向的元素交换
            if (i < j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        array[left] = array[i];
        array[i] = key;
        count++;
        QuickSort(array, left, i - 1);
        QuickSort(array, i + 1, right);
    }
}
