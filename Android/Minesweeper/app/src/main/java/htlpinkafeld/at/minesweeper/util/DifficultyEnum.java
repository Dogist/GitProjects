package htlpinkafeld.at.minesweeper.util;

import htlpinkafeld.at.minesweeper.gui.MainActivity;
import htlpinkafeld.at.minesweeper.R;

/**
 * Created by User on 29.12.2015.
 */

//used for defining the default-settings
public enum DifficultyEnum {
    EASY(R.integer.lowDiff_numMines,R.integer.lowDiff_rownum,R.integer.lowDiff_colnum),
    MEDIUM(R.integer.mediumDiff_numMines,R.integer.mediumDiff_rownum,R.integer.mediumDiff_colnum),
    EXPERT(R.integer.highDiff_numMines,R.integer.highDiff_rownum,R.integer.highDiff_colnum),
    SPECIAL(R.integer.special_numMines,R.integer.special_rownum,R.integer.special_colnum);

    DifficultyEnum(int mineNumID, int rowNumID, int colNumID) {
        mineNum=MainActivity.getContext().getResources().getInteger(mineNumID);
        rowNum=MainActivity.getContext().getResources().getInteger(rowNumID);
        colNum=MainActivity.getContext().getResources().getInteger(colNumID);
    }
    private int mineNum;
    private int rowNum;
    private int colNum;

    public int getNumMines(){
        return mineNum;
    };
    public int getHeight(){
        return rowNum;
    };
    public int getWidth(){
        return colNum;
    };
}
