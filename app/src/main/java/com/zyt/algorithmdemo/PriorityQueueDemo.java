package com.zyt.algorithmdemo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * author : zhaoyongtao
 * e-mail : 285929232@qq.com
 * time   : 2019/2/20
 * desc   : 优先级队列
 */
public class PriorityQueueDemo {

    class Interval{
        int start;
        int end;

        public Interval(int start, int end) {
            this.start  = start;
            this.end  = end;
        }
    }


    //合并线段的问题
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        /**
         **用优先级队列把所有线段排好序，按照起始时间
         **/
        PriorityQueue<Interval> priorityQueue = new PriorityQueue<Interval>(new Comparator<Interval>() {
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            };
        });
        for (int i = 0; i < intervals.size(); i++) {
            priorityQueue.add(intervals.get(i));
        }
        priorityQueue.add(newInterval);

        /**
         **用栈保存处理过的线段
         **/
        Stack<Interval> stack = new Stack<>();
        stack.push(priorityQueue.remove());
        /**
         **while循环处理线段
         **/
        while (!stack.isEmpty() && !priorityQueue.isEmpty()) {
            Interval inItem = priorityQueue.remove();
            Interval originalItem = stack.pop();
            /**
             **当线段不完全重叠的时候，取两者的最小开始时间和最大结束时间，生成新的线段
             **/
            if (inItem.start <= originalItem.end && inItem.end > originalItem.end) {
                stack.push(new Interval(originalItem.start, inItem.end));
                /**
                 **当线段完全重叠的时候，取两者的中覆盖面最大的那一线段
                 **/
            } else if (inItem.start <= originalItem.end && originalItem.end >= inItem.end) {
                stack.push(originalItem);
            }
            /**
             **当线段没有重叠的时候，两者一起入栈
             **/
            else {
                stack.push(originalItem);
                stack.push(inItem);
            }
        }
        /**
         **因为stack的输出是倒着来的，所以翻转一次。。。
         **/
        Stack<Interval> stack2 = new Stack<>();
        while (!stack.isEmpty()) {
            stack2.push(stack.pop());
        }
        ArrayList<Interval> arrayList = new ArrayList<>();
        while (!stack2.isEmpty()) {
            arrayList.add(stack2.pop());
        }
        return arrayList;

    }
}
