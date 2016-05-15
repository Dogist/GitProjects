package at.htlpinkafeld.projectmanager.ui.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import at.htlpinkafeld.projectmanager.R;
import at.htlpinkafeld.projectmanager.service.EntityServiceAdapter;

/**
 * Created by tq on 16-01-08.
 */
public class EntityListAdapter<E> extends BaseAdapter {

    private final Context context;
    private final EntityServiceAdapter<E> service;

    public EntityListAdapter(Context context, EntityServiceAdapter<E> service) {
        this.context = context;
        this.service = service;
    }

    @Override
    public int getCount() {
        return service.getList().size();
    }

    @Override
    public E getItem(int position) {
        List<E> list = service.getList();
        if (position >= 0 && position < list.size()) {
            return list.get(position);
        } else {
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.generic_list_item, parent, false);
//            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_item, parent, false);
        }

        TextView label = (TextView) convertView.findViewById(R.id.generic_list_item_txt);
//        TextView label = (TextView) convertView.findViewById(android.R.id.text1);

        if (label != null) {
            label.setText(getItem(position).toString());
        }


        return convertView;
    }
}
