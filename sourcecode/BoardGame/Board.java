package sourcecode.BoardGame;
public class Board {
    public Node[] board;
    final static int length = 12;
    public Board() {
        board = new Node[length];
        board[0] = new Node(0, 1);
        for (int i = 1; i < length; i++){
            if (i == 6){
                board[i] = new Node(0, 1);
            }
            else {
                board[i] = new Node(5, 0);
            }
            board[i - 1].next = board[i];
        }
        board[length - 1].next = board[0];
    }
    public void display(){
        for (int i = 0; i < length;i++){
            System.out.println("Square" + (i + 1) + " has " + board[i].small + " small and " + board[i].large + " large rock");
        }
    }

}
