package sourcecode.GamePieces;

public class Tile {
    private int i;
    private int dan;
    private int quan;
    private int isPointed = 0;
    private int isCollected = 0;
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
    public int getIsPointed() {
        return isPointed;
    }

    public int getIsCollected() {
        return isCollected;
    }
    public void setIsPointed(int i){isPointed = i;}
    public void setIsCollected(int i){isCollected = i;}
}
