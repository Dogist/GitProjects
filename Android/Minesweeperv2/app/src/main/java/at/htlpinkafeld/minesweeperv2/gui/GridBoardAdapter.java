package at.htlpinkafeld.minesweeperv2.gui;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;

import at.htlpinkafeld.minesweeperv2.R;
import at.htlpinkafeld.minesweeperv2.pojo.MineField;
import at.htlpinkafeld.minesweeperv2.service.FieldServiceClass;

/**
 * Created by User on 29.12.2015.
 */
public class GridBoardAdapter extends BaseAdapter implements ListAdapter {


    private Context context;
    private FieldServiceClass sc;

    public GridBoardAdapter(Context context) {
        this.context = context;
        sc = FieldServiceClass.getFieldServiceClass();
    }


    @Override
    public int getCount() {
        return FieldServiceClass.getWidth() * FieldServiceClass.getHeight();
    }

    @Override
    public Object getItem(int position) {
        return sc.getField(position % FieldServiceClass.getWidth(), position / FieldServiceClass.getWidth());
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else {
            imageView = (ImageView) convertView;
        }

        MineField f = (MineField) getItem(position);
        //if tree + switch for setting the image
        if (f.isFlagged()) {
            imageView.setImageResource(R.drawable.field_flag);
        } else {
            if (f.isCovered()) {
                imageView.setImageResource(R.drawable.field_covered);
            } else {
                if (f.isMine()) {
                    imageView.setImageResource(R.drawable.field_mine);
                } else {
                    switch (f.getNearMines()) {
                        case 0:
                            imageView.setImageResource(R.drawable.field_empty);
                            break;
                        case 1:
                            imageView.setImageResource(R.drawable.field_1);
                            break;
                        case 2:
                            imageView.setImageResource(R.drawable.field_2);
                            break;
                        case 3:
                            imageView.setImageResource(R.drawable.field_3);
                            break;
                        case 4:
                            imageView.setImageResource(R.drawable.field_4);
                            break;
                        case 5:
                            imageView.setImageResource(R.drawable.field_5);
                            break;
                        case 6:
                            imageView.setImageResource(R.drawable.field_6);
                            break;
                        case 7:
                            imageView.setImageResource(R.drawable.field_7);
                            break;
                        case 8:
                            imageView.setImageResource(R.drawable.field_8);
                            break;
                    }
                }
            }
        }
        return imageView;
    }
}
