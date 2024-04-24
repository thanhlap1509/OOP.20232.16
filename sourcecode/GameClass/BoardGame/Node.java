package sourcecode.GameClass.BoardGame;

public class Node {
    public int small;
    public int large;
    public Node next;
    public Node prev;
    public Node(int small, int large) {
        this.small = small;
        this.large = large;
    }
}
