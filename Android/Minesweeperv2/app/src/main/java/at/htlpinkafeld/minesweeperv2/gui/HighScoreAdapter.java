package at.htlpinkafeld.minesweeperv2.gui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import at.htlpinkafeld.minesweeperv2.R;
import at.htlpinkafeld.minesweeperv2.pojo.Score;

/**
 * Created by User on 26.05.2016.
 */
public class HighScoreAdapter extends BaseAdapter {

    private static class ViewHolder{
        public final TextView posV;
        public final TextView scoreV;
        public final TextView pnameV;
        public final TextView fieldV;

        private  ViewHolder(TextView posV,TextView scoreV, TextView pnameV, TextView fieldV){
            this.posV = posV;
            this.scoreV= scoreV;
            this.pnameV = pnameV;
            this.fieldV = fieldV;
        }
    }

    private List<Score> scores;
    private final Context context;


    public HighScoreAdapter(List<Score> scores, Context context) {
        this.scores=scores;
        this.context=context;
    }

    @Override
    public int getCount() {
        return scores.size();
    }

    @Override
    public Object getItem(int position) {
        return scores.get(position);
    }

    @Override
    public long getItemId(int position) {
        return scores.get(position).getPosition();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView posV;
        TextView scoreV;
        TextView pnameV;
        TextView fieldV;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.highscorelist_item, parent, false);

            posV = (TextView) convertView.findViewById(R.id.highscore_item_position);
            scoreV = (TextView) convertView.findViewById(R.id.highscore_item_score);
            pnameV = (TextView) convertView.findViewById(R.id.highscore_item_name);
            fieldV = (TextView) convertView.findViewById(R.id.highscore_item_field);

            convertView.setTag(new ViewHolder(posV,scoreV,pnameV, fieldV));
        }

        ViewHolder viewHolder = (ViewHolder) convertView.getTag();

        posV=viewHolder.posV;
        scoreV=viewHolder.scoreV;
        pnameV=viewHolder.pnameV;
        fieldV=viewHolder.fieldV;

        posV.setText(((Score) getItem(position)).getPosition()+".");
        scoreV.setText(String.valueOf(((Score)getItem(position)).getScore()));
        pnameV.setText(((Score)getItem(position)).getPname());
        fieldV.setText(((Score)getItem(position)).getFieldSize());

        return convertView;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }
}
