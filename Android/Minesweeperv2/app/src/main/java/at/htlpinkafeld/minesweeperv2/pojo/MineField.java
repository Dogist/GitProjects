package at.htlpinkafeld.minesweeperv2.pojo;

/**
 * Created by User on 29.12.2015.
 */
public class MineField {
    private boolean flagged;
    private boolean covered = true;
    private boolean mine;
    private int nearMines;

    public MineField() {
    }

    protected MineField(boolean ismine) {
        mine = ismine;
    }

    public MineField(int nearMines) {
        this.nearMines = nearMines;
    }

    public int getNearMines() {
        return nearMines;
    }

    public void setNearMines(int nearMines) {
        this.nearMines = nearMines;
    }

    public boolean isMine() {
        return mine;
    }

    public void setMine(boolean mine) {
        this.mine = mine;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
    }

    public boolean isCovered() {
        return covered;
    }

    public void setCovered(boolean covered) {
        this.covered = covered;
    }
}
