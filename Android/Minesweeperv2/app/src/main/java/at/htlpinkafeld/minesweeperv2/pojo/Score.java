package at.htlpinkafeld.minesweeperv2.pojo;

/**
 * Created by User on 27.05.2016.
 */
public class Score implements Comparable {
    private int position;
    private String pname;
    private long score;
    private String fieldSize;
    private int mineNum;

    public Score(int position, String pname, long score, String fieldSize, int mineNum) {
        this.position = position;
        this.pname = pname;
        this.score = score;
        this.fieldSize = fieldSize;
        this.mineNum = mineNum;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public String getFieldSize() {
        return fieldSize;
    }

    public void setFieldSize(String fieldSize) {
        this.fieldSize = fieldSize;
    }

    public int getMineNum() {
        return mineNum;
    }

    public void setMineNum(int mineNum) {
        this.mineNum = mineNum;
    }

    @Override
    public int compareTo(Object another) {
        return (int) (score - ((Score) another).getScore());
    }
}
