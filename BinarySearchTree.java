public class BinarySearchTree<E extends Comparable<E>> {
    //variables
    private TreeNode<E> root;
    private int nodeCount;

    public BinarySearchTree () {
        root = null;
        nodeCount = 0;
    }

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

    public int countNodes () {
        return nodeCount;
    }

    public int countLeafNodes () {
        return lnHelper(root);
    }

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

    public int getHeight () {
        return 0;
    }

    public void printInOrder () {
        if (countNodes() == 0) {
            System.out.println();
        } else {
            String str = "";
            str = inOHelper(root);
            System.out.println(str);
        }
    }

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

    public void printPreOrder () {
        if (countNodes() == 0) {
            System.out.println();
        } else {
            String str = "";
            str = preOHelper(root);
            System.out.println(str);
        }
    }

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

    public void printPostOrder () {
        if (countNodes() == 0) {
            System.out.println();
        } else {
            String str = "";
            str = postOHelper(root);
            System.out.println(str);
        }
    }

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

    public E delete(E value) {
        if (value.compareTo(root.getValue()) == 0) {
            if (root.getLeftChild() != null && root.getRightChild() != null) {
                reconnect(root.getRightChild(), root.getLeftChild(), true);
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
                    reconnect(curNode.getLeftChild().getRightChild(), curNode.getLeftChild().getLeftChild(), true);
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
                    reconnect(curNode.getRightChild().getLeftChild(), curNode.getRightChild().getRightChild(), false);
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

    public void reconnect (TreeNode<E> floatNode, TreeNode<E> parent, boolean left) {
        if (left) {
            while (parent.getRightChild() != null) {
                parent = parent.getRightChild();
            }
            parent.setRightChild(floatNode);
        } else {
            while (parent.getLeftChild() != null) {
                parent = parent.getLeftChild();
            }
            parent.setLeftChild(floatNode);
        }
    }
}
