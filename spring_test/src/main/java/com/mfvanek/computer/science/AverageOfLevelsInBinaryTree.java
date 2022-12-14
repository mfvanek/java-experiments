package com.mfvanek.computer.science;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

public class AverageOfLevelsInBinaryTree {

  public static void main(String[] args) {
    System.out.println("depth = " + numberOfLevels(null)); // 0
    print(null);
    System.out.println(averageOfLevels(null));

    TreeNode root = buildTree(new Integer[]{1,2,3,null,null,4});
    System.out.println("depth = " + numberOfLevels(root)); // 3
    print(root);
    System.out.println(averageOfLevels(root));

    root = buildTree(new Integer[]{3,9,20,null,null,15,7});
    System.out.println("depth = " + numberOfLevels(root)); // 3
    print(root);
    System.out.println(averageOfLevels(root));

    root = buildTree(new Integer[]{3,9,20,15,7});
    System.out.println("depth = " + numberOfLevels(root)); // 3
    print(root);
    System.out.println(averageOfLevels(root));

    root = buildTree(new Integer[]{1,null,3,4,5,null,null,6,7});
    System.out.println("depth = " + numberOfLevels(root)); // 4
    print(root);
    System.out.println(averageOfLevels(root));
  }

  public static List<Double> averageOfLevels(TreeNode root) {
    List<Double> res = new ArrayList<>();
    if (root == null) {
      return res;
    }

    Queue<TreeNode> q = new LinkedList<>();
    q.offer(root);
    while (!q.isEmpty()) {
      int nodesOnLevel = q.size();
      double levelSum = 0.0;
      int i = q.size();
      while (i > 0) {
        TreeNode cur = Objects.requireNonNull(q.poll());
        levelSum += cur.val;
        if (cur.left != null) {
          q.offer(cur.left);
        }
        if (cur.right != null) {
          q.offer(cur.right);
        }
        --i;
      }
      res.add(levelSum / nodesOnLevel);
    }
    return res;
  }

  public static int numberOfLevels(TreeNode root) {
    /*
    // recursive
    if (root == null) {
      return 0;
    }
    return Math.max(numberOfLevels(root.left), numberOfLevels(root.right)) + 1;
     */
    if (root == null) {
      return 0;
    }
    Deque<TreeNode> q = new LinkedList<>();
    q.addLast(root);
    int level = 0;
    while (!q.isEmpty()) {
      ++level;
      int size = q.size(); // amount of nodes on current level
      while (size > 0) {
        TreeNode cur = q.removeFirst();
        if (cur.left != null) {
          q.addLast(cur.left);
        }
        if (cur.right != null) {
          q.addLast(cur.right);
        }
        --size;
      }
    }
    return level;
  }

  // root = [3,9,20,null,null,15,7]
  // root = [3,9,20,15,7]
  public static TreeNode buildTree(Integer[] arr) {
    if (arr.length == 0) {
      return null;
    }

    TreeNode root = new TreeNode(arr[0]);
    Deque<TreeNode> q = new LinkedList<>();
    q.addLast(root);
    int i = 1;
    while (!q.isEmpty() && i < arr.length) {
      TreeNode current = q.removeFirst();
      Integer value = arr[i++];
      if (value != null) {
        TreeNode left = new TreeNode(value);
        q.addLast(left);
        current.left = left;
      }
      if (i < arr.length) {
        value = arr[i++];
        if (value != null) {
          TreeNode right = new TreeNode(value);
          q.addLast(right);
          current.right = right;
        }
      }
    }
    return root;
  }

  public static void print(TreeNode root) {
    if (root == null) {
      System.out.println("root = null");
      return;
    }

    Deque<TreeNode> q = new LinkedList<>();
    q.addLast(root);
    System.out.print("root = [");
    while (!q.isEmpty()) {
      int levelSize = q.size();
      for (int i = 0; i < levelSize; ++i) {
        TreeNode cur = q.removeFirst();
        if (cur.left != null) {
          q.addLast(cur.left);
        }
        if (cur.right != null) {
          q.addLast(cur.right);
        }
        System.out.print(cur.val);
        if (!q.isEmpty()) {
          System.out.print(",");
        }
      }
      if (!q.isEmpty()) {
        System.out.println();
      }
    }
    System.out.print("]");
    System.out.println();
  }
}
