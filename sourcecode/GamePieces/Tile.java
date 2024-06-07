package sourcecode.GamePieces;

public class Tile {
    private int i;
    private int dan;
    private int quan;
    public Tile(int i,int dan, int quan){
        this.i = i;
        this.dan= dan;
        this.quan = quan;
    }

    public int getI() {
        return i;
    }

    public int getDan() {
        return dan;
    }

    public int getQuan() {
        return quan;
    }

    public void setDan(int dan) {
        this.dan = dan;
    }

    public void setQuan(int quan) {
        this.quan = quan;
    }
}
