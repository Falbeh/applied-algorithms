package assignment3;

public class Node {
    public Node left;
    public Node right;
    public Node up;
    public Node down;
    public ColumnNode column;

    public Node() {
        left = this;
        right = this;
        up = this;
        down = this;
    }

    public Node(ColumnNode cn) {
        this();
        left = this;
        right = this;
        up = this;
        down = this;
        column = cn;
    }

    public Node hookright(Node node) {
        node.right=right;
        node.right.left=node;
        node.left=this;
        right=node;
        return node;
    }

    public Node hookDown(Node node) {
        node.up=up;
        node.up.down=node;
        node.up=this;
        down=node;
        return node;
    }

}


