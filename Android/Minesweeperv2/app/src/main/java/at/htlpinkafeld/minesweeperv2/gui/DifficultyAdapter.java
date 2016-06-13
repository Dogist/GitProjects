package at.htlpinkafeld.minesweeperv2.gui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import at.htlpinkafeld.minesweeperv2.R;
import at.htlpinkafeld.minesweeperv2.service.FieldServiceClass;
import at.htlpinkafeld.minesweeperv2.util.DifficultyEnum;

/**
 * Created by User on 29.12.2015.
 */
public class DifficultyAdapter extends BaseAdapter implements ListAdapter {

    private final Context context;
    private FieldServiceClass sc;

    public DifficultyAdapter(Context context) {
        sc = FieldServiceClass.getFieldServiceClass();
        this.context = context;
    }

    @Override
    public int getCount() {
        return DifficultyEnum.values().length;
    }

    @Override
    public Object getItem(int position) {
        return DifficultyEnum.values()[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView diffView;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.difficulty_entry, parent, false);
            diffView = (TextView) convertView.findViewById(R.id.difficulty_entryitem);
            convertView.setTag(new ViewHolder(diffView));
        }
        ViewHolder viewHolder = (ViewHolder) convertView.getTag();

        DifficultyEnum opt = (DifficultyEnum) getItem(position);
        diffView = viewHolder.diffV;
        diffView.setText(opt.name());

        return convertView;
    }

    private static class ViewHolder {
        public final TextView diffV;

        private ViewHolder(TextView diffV) {
            this.diffV = diffV;
        }
    }
}
