package com.techinterviews.graphs;

import com.techinterviews.tree.Tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by xeniah on 3/7/15.
 */
public class FunWithTrees {
    public static class TreeNode{
        int data;
        TreeNode left;
        TreeNode right;

        public TreeNode(int data)
        {
            this.data = data;
            left = right = null;
        }

    }

    public static boolean isSubtree(TreeNode r1, TreeNode r2){
        if(r1 == null && r2 == null) return true;
        if(r1 == null || r2 == null) return false;
        if(r1.data == r2.data) {
            return isSubtree(r1.left, r2.left) && isSubtree(r1.right, r2.right);
        }else{
            return isSubtree(r1.left, r2) || isSubtree(r1.right, r2);
        }
    }

    public static void printTree(TreeNode root)
    {
        if(root == null)
        {
            return;
        }

        printTree(root.left);
        printTree(root.right);
        System.out.print(" " + root.data);
    }

    public static void main(String[] args) {

    }


    public static void printBFS(TreeNode root)
    {
        if(root == null) return;
        ArrayList<TreeNode> tn = new ArrayList<TreeNode>();
        tn.add(root);
        while(tn.size() > 0)
        {
            TreeNode n = tn.get(0);
            tn.remove(0);
            if(n != null)
            {
                System.out.println(n.data);
                tn.add(n.left);
                tn.add(n.right);
            }
        }
    }


}
