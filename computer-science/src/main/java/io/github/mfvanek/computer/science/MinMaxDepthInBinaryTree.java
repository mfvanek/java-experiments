package io.github.mfvanek.computer.science;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import static io.github.mfvanek.computer.science.AverageOfLevelsInBinaryTree.buildTree;
import static io.github.mfvanek.computer.science.AverageOfLevelsInBinaryTree.numberOfLevels;

public class MinMaxDepthInBinaryTree {

  public static void main(String[] args) {
    TreeNode root = buildTree(new Integer[]{1,2,3,null,null,4});
    //print(root);
    //System.out.println("depth = " + numberOfLevels(root)); // 3
    //System.out.println("minDepth = " + minDepth(root)); // 2
    //System.out.println("maxDepth = " + maxDepth(root)); // 3
    //System.out.println(calcPathSum(root));
    System.out.println(hasPathSum(root, 8)); // true
    System.out.println(pathSum(root, 8));

    root = buildTree(new Integer[]{2,null,3,null,4,null,5,null,6});
    //print(root);
    //System.out.println("depth = " + numberOfLevels(root)); // 5
    //System.out.println("minDepth = " + minDepth(root)); // 5
    //System.out.println("maxDepth = " + maxDepth(root)); // 5
    //System.out.println(calcPathSum(root));
    System.out.println(hasPathSum(root, 8)); // false
    System.out.println(pathSum(root, 8));

    root = buildTree(new Integer[]{1,2,3,4,5});
    //print(root);
    //System.out.println("depth = " + numberOfLevels(root)); // 3
    //System.out.println("minDepth = " + minDepth(root)); // 2
    //System.out.println("maxDepth = " + maxDepth(root)); // 3
    //System.out.println(calcPathSum(root));
    System.out.println(hasPathSum(root, 8)); // true
    System.out.println(pathSum(root, 8));
  }

  public static int minDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }

    int left = minDepth(root.left);
    int right = minDepth(root.right);
    if (left == 0 || right == 0) {
      return Math.max(left, right) + 1;
    }
    else {
      return Math.min(left, right) + 1;
    }

    /*
    // BFS
    Queue<TreeNode> q = new LinkedList<>();
    q.offer(root);
    //int minDepth = Integer.MAX_VALUE;
    int currentDepth = 0;
    while (!q.isEmpty()) {
      ++currentDepth;
      int nodesOnLevel = q.size();
      for (int i = 0; i < nodesOnLevel; ++i) {
        TreeNode cur = q.poll();
        if (isLeaf(cur)) {
          // minDepth = Math.min(minDepth, currentDepth);
          return currentDepth;
        }
        if (cur.left != null) {
          q.offer(cur.left);
        }
        if (cur.right != null) {
          q.offer(cur.right);
        }
      }
    }
    // return minDepth;
    return currentDepth;
     */
  }

  public static boolean isLeaf(TreeNode node) {
    return node != null && node.left == null && node.right == null;
  }

  public static int maxDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }

    /*
    // recursive DFS
    return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
     */

    /*
    // BFS
    Queue<TreeNode> q = new LinkedList<>();
    q.offer(root);
    int depth = 0;
    while (!q.isEmpty()) {
      ++depth;
      int nodesOnThatDepth = q.size();
      while (nodesOnThatDepth > 0) {
        TreeNode cur = q.poll();
        if (cur.left != null) {
          q.offer(cur.left);
        }
        if (cur.right != null) {
          q.offer(cur.right);
        }
        --nodesOnThatDepth;
      }
    }
    return depth;
     */

    // iterative DFS
    Deque<Map.Entry<TreeNode, Integer>> q = new LinkedList<>();
    q.push(new AbstractMap.SimpleEntry<>(root, 1));
    int depth = 0;
    while (!q.isEmpty()) {
      Map.Entry<TreeNode, Integer> e = q.pop();
      TreeNode n = e.getKey();
      int curDepth = e.getValue();
      depth = Math.max(depth, curDepth);
      if (n.right != null) {
        q.push(new AbstractMap.SimpleEntry<>(n.right, curDepth + 1));
      }
      if (n.left != null) {
        q.push(new AbstractMap.SimpleEntry<>(n.left, curDepth + 1));
      }
    }
    return depth;
  }

  public static List<Integer> calcPathSum(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    if (root == null) {
      return result;
    }

    // iterative DFS
    Deque<Map.Entry<TreeNode, Integer>> q = new LinkedList<>();
    q.push(new AbstractMap.SimpleEntry<>(root, root.val));
    while (!q.isEmpty()) {
      Map.Entry<TreeNode, Integer> e = q.pop();
      TreeNode n = e.getKey();
      Integer prevPathSum = e.getValue();
      if (isLeaf(n)) {
        result.add(prevPathSum);
      } else {
        if (n.right != null) {
          q.push(new AbstractMap.SimpleEntry<>(n.right, prevPathSum + n.right.val));
        }
        if (n.left != null) {
          q.push(new AbstractMap.SimpleEntry<>(n.left, prevPathSum + n.left.val));
        }
      }
    }
    return result;
  }

  public static boolean hasPathSum(TreeNode root, int targetSum) {
    if (root == null) {
      return false;
    }

    /*
    // iterative DFS
    Deque<Map.Entry<TreeNode, Integer>> q = new LinkedList<>();
    q.push(new AbstractMap.SimpleEntry<>(root, root.val));
    while (!q.isEmpty()) {
      Map.Entry<TreeNode, Integer> e = q.pop();
      TreeNode n = e.getKey();
      Integer prevPathSum = e.getValue();
      if (isLeaf(n)) {
        if (prevPathSum == targetSum) {
          return true;
        }
      } else {
        if (n.right != null) {
          q.push(new AbstractMap.SimpleEntry<>(n.right, prevPathSum + n.right.val));
        }
        if (n.left != null) {
          q.push(new AbstractMap.SimpleEntry<>(n.left, prevPathSum + n.left.val));
        }
      }
    }
    return false;
     */

    /*
    // recursive DFS
    if (root.left == null && root.right == null && root.val == targetSum) {
      return true;
    }
    return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
     */

    // BFS
    Deque<Map.Entry<TreeNode, Integer>> q = new LinkedList<>();
    q.push(new AbstractMap.SimpleEntry<>(root, targetSum - root.val));
    while (!q.isEmpty()) {
      int nodesOnLevel = q.size();
      for (int i = 0; i < nodesOnLevel; ++i) {
        Map.Entry<TreeNode, Integer> e = q.pop();
        TreeNode n = e.getKey();
        Integer sum = e.getValue();
        if (isLeaf(n)) {
          if (sum == 0) {
            return true;
          }
        } else {
          if (n.left != null) {
            q.push(new AbstractMap.SimpleEntry<>(n.left, sum - n.left.val));
          }
          if (n.right != null) {
            q.push(new AbstractMap.SimpleEntry<>(n.right, sum - n.right.val));
          }
        }
      }
    }
    return false;
  }

  public static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
    List<List<Integer>> res = new ArrayList<>();
    if (root == null) {
      return res;
    }

    // iterative DFS
    Deque<Tuple> q = new LinkedList<>();
    q.push(Tuple.of(root, targetSum - root.val));
    while (!q.isEmpty()) {
      Tuple t = q.pop();
      if (isLeaf(t.n)) {
        if (t.sum == 0) {
          res.add(t.path);
        }
      } else {
        if (t.n.right != null) {
          q.push(Tuple.of(t.n.right, t.sum - t.n.right.val, t.path));
        }
        if (t.n.left != null) {
          q.push(Tuple.of(t.n.left, t.sum - t.n.left.val, t.path));
        }
      }
    }
    return res;
  }

  private static class Tuple {
    final TreeNode n;
    final int sum;
    final List<Integer> path;

    private Tuple(TreeNode n, int sum, List<Integer> path) {
      this.n = n;
      this.sum = sum;
      this.path = path;
    }

    static Tuple of(TreeNode n, int sum) {
      Tuple t = new Tuple(n, sum, new ArrayList<>());
      t.path.add(n.val);
      return t;
    }

    static Tuple of(TreeNode n, int sum, List<Integer> path) {
      Tuple t = new Tuple(n, sum, new ArrayList<>(path));
      t.path.add(n.val);
      return t;
    }
  }
}
