package com.zyt.algorithmdemo;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * author : zhaoyongtao
 * e-mail : 285929232@qq.com
 * time   : 2019/2/19
 * desc   : 二叉树算法
 */
public class TreeAlgorithm {

    static class TreeNode {
        TreeNode(String value) {
            this.value = value;
        }

        TreeNode left;
        TreeNode right;
        TreeNode next;
        String value;
    }

    static TreeNode a;

    static {
        /*
                   a
                 b    c
               d  e  f  g
         */
        a = new TreeNode("a");
        TreeNode b = new TreeNode("b");
        TreeNode c = new TreeNode("c");
        TreeNode d = new TreeNode("d");
        TreeNode e = new TreeNode("e");
        TreeNode f = new TreeNode("f");
        TreeNode g = new TreeNode("g");

        a.left = b;
        a.right = c;
        b.left = d;
        b.right = e;
        c.left = f;
        c.right = g;
    }


    public static void main(String[] args) {
        prientTree(a);
    }

    /**
     * 前序递归遍历算法：访问根结点-->递归遍历根结点的左子树-->递归遍历根结点的右子树
     * <p>
     * 中序递归遍历算法：递归遍历根结点的左子树-->访问根结点-->递归遍历根结点的右子树
     * <p>
     * 后序递归遍历算法：递归遍历根结点的左子树-->递归遍历根结点的右子树-->访问根结点
     *
     * @param root
     * @return
     */
    static void forEachTree(TreeNode root) {
        if (root == null) {
            return;
        } else {
            System.out.println(root.value);
            forEachTree(root.left);
            forEachTree(root.right);
        }
    }

    //排成一列abdecfg
    static TreeNode flatten(TreeNode root) {//??
        if (root == null) {
            return null;
        }
        TreeNode leftNode = flatten(root.left);
        TreeNode rightNode = flatten(root.right);
        root.left = root.right = null;
        if (leftNode == null) {
            root.right = rightNode;
            return root;
        }

        //如果左子树生成链表不为空，那么用while循环获取最后一个节点，并且它的右指针要指向右子树生成的链表的头节点
        root.right = leftNode;
        TreeNode lastLeft = leftNode;
        while (lastLeft != null && lastLeft.right != null) {
            lastLeft = lastLeft.right;
        }
        lastLeft.right = rightNode;

        return root;
    }

    //abcdefg
    static void prientTree(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            System.out.println(current.value);
            if (current.left != null) {
                ((LinkedList<TreeNode>) queue).add(current.left);
            }
            if (current.right != null) {
                ((LinkedList<TreeNode>) queue).add(current.right);
            }
        }
    }

    //翻转二叉树
    static void recerseTree(TreeNode root) {
        if (root == null) {
            return;
        }

        TreeNode left = root.left;
        TreeNode right = root.right;
        root.right = left;
        root.left = right;

        recerseTree(left);
        recerseTree(right);
    }

    //走迷宫问题
    public int getMinSteps(int[][] matrix) {

        int row = matrix.length;
        int col = matrix[0].length;
        //迷宫可以走四个方向，这个二维数组代表四个方向的x与y的偏移量
        int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        HashSet<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        //把起点加入到队列
        queue.add(0);
        int level = 0;
        while (!queue.isEmpty()) {
            //把该层的节点全部遍历
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int current = queue.poll();
                if (!visited.contains(current)) {
                    visited.add(current);
                    //确定该节点的x与y坐标
                    int currentX = current / col;
                    int currentY = current % col;
                    //假如该点是重点，那么直接返回level
                    if (currentX == matrix.length - 1 && currentY == matrix[0].length - 1) {
                        return level;
                    }
                    //如果不是，那么我们分别把它的四个方向的节点都尝试加入到队列尾端，也就是下一层中
                    for (int j = 0; j < direction.length; j++) {
                        int tempX = currentX + direction[j][0];
                        int tempY = currentY + direction[j][1];
                        //因为1代表墙壁，我们不能走，只能加数值为0的点
                        if (tempX > -1 && tempY > -1 && tempX < row && tempY < col && matrix[tempX][tempY] != 1) {
                            int code = tempX * col + tempY;
                            queue.add(code);
                        }
                    }
                }
            }
            level++;
        }
        return -1;
    }

    //二叉树的最大路径和

}
