package sourcecode.GamePieces;

public class GameBoard {
    public Tile tiles[];
    public GameBoard(){
        tiles = new Tile[12];
        for (int i = 0; i < 12; i++){
            if (i == 5 || i == 11) tiles[i] = new Tile(i, 0, 1);
            else tiles[i] = new Tile(i, 5, 0);
        }
    }
}
