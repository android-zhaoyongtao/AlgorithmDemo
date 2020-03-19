package com.zyt.algorithmdemo;

/**
 * author : zhaoyongtao
 * e-mail : 285929232@qq.com
 * time   : 2019/2/19
 * desc   : //二叉树的最大路径和，不包含根节点
 */
public class TreeNumberAlgorithm {
    static int ans = Integer.MIN_VALUE;

    public static void main(String[] args) {
        ans = Integer.MIN_VALUE;
        maxSumInLorR(root);
        System.out.println(ans);
    }
    //我感觉这个更简单
    private static int maxSumInLorR(TreeNode root) {//当前节点的左或者右最大路径的和,不包括横跨
        if (root == null) {
            return 0;
        }
        int l = maxSumInLorR(root.left);
        int r = maxSumInLorR(root.right);
        int curSum = Math.max(root.val + l, root.val + r);//不包括横跨最大值
        int curMax = Math.max(curSum, root.val + l + r);//横跨情况的最大值
        ans = Math.max(curMax, ans);
        return curSum;
    }


    static class TreeNode {
        TreeNode(int value) {
            this.val = value;
        }

        TreeNode left;
        TreeNode right;
        int val;
    }

    static TreeNode root;

    static {
        root = new TreeNode(-10);
        TreeNode b = new TreeNode(9);
        TreeNode c = new TreeNode(-20);
        TreeNode d = new TreeNode(15);
        TreeNode e = new TreeNode(7);
        TreeNode f = new TreeNode(-167);
        TreeNode g = new TreeNode(-17);
        root.left = b;
        root.right = c;
        c.left = d;
        c.right = e;
        e.left = f;
        e.right = g;
    }



}
