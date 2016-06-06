package at.htlpinkafeld.minesweeperv2.pojo;

/**
 * Created by User on 20.05.2016.
 */
public class Game {
    private int game_id;
    private int numMines;
    private int fieldsUncovered;
    //saves game board
    private MineField[][] board;
    private long time;

    public Game(int game_id, MineField[][] board, int numMines, int fieldsUncovered, long time) {
        this.game_id = game_id;
        this.board = board;
        this.numMines = numMines;
        this.fieldsUncovered = fieldsUncovered;
        this.time=time;
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public int getFieldsUncovered() {
        return fieldsUncovered;
    }

    public void setFieldsUncovered(int fieldsUncovered) {
        this.fieldsUncovered = fieldsUncovered;
    }

    public int getNumMines() {
        return numMines;
    }

    public void setNumMines(int numMines) {
        this.numMines = numMines;
    }

    public MineField[][] getBoard() {
        return board;
    }

    public void setBoard(MineField[][] board) {
        this.board = board;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
