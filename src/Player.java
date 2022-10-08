public class Player {
    private boolean isWhite;
    Player(boolean isWhite){
        this.isWhite=isWhite;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public void setWhite(boolean white) {
        isWhite = white;
    }
}
