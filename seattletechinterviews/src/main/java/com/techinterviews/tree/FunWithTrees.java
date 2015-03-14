package com.techinterviews.tree;

import com.techinterviews.tree.Tree;

import java.util.*;

/**
 * Created by xeniah on 3/7/15.
 */
public class FunWithTrees {

    public enum Order{
        PREORDER,
        INORDER,
        POSTORDER
    };

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

    public static TreeNode goldStandard()
    {
        TreeNode tree = new TreeNode(1);

        tree.left = new TreeNode(2);
        tree.right = new TreeNode(3);

        TreeNode left = tree.left;
        TreeNode right = tree.right;

        left.left = new TreeNode(4);
        left.right = new TreeNode(5);

        right.left = new TreeNode(6);
        right.right = new TreeNode(7);

        return tree;

    }

    public static void printList(List<Integer> list)
    {
        for(Integer i : list)
        {
            System.out.print(" " + i);
        }
        System.out.println();
    }

    public static void printTree(TreeNode root, Order traversalOrder)
    {
        if(root == null)
        {
            return;
        }

        switch (traversalOrder)
        {
            case PREORDER:
                System.out.print(" " + root.data);
                printTree(root.left, traversalOrder);
                printTree(root.right, traversalOrder);
                break;
            case INORDER:
                printTree(root.left, traversalOrder);
                System.out.print(" " + root.data);
                printTree(root.right, traversalOrder);
                break;
            case POSTORDER:
                printTree(root.left, traversalOrder);
                printTree(root.right, traversalOrder);
                System.out.print(" " + root.data);
                break;
            default:
                break;
        }
    }

    public static List<Integer> tree2List(TreeNode root, Order traversalOrder)
    {
        List<Integer> list = new ArrayList<Integer>();
        return tree2List(root, list, traversalOrder);
    }


    public static List<Integer> tree2List(TreeNode root, List<Integer> list, Order traversalOrder)
    {
        if(root == null)
        {
            return list;
        }else
        {
            switch (traversalOrder)
            {
                case PREORDER:
                    list.add(root.data);
                    tree2List(root.left, list, traversalOrder);
                    return  tree2List(root.right, list, traversalOrder);
                case INORDER:
                    tree2List(root.left, list, traversalOrder);
                    list.add(root.data);
                    return  tree2List(root.right, list, traversalOrder);
                case POSTORDER:
                    tree2List(root.left, list, traversalOrder);
                    tree2List(root.right, list, traversalOrder);
                    list.add(root.data);
                    return list;
                default:
                    tree2List(root.left, list, traversalOrder);
                    list.add(root.data);
                    return  tree2List(root.right, list, traversalOrder);
            }

        }
    }


    public static void main(String[] args) {
        printList(tree2List(goldStandard(), Order.PREORDER));
        printList(tree2List(goldStandard(), Order.INORDER));

        TreeNode root = BSTFromInorderAndPreorder(tree2List(goldStandard(), Order.PREORDER), tree2List(goldStandard(), Order.INORDER));
        printTree(root, Order.INORDER);


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

    public static TreeNode BSTFromInorderAndPreorder(List<Integer> preorder, List<Integer> inorder)
    {
        TreeNode root = null;
        if(inorder.isEmpty()) return null;
        else
        {
            root = new TreeNode(preorder.get(0));
            preorder.remove(0);

            List<Integer> leftSubtree = new ArrayList<Integer>();
            List<Integer> rightSubtree = new ArrayList<Integer>();
            boolean foundMid = false;
            for(int i = 0; i < inorder.size(); i++)
            {
                if(inorder.get(i) == root.data)
                {
                    foundMid = true;
                }else
                {
                    if(!foundMid)
                    {
                        leftSubtree.add(inorder.get(i));
                    }else
                    {
                        rightSubtree.add(inorder.get(i));
                    }
                }
            }

            root.left = BSTFromInorderAndPreorder(preorder, leftSubtree);
            root.right = BSTFromInorderAndPreorder(preorder, rightSubtree);
        }
        return root;
    }




}
