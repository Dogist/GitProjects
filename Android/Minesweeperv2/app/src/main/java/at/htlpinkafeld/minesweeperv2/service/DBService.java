package at.htlpinkafeld.minesweeperv2.service;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;

import com.google.gson.Gson;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import at.htlpinkafeld.minesweeperv2.cp.MSContract;
import at.htlpinkafeld.minesweeperv2.cp.SaveGameTable;
import at.htlpinkafeld.minesweeperv2.cp.ScoreTable;
import at.htlpinkafeld.minesweeperv2.pojo.Game;
import at.htlpinkafeld.minesweeperv2.pojo.Score;

/**
 * Created by User on 20.05.2016.
 */
public class DBService {
    ContentResolver cr;
    Gson gson;

    public DBService(ContentResolver cr) {
        this.cr = cr;
        gson = new Gson();
    }

    public void saveGame(long time) {
        ContentValues cv = new ContentValues();
        Game game = FieldServiceClass.getFieldServiceClass().getGame();
        cv.put(SaveGameTable.COLUMN_BOARD, gson.toJson(game.getBoard()));
        cv.put(SaveGameTable.COLUMN_FIELDSUNCOVERED, game.getFieldsUncovered());
        cv.put(SaveGameTable.COLUMN_NUMMINES, game.getNumMines());
        cv.put(SaveGameTable.COLUMN_TIME, time);
        //if(game.getGame_id()==-1)
        cr.insert(MSContract.SaveGames.CONTENT_URI, cv);
        //Funktionalität für Updaten statt neues hinzufügen bei gleichem Spiel
        //else {
        //    cv.put(SaveGameTable.COLUMN_SGID, game.getGame_id());
        //    cr.update(MSContract.CONTENT_URI,cv,SaveGameTable.COLUMN_SGID+" = ?",new String[]{String.valueOf(game.getGame_id())});
        //}

    }

    public void saveScore(long time, String playerName) {
        ContentValues cv = new ContentValues();
        cv.put(ScoreTable.COLUMN_TIME, time);
        cv.put(ScoreTable.COLUMN_PNAME, playerName);
        cv.put(ScoreTable.COLUMN_HEIGHT, FieldServiceClass.getHeight());
        cv.put(ScoreTable.COLUMN_WIDTH, FieldServiceClass.getWidth());
        cv.put(ScoreTable.COLUMN_NUMMINES, FieldServiceClass.getNumMines());
        cv.put(ScoreTable.COLUMN_FIELDS_CLEARED, FieldServiceClass.getFieldServiceClass().getFieldsUncovered());

        cr.insert(MSContract.Scores.CONTENT_URI, cv);
    }

    public void deleteSaveGame(long id) {
        if (id != -1) {
            String[] clause = {Long.toString(id)};
            cr.delete(MSContract.SaveGames.CONTENT_URI, MSContract.SaveGames._ID + " = ?", clause);
        }
    }

    public Cursor getSaveGames() {
        return cr.query(MSContract.SaveGames.CONTENT_URI, MSContract.SaveGames.PROJECTION_ALL, null, null, MSContract.SaveGames.SORT_ORDER_DEFAULT);
    }

    public List<Score> getScores() {
        Cursor c = cr.query(MSContract.Scores.CONTENT_URI, MSContract.Scores.PROJECTION_ALL, null, null, MSContract.Scores.SORT_ORDER_DEFAULT);
        List<Score> scores = new LinkedList<>();
        int numMines, fieldsUncovered, width, height;
        long score, time;
        while (c.moveToNext()) {
            width = c.getInt(c.getColumnIndexOrThrow(MSContract.Scores.WIDTH));
            height = c.getInt(c.getColumnIndexOrThrow(MSContract.Scores.HEIGHT));
            numMines = c.getInt(c.getColumnIndexOrThrow(MSContract.Scores.NUMMINES));
            fieldsUncovered = c.getInt(c.getColumnIndexOrThrow(MSContract.Scores.FIELDS_CLEARED));
            time = c.getLong(c.getColumnIndexOrThrow(MSContract.Scores.TIME));

            score = (long) (Math.sqrt(numMines) * Math.sqrt(fieldsUncovered) * 1000);
            if (fieldsUncovered == (width * height - numMines))
                score *= 2.5;
            score /= Math.sqrt(time / 10);

            scores.add(new Score(-1, c.getString(c.getColumnIndexOrThrow(MSContract.Scores.PNAME)), score, height + "x" + width, numMines));
        }
        c.close();
        Collections.sort(scores, Collections.reverseOrder());
        Iterator<Score> scoreIterator = scores.iterator();
        for (int pos = 1; scoreIterator.hasNext(); pos++) {
            Score s = scoreIterator.next();
            s.setPosition(pos);
        }
        return scores;
    }
}
