package htlpinkafeld.at.minesweeper.pojo;

/**
 * Created by User on 29.12.2015.
 */
public abstract class MineField {
    private boolean flagged;
    private boolean covered=true;

    public abstract boolean isMine();

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
