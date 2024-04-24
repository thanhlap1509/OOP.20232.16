package sourcecode.GameClass.BoardGame;
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
            board[i].prev = board[i - 1];
        }
        board[length - 1].next = board[0];
        board[0].prev = board[length - 1];
    }
    public void display(){
        System.out.print(" ".repeat(4));
        for (int i = 11; i >= 7; i--){
            System.out.print( "|" + board[i].small + " " + board[i].large + "|" +  " ");
        }
        System.out.println();
        System.out.print("|" + board[0].small + " " + board[0].large + "|" + " ");
        System.out.print(" ".repeat(26));
        System.out.println("|" + board[6].small + " " + board[6].large + "|" + " ");
        System.out.print(" ".repeat(4));
        for (int i = 1; i <= 5; i++){
            System.out.print("|"+ board[i].small + " " + board[i].large + "|" + " ");
        }
    }
}
