package assignment3.DancingLinks;

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
        node.right = right;
        node.right.left = node;
        node.left = this;
        right = node;
        return node;
    }

    public Node hookDown(Node node) {
        node.down = down;
        node.down.up = node;
        node.up = this;
        down = node;
        return node;
    }

    // Linking and unlinking methods used for auxiliary DLX routines cover and uncover
    public void unlinkLeftRight() {
        left.right=right;
        right.left=left;
    }
    public void linkLeftRight() {
        right.left=this;
        left.right=this;
    }

    public void unlinkUpDown() {
        up.down=down;
        down.up=up;
    }
    public void linkUpDown() {
        up.down=this;
        down.up=this;
    } 
}
