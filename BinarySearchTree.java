public class BinarySearchTree<E extends Comparable<E>> {
    private TreeNode<E> root;
    private int nodeCount;

    /**
     * creates a new, empty tree
     * sets the root to null
     * sets the nodeCount to 0
     */
    public BinarySearchTree () {
        root = null;
        nodeCount = 0;
    }

    /**
     * creates a new node with the value specified by the parameter with both children null
     * makes a temporary node that is the root
     * if the tree is empty, set the root to the new node
     * if not, compare the value of the new node to the current node and follow in the direction of the value being searched for
     * if the value is found as the child of the current node, end the loop
     * set the proper child to the new node
     * increment the node count
     * @param value the value of the node to be added
     */
    public void add (E value) {
        TreeNode<E> newNode = new TreeNode<>(value, null, null);
        TreeNode<E> curNode = root;
        if (curNode == null) {
            root = newNode;
        } else {
            boolean nextNode = true;
            while (nextNode) {
                if (newNode.getValue().compareTo(curNode.getValue()) <= 0) {
                    if (curNode.getLeftChild() != null) {
                        curNode = curNode.getLeftChild();
                    } else {
                        nextNode = false;
                    }
                } else {
                    if (curNode.getRightChild() != null) {
                        curNode = curNode.getRightChild();
                    } else {
                        nextNode = false;
                    }
                }
            }
            if (newNode.getValue().compareTo(curNode.getValue()) <= 0) {
                curNode.setLeftChild(newNode);
            } else {
                curNode.setRightChild(newNode);
            }
        }
        nodeCount++;
    }

    /**
     * trace through the tree, checking each node's value against the value being looked for
     * if the value is found, return true
     * if the end of the path is reached and the value is not found, return false
     * @param value the value being searched for
     * @return a boolean that represents if the value is there
     */
    public boolean contains (E value) {
        TreeNode<E> curNode = root;
        boolean leaf = false;
        while (!leaf) {
            if (value.compareTo(curNode.getValue()) == 0) {
                return true;
            } else if (value.compareTo(curNode.getValue()) < 0 && curNode.getLeftChild() != null) {
                curNode = curNode.getLeftChild();
            } else if (value.compareTo(curNode.getValue()) > 0 && curNode.getRightChild() != null) {
                curNode = curNode.getRightChild();
            } else {
                leaf = true;
            }
        }
        return false;
    }

    /**
     * returns the amount of nodes in the tree
     * @return the amount of nodes in the tree
     */
    public int countNodes () {
        return nodeCount;
    }

    /**
     * calls lnHelper with the root
     * @return the number of leaf nodes
     */
    public int countLeafNodes () {
        return lnHelper(root);
    }

    /**
     * uses recursion to find the amount of leaf nodes
     * if a leaf node is reached, return one
     * if only one child exists, recursive call with it
     * if both children exist, add the two recursive calls with each child
     * @param curNode the node to be checked if it is a leaf
     * @return the number of leaf nodes
     */
    public int lnHelper (TreeNode<E> curNode) {
        if (curNode.getRightChild() == null && curNode.getLeftChild() == null) {
            return 1;
        } else if (curNode.getLeftChild() == null) {
            return lnHelper(curNode.getRightChild());
        } else if (curNode.getRightChild() == null) {
            return lnHelper(curNode.getLeftChild());
        } else {
            return lnHelper(curNode.getLeftChild()) + lnHelper(curNode.getRightChild());
        }
    }

    /**
     * calls heightHelper and subtracts one
     * @return the height of the tree
     */
    public int getHeight () {
        return heightHelper(root) - 1;
    }

    /**
     * recursively finds the height of the tree
     * if there are two children, return the maximum of the call of both children
     * if there is one child, return one plus the recursive call of the child
     * if there are no children, return one
     * @param tempNode the node passed in to be checked
     * @return the height of the tree
     */
    public int heightHelper(TreeNode<E> tempNode) {
        if (tempNode.getLeftChild() != null && tempNode.getRightChild() != null) {
            return Math.max(heightHelper(tempNode.getLeftChild()), heightHelper(tempNode.getRightChild())) + 1;
        } else if (tempNode.getLeftChild() != null) {
            return 1 + heightHelper(tempNode.getLeftChild());
        } else if (tempNode.getRightChild() != null) {
            return 1 + heightHelper(tempNode.getRightChild());
        } else {
            return 1;
        }
    }

    /**
     * calls inOHelper if there are nodes in the tree, printing the result
     */
    public void printInOrder () {
        if (countNodes() == 0) {
            System.out.println();
        } else {
            String str = "";
            str = inOHelper(root);
            System.out.println(str);
        }
    }

    /**
     * recursively prints the tree in order
     * if neither child exists, return the value as a string
     * if the left child exists, return the value plus the recursive call of the child
     * if the right child exists, return the recursive call of the child plus the value
     * if both children exist, return the recursive call of the left plus the value plus the recursive call of the right
     * @param curNode the root node to be printed and whose children continue the recursive call
     * @return a string containing the values in order
     */
    public String inOHelper (TreeNode<E> curNode) {
        if (curNode.getLeftChild() == null && curNode.getRightChild() == null) {
            return curNode.getValue() + "";
        } else if (curNode.getLeftChild() == null) {
            return curNode.getValue() + " " + inOHelper(curNode.getRightChild());
        } else if (curNode.getRightChild() == null) {
            return inOHelper(curNode.getLeftChild()) + " " + curNode.getValue();
        } else {
            return inOHelper(curNode.getLeftChild()) + " " + curNode.getValue() + " " + inOHelper(curNode.getRightChild());
        }
    }

    /**
     * if there are nodes in the tree, calls preOHelper and prints the result
     */
    public void printPreOrder () {
        if (countNodes() == 0) {
            System.out.println();
        } else {
            String str = "";
            str = preOHelper(root);
            System.out.println(str);
        }
    }

    /**
     * recursively prints the tree in preorder
     * if neither child exists, print the value as a string
     * if one child exists, print the value plus the recursive call of the child
     * if both children exist, print the value plus the recursive call of the left child plus the recursive call of the right child
     * @param curNode the node to be printed and whose children continue the recursive call
     * @return a string with all values in the tree sorted in pre-order
     */
    public String preOHelper (TreeNode<E> curNode) {
        if (curNode.getLeftChild() == null && curNode.getRightChild() == null) {
            return curNode.getValue() + "";
        } else if (curNode.getLeftChild() == null) {
            return curNode.getValue() + " " + preOHelper(curNode.getRightChild());
        } else if (curNode.getRightChild() == null) {
            return curNode.getValue() + " " + preOHelper(curNode.getLeftChild());
        } else {
            return curNode.getValue() + " " + preOHelper(curNode.getLeftChild()) + " " + preOHelper(curNode.getRightChild());
        }
    }

    /**
     * if there are nodes in the tree, calls postOHelper and prints the result
     */
    public void printPostOrder () {
        if (countNodes() == 0) {
            System.out.println();
        } else {
            String str = "";
            str = postOHelper(root);
            System.out.println(str);
        }
    }

    /**
     * recursively prints the tree in post order
     * if neither child exists, return the value as a string
     * if one child exists, return the recursive call of the child plus the value
     * if both children exist, return the recursive call of the left child plus the recursive call of the right child plus the value
     * @param curNode the node to be printed and whose children continue the recursive call
     * @return a string containing all the values in the tree sorted in post order
     */
    public String postOHelper (TreeNode<E> curNode) {
        if (curNode.getLeftChild() == null && curNode.getRightChild() == null) {
            return curNode.getValue() + "";
        } else if (curNode.getLeftChild() == null) {
            return postOHelper(curNode.getRightChild()) + " " + curNode.getValue();
        } else if (curNode.getRightChild() == null) {
            return postOHelper(curNode.getLeftChild()) + " " + curNode.getValue();
        } else {
            return postOHelper(curNode.getLeftChild()) + " " + postOHelper(curNode.getRightChild()) + " " + curNode.getValue();
        }
    }

    /**
     * deletes a node containing a specified value
     * special case if the deleted node is the root, to change the variable
     * calls helper method getParent, returning the parent of the node to be deleted and storing that node
     * if it is the left child of that parent:
     * if both grandchildren exist, call reconnect to attach the right subtree onto the left grandchild and set the left child to the left grandchild
     * if one grandchild exists, set the left child to that grandchild
     * if no grandchildren exist, set the left child to null
     * subtract from nodeCount
     * return value
     * if it is the right child of that parent:
     * do the same as with the left child but in the other direction
     * subtract from nodeCount
     * return the value
     * @param value the value of the node to be deleted
     * @return the value
     */
    public E delete(E value) {
        if (value.compareTo(root.getValue()) == 0) {
            if (root.getLeftChild() != null && root.getRightChild() != null) {
                reconnect(root.getRightChild(), root.getLeftChild());
                root = root.getLeftChild();
            } else if (root.getLeftChild() != null) {
                root = root.getLeftChild();
            } else if (root.getRightChild() != null) {
                root = root.getRightChild();
            } else {
                root = null;
            }
            nodeCount--;
            return value;
        } else {
            TreeNode<E> curNode = getParent(value);
            if (value.compareTo(curNode.getValue()) < 0) {
                if (curNode.getLeftChild().getLeftChild() != null && curNode.getLeftChild().getRightChild() != null) {
                    reconnect(curNode.getLeftChild().getRightChild(), curNode.getLeftChild().getLeftChild());
                    curNode.setLeftChild(curNode.getLeftChild().getLeftChild());
                } else if (curNode.getLeftChild().getRightChild() != null) {
                    curNode.setLeftChild(curNode.getLeftChild().getRightChild());
                } else if (curNode.getLeftChild().getLeftChild() != null) {
                    curNode.setLeftChild(curNode.getLeftChild().getLeftChild());
                } else {
                    curNode.setLeftChild(null);
                }
                nodeCount--;
                return value;
            } else {
                if (curNode.getRightChild().getLeftChild() != null && curNode.getRightChild().getRightChild() != null) {
                    reconnect(curNode.getRightChild().getRightChild(), curNode.getRightChild().getLeftChild());
                    curNode.setRightChild(curNode.getRightChild().getLeftChild());
                } else if (curNode.getRightChild().getRightChild() != null) {
                    curNode.setRightChild(curNode.getRightChild().getRightChild());
                } else if (curNode.getRightChild().getLeftChild() != null) {
                    curNode.setRightChild(curNode.getRightChild().getLeftChild());
                } else {
                    curNode.setRightChild(null);
                }
                nodeCount--;
                return value;
            }
        }
    }

    /**
     * trace through the tree in the direction of the value
     * stop when a node whose child is that value is found and return said node
     * if no such node is found, return null
     * @param value the value to be searched for
     * @return the parent of the node to be deleted
     */
    public TreeNode<E> getParent (E value) {
        TreeNode<E> curNode = root;
        boolean leaf = false;
        while (!leaf) {
            if (value.compareTo(curNode.getValue()) == 0) {
                return root;
            } else if (value.compareTo(curNode.getValue()) < 0 && curNode.getLeftChild() != null) {
                if (value.compareTo(curNode.getLeftChild().getValue()) == 0) {
                    return curNode;
                }
                curNode = curNode.getLeftChild();
            } else if (value.compareTo(curNode.getValue()) > 0 && curNode.getRightChild() != null) {
                if (value.compareTo(curNode.getRightChild().getValue()) == 0) {
                    return curNode;
                }
                curNode = curNode.getRightChild();
            } else {
                leaf = true;
            }
        }
        return null;
    }

    /**
     * reinserts the disconnected subtree into its place in the tree in the case that a node with two children is deleted
     * loop through every rightmost child of the left subtree until the floating subtree can be inserted
     * @param floatNode the node to be reinserted
     * @param parent the node replacing the deleted node
     */
    public void reconnect (TreeNode<E> floatNode, TreeNode<E> parent) {
            while (parent.getRightChild() != null) {
                parent = parent.getRightChild();
            }
            parent.setRightChild(floatNode);
    }
}
