package at.htlpinkafeld.projectmanager.ui.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import at.htlpinkafeld.projectmanager.R;

/**
 * Created by tq on 15-11-30.
 */
public class ListAdapter<T> extends BaseAdapter {

    private List<T> items = Collections.emptyList();

    private final Context context;

    public ListAdapter(List<T> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public T getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.generic_list_item, parent, false);
        }

        TextView label = (TextView) convertView.findViewById(R.id.generic_list_item_txt);

        label.setText(getItem(position).toString());

        return convertView;
    }
}
