package htlpinkafeld.at.minesweeper.service;

import java.util.Arrays;

import htlpinkafeld.at.minesweeper.pojo.Field;
import htlpinkafeld.at.minesweeper.pojo.Mine;
import htlpinkafeld.at.minesweeper.pojo.MineField;

/**
 * Created by User on 29.12.2015.
 */
public class FieldServiceClass {

    private static FieldServiceClass sc;

    //defines number of mines
    private static int numMines=10;
    //defines the width and height of the array/game board
    private static int width=9;
    private static int height=9;
    //used to count if every mine is discovered
    private int fieldsUncovered=0;
    //used to keep the above settings and the array compatible
    private static boolean changed=false;

    //saves the game board
    private  final MineField[][] board;

    private FieldServiceClass(){
        board=new MineField[width][height];
        int randx, randy;
        for(int i=0;i<numMines;i++){
            do{
                randx= (int) Math.floor(Math.random()* width);
                randy= (int) Math.floor(Math.random()* height);
            }while(board[randx][randy]!=null);
            board[randx][randy]=new Mine();
        }
        for(int x=0;x< width;x++){
            for(int y=0;y< height;y++){
                if(board[x][y]==null)
                    board[x][y]=new Field(this.getNearMines(x, y));
            }
        }
    };

    //Singelton-getInstance which automatically recreates itself, if the board and the settings are incompatible
    public static FieldServiceClass getFieldServiceClass(){
        if(sc==null){
            sc=new FieldServiceClass();
        }
        if(changed==true) forceRecreate();
        return sc;
    }

    //used for the number in the field
    private int getNearMines(int fieldx, int fieldy){
        int num=0;
        MineField f;
        //WEST
        if(fieldx>0){
            f=board[fieldx-1][fieldy];
            if(f!=null&&f.isMine())
                num++;
        }
        //EAST
        if(fieldx<(width -1)){
            f=board[fieldx+1][fieldy];
            if(f!=null&&f.isMine())
                num++;
        }
        //NORTH
        if(fieldy>0){
            f=board[fieldx][fieldy-1];
            if(f!=null&&f.isMine())
                num++;
        }
        //SOUTH
        if(fieldy<(height -1)){
            f=board[fieldx][fieldy+1];
            if(f!=null&&f.isMine())
                num++;
        }
        //northwest
        if (fieldy > 0&&fieldx > 0) {
            f=board[fieldx-1][fieldy-1];
            if(f!=null&&f.isMine())
                num++;
        }
        //northeast
        if (fieldy > 0&&fieldx  < (width - 1)) {
            f=board[fieldx+1][fieldy-1];
            if(f!=null&&f.isMine())
                num++;
        }
        //southeast
        if (fieldy < (height - 1) &&fieldx < (width - 1)) {
            f=board[fieldx+1][fieldy+1];
            if(f!=null&&f.isMine())
                num++;
        }
        //southwest
        if (fieldy < (height - 1)&&fieldx > 0) {
            f=board[fieldx-1][fieldy+1];
            if(f!=null&&f.isMine())
                num++;
        }
        return num;
    }

    //gets Field from the two-dimensional-array
    public MineField getField(int x,int y){
        return board[x][y];
    }

    //getter and setter for the settings, etc.
    public static int getNumMines() {
        return numMines;
    }

    public static void setNumMines(int numMines) {
        FieldServiceClass.numMines = numMines;
        changed=true;
    }

    public static int getWidth() {
        return width;
    }

    public static void setWidth(int width) {
        FieldServiceClass.width = width;
        changed=true;
    }

    public static int getHeight() {
        return height;
    }

    public static void setHeight(int height) {
        FieldServiceClass.height = height;
        changed=true;
    }

    public int getFieldsUncovered() {
        return fieldsUncovered;
    }

    //resets the game without changing the width, height and minenumber
    public void reset(){
        fieldsUncovered=0;
        for(int i=0;i<board.length;i++){
            Arrays.fill(board[i],null);
        }
        int randx, randy;
        for(int i=0;i<numMines;i++){
            do{
                randx= (int) Math.floor(Math.random()* width);
                randy= (int) Math.floor(Math.random()* height);
            }while(board[randx][randy]!=null);
            board[randx][randy]=new Mine();
        }
        for(int x=0;x< width;x++){
            for(int y=0;y< height;y++){
                if(board[x][y]==null)
                    board[x][y]=new Field(this.getNearMines(x, y));
            }
        }
    }

    //forcibly recreates the game with different width, height and minenumber
    public static void forceRecreate(){
        sc=new FieldServiceClass();
        changed=false;
    }

    //used to auto-clear Fields, if there are no nearby mines. Only method called to uncover Fields
    public void clearAround(int x, int y){
        if(board[x][y].isCovered()) {
            board[x][y].setCovered(false);
            if (!board[x][y].isMine()) {
                fieldsUncovered++;
                Field f = (Field) board[x][y];
                if (f.getNearMines() == 0) {
                    //west
                    if (x > 0) {
                        clearAround(x - 1, y);
                    }
                    //east
                    if (x < (width - 1)) {
                        clearAround(x + 1, y);
                    }
                    //north
                    if (y > 0) {
                        clearAround(x, y - 1);
                    }
                    //south
                    if (y < (height - 1)) {
                        clearAround(x, y + 1);
                    }
                    //northwest
                    if (y > 0&&x > 0) {
                        clearAround(x - 1, y-1);
                    }
                    //northeast
                    if (y > 0&&x  < (width - 1)) {
                        clearAround(x + 1, y-1);
                    }
                    //southeast
                    if (y < (height - 1) &&x < (width - 1)) {
                        clearAround(x + 1, y+1);
                    }
                    //southwest
                    if (y < (height - 1)&&x > 0) {
                        clearAround(x - 1, y+1);
                    }
                }
            }
        }
    }

    //used to uncover all Mines in case of Failure
    public void setMinesCovered(Boolean b){
        for(int x=0;x< width;x++){
            for(int y=0;y< height;y++){
                if(board[x][y].isMine())
                    board[x][y].setCovered(b);
            }
        }
    }

    //Debugging/Test Method
    //(un)covers the whole board
    public void setBoardCovered(Boolean b){
        for(int x=0;x< width;x++) {
            for (int y = 0; y < height; y++) {
                board[x][y].setCovered(b);
            }
        }
    }
}
