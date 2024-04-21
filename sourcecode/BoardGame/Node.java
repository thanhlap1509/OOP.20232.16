package sourcecode.BoardGame;

public class Node {
    public int small;
    public int large;
    public Node next;
    public Node(int small, int large) {
        this.small = small;
        this.large = large;
    }
}
