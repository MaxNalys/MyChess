package utils;

public class ColourGameChange {

    boolean isWhite;

    public ColourGameChange() {
        this.isWhite = true;
    }

    public void setWhite(boolean white) {
        isWhite = white;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public void gameChangeColour() {
         isWhite = !isWhite();
    }
}
