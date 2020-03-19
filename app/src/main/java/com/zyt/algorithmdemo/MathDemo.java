package com.zyt.algorithmdemo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MathDemo {


    public static void main(String[] args) {

//        num_NM();
//        listAllSub();
        printN(5);
    }

    //n*m棋盘的长方形个数和正方形个数
    private static void num_NM() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int sumz = 0;//正方形个数
        int sumc = 0;
        if (N > M) {
            for (int i = M - 1; i >= 0; i--) {
                sumz += (N - i) * (M - i);
            }
        } else {
            for (int i = N - 1; i >= 0; i--) {
                sumz += (N - i) * (M - i);
            }
        }
        // 长方形个数
        //(1+2+3+...+m)*(1+2+3+...+n)=n*m(n+1)*(m+1)/4
        sumc = N * M * (N + 1) * (M + 1) / 4 - sumz;//长 - 正 个数
        System.out.println(sumz + "," + sumc);
    }

    //写出所有数组的子序列
    static void listAllSub() {
        int[] arr = {1, 2};

        List<Integer> aList = new ArrayList<Integer>();
        List<Integer> bList = new ArrayList<Integer>();
        for (int i = 0; i < arr.length; i++) {
            aList.add(arr[i]);
        }
        getSonSet1(0, aList, bList); //方法1，递归法

        System.out.println("----数组arr公用，分割线-----");

        getSonSet2(arr, arr.length); //方法2，按位对应法
    }

    /*
     * 递归法
     */
    public static void getSonSet1(int i, List<Integer> aList, List<Integer> bList) {
        if (i > aList.size() - 1) {
            if (bList.size() <= 0) {
                System.out.print("@");
            } else {
                /*for(int v:bList){
                    System.out.print(v+",");//可以直接用这种方法输出bList数组里所有值，但是每个子数组最后就会带逗号
                }*/
                System.out.print(bList.get(0));
                for (int m = 1; m < bList.size(); m++) {
                    System.out.print("," + bList.get(m));
                }
            }
            System.out.println();
        } else {
            bList.add(aList.get(i));
            getSonSet1(i + 1, aList, bList);
            int bLen = bList.size();
            bList.remove(bLen - 1);
            getSonSet1(i + 1, aList, bList);
        }
    }


    /*
     *按位对应法。
     */
    private static void getSonSet2(int[] arr, final int length) {
        int mark = 0;
        final int nEnd = 1 << length;//*2
        boolean bNullSet = false;
        for (mark = 0; mark < nEnd; mark++) {
            bNullSet = true;
            for (int i = 0; i < length; i++) {
                if (((1 << i) & mark) != 0) {//该位有元素输出
                    bNullSet = false;
                    System.out.print(arr[i] + ",");
                }
            }
            if (bNullSet) {//空集合
                System.out.print("@");
            }
            System.out.println();
        }
    }

    //回型打印二维数组
    private static void printN(final int n) {
        int[][] arr = new int[n][n];
        int count = 1;
        //i代表圈数
        for (int round = 0; round < n / 2; round++) {
            //绘制第一条线（上）
            for (int j = round, k = round; k < n - round - 1; k++) {
                arr[j][k] = count++;
            }
            //绘制第二条线（右）
            for (int j = round, k = n - round - 1; j < n - round - 1; j++) {
                arr[j][k] = count++;
            }
            //绘制第三条线(下)
            for (int j = n - round - 1, k = n - round - 1; k > round; k--) {
                arr[j][k] = count++;
            }
            //绘制第四条线（左）
            for (int j = n - round - 1, k = round; j > round; j--) {
                arr[j][k] = count++;
            }
        }
        //如果是奇数，则中间的填充
        if (n % 2 == 1) {
            arr[n / 2][n / 2] = count;
        }
        //遍历
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
