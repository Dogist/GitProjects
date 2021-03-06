package at.htlpinkafeld.minesweeperv2.util;


import at.htlpinkafeld.minesweeperv2.R;
import at.htlpinkafeld.minesweeperv2.gui.MainActivity;

/**
 * Created by User on 29.12.2015.
 */

//used for defining the default-settings
public enum DifficultyEnum {
    EASY(R.integer.lowDiff_numMines, R.integer.lowDiff_rownum, R.integer.lowDiff_colnum),
    MEDIUM(R.integer.mediumDiff_numMines, R.integer.mediumDiff_rownum, R.integer.mediumDiff_colnum),
    EXPERT(R.integer.highDiff_numMines, R.integer.highDiff_rownum, R.integer.highDiff_colnum),
    SPECIAL(R.integer.special_numMines, R.integer.special_rownum, R.integer.special_colnum);

    private int mineNum;
    private int rowNum;
    private int colNum;
    DifficultyEnum(int mineNumID, int rowNumID, int colNumID) {
        mineNum = MainActivity.getContext().getResources().getInteger(mineNumID);
        rowNum = MainActivity.getContext().getResources().getInteger(rowNumID);
        colNum = MainActivity.getContext().getResources().getInteger(colNumID);
    }

    public int getNumMines() {
        return mineNum;
    }

    ;

    public int getHeight() {
        return rowNum;
    }

    ;

    public int getWidth() {
        return colNum;
    }

    ;
}
