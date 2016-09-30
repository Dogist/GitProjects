package at.htlpinkafeld.projectmanager.service;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import at.htlpinkafeld.projectmanager.R;
import at.htlpinkafeld.projectmanager.pojo.Project;


/**
 * Created by User on 01.02.2016.
 */
public class ProjectListAdapter extends BaseAdapter implements ListAdapter {


    private final Context context;
    private ServiceClass sc;
    public ProjectListAdapter(Context context) {
        this.sc = ServiceClass.getServiceClass();
        this.context = context;
    }

    @Override
    public int getCount() {
        return sc.sizeP();
    }

    @Override
    public Project getItem(int position) {
        return sc.getProject(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView projView;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.project_list_entry, parent, false);
            projView = (TextView) convertView.findViewById(R.id.projectlist_item);
            convertView.setTag(new ViewHolder(projView));
        }
        ViewHolder viewHolder = (ViewHolder) convertView.getTag();

        Project p = getItem((int) getItemId(position));
        projView = viewHolder.projV;
        projView.setText(p.getName());

        return convertView;
    }

    private static class ViewHolder {
        public final TextView projV;

        private ViewHolder(TextView projV) {
            this.projV = projV;
        }
    }


}
