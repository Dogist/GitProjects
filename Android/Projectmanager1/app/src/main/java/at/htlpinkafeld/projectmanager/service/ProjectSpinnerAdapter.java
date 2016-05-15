package at.htlpinkafeld.projectmanager.service;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import at.htlpinkafeld.projectmanager.R;


/**
 * Created by User on 23.11.2015.
 */




public class ProjectSpinnerAdapter extends BaseAdapter {

    private static class ViewHolder{
        public final TextView nameV;
        public final ImageView imageV;

        private ViewHolder(TextView nameV, ImageView imageV) {
            this.nameV = nameV;
            this.imageV=imageV;
        }
    }

    private at.htlpinkafeld.projectmanager.service.ServiceClass sc;
    private final Context context;

    public ProjectSpinnerAdapter(Context context) {
        this.sc = at.htlpinkafeld.projectmanager.service.ServiceClass.getServiceClass();
        this.context = context;
    }

    @Override
    public int getCount() {
        return sc.sizeP();
    }

    @Override
    public at.htlpinkafeld.projectmanager.pojo.Project getItem(int position) {
        return sc.getProject(position);
    }

    @Override
    public long getItemId(int position) {
        return  position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView nameView;
        ImageView imageView;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.project_spinner_entry, parent, false);
            nameView = (TextView) convertView.findViewById(R.id.spinner_projName);
            imageView = (ImageView) convertView.findViewById(R.id.spinner_projImg);
            convertView.setTag(new ViewHolder(nameView, imageView));
        }
        ViewHolder viewHolder = (ViewHolder) convertView.getTag();

        at.htlpinkafeld.projectmanager.pojo.Project p=getItem((int) getItemId(position));
        nameView = viewHolder.nameV;
        nameView.setText(p.getName());

        imageView = viewHolder.imageV;
        imageView.setImageResource(R.mipmap.ic_launcher);
        return convertView;
    }
}
