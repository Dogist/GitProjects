package htlpinkafeld.at.minesweeper.pojo;

/**
 * Created by User on 29.12.2015.
 */
public class Field extends MineField {
    private int nearMines;

    public Field(int nearMines) {
        this.nearMines = nearMines;
    }

    @Override
    public boolean isMine() {
        return false;
    }

    public int getNearMines() {
        return nearMines;
    }
}
