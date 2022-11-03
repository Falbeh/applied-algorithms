package assignment3;

public class ColumnNode extends Node {
    public int size;
    public String name;

    public ColumnNode(String n) {
        super();
        size = 0;
        name = n;
        column = this;
    }
}
