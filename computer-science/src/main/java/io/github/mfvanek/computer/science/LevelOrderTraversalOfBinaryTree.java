package io.github.mfvanek.computer.science;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static io.github.mfvanek.computer.science.AverageOfLevelsInBinaryTree.buildTree;

public class LevelOrderTraversalOfBinaryTree {
  public static void main(String[] args) {
    TreeNode r1 = buildTree(new Integer[]{3,9,20,null,null,15,7});
    TreeNode r2 = buildTree(new Integer[]{3,9,20,null,null,15,7});
    TreeNode r3 = buildTree(new Integer[]{3,9,20,null,null,15,8});
    TreeNode r4 = buildTree(new Integer[]{3,9,20});
    TreeNode r5 = buildTree(new Integer[]{3,20,9});
    TreeNode r6 = buildTree(new Integer[]{1,2});
    TreeNode r7 = buildTree(new Integer[]{1,null,2});

    System.out.println(isSameTree(r6,r7)); // false
    System.out.println(isSameTree(r4,r5)); // false
    System.out.println(isSameTree(r1,r2)); // true
    System.out.println(isSameTree(r1,r3)); // false
    System.out.println(isSameTree(r4,r3)); // false

    /*
    TreeNode root = buildTree(new Integer[]{3,9,20,null,null,15,7});
    System.out.println(levelOrder(root));

    root = buildTree(new Integer[]{1});
    System.out.println(levelOrder(root));
     */
  }

  public static List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    if (root == null) {
      return res;
    }

    Queue<TreeNode> q = new LinkedList<>();
    q.offer(root);
    while (!q.isEmpty()) {
      int nodesOnLevel = q.size();
      List<Integer> levelRes = new ArrayList<>();
      while (nodesOnLevel > 0) {
        TreeNode cur = q.poll();
        levelRes.add(cur.val);
        if (cur.left != null) {
          q.offer(cur.left);
        }
        if (cur.right != null) {
          q.offer(cur.right);
        }
        --nodesOnLevel;
      }
      res.add(levelRes);
    }
    return res;
  }

  public static boolean isSameTree(TreeNode p, TreeNode q) {
    if (p == null && q == null) {
      return true;
    }
    if (p == null || q == null) {
      return false;
    }

    /* iterative
    Deque<TreeNode> q1 = new LinkedList<>();
    q1.push(p);
    Deque<TreeNode> q2 = new LinkedList<>();
    q2.push(q);
    while (!q1.isEmpty() && !q2.isEmpty()) {
      TreeNode n1 = q1.pop();
      TreeNode n2 = q2.pop();
      if (n1.val != n2.val) {
        return false;
      }
      if (!isPointersEqual(n1.left, n2.left)) {
        return false;
      }
      if (!isPointersEqual(n1.right, n2.right)) {
        return false;
      }

      if (n1.right != null) {
        q1.push(n1.right);
      }
      if (n2.right != null) {
        q2.push(n2.right);
      }
      if (n1.left != null) {
        q1.push(n1.left);
      }
      if (n2.left != null) {
        q2.push(n2.left);
      }
    }

    return q1.isEmpty() && q2.isEmpty();
     */

    if (p.val == q.val) {
      return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
    return false;
  }

  private static boolean isPointersEqual(TreeNode first, TreeNode second) {
    if (first != null) {
      return second != null;
    }
    return second == null;
  }
}
