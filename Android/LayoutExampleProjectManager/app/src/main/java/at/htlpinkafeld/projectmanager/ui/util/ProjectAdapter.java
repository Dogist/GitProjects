package at.htlpinkafeld.projectmanager.ui.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import at.htlpinkafeld.projectmanager.R;
import at.htlpinkafeld.projectmanager.pojo.Project;
import at.htlpinkafeld.projectmanager.service.ProjectManagerService;

/**
 * Created by tq on 15-11-20.
 */
public class ProjectAdapter extends BaseAdapter {

    private final Context context;

    public ProjectAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return ProjectManagerService.getInstance().getProjects().size();
    }

    @Override
    public Project getItem(int position) {
        return ProjectManagerService.getInstance().getProjects().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView label;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.project_item, parent, false);
            label = (TextView) convertView.findViewById(R.id.project_item_label);
            convertView.setTag(new ViewHolder(label));
        } else {
            label = ((ViewHolder) convertView.getTag()).getLabel();
        }
        label.setText(getItem(position).getName());
        return convertView;
    }

    private static class ViewHolder {
        private final TextView label;

        public ViewHolder(TextView label) {
            this.label = label;
        }

        public TextView getLabel() {
            return label;
        }
    }
}
